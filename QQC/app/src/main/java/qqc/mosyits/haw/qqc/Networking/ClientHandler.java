package qqc.mosyits.haw.qqc.Networking;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

import qqc.mosyits.haw.qqc.GameActivity;
import qqc.mosyits.haw.qqc.R;
import qqc.mosyits.haw.qqc.StartActivity;

/**
 * Handles all network interaction, publishing and subscribing
 */

public class ClientHandler implements MqttCallback {
    public static final String PLAYER_KEY = "player_key";
    public static final String QUESTION_KEY = "question_key";

    //    http://www.hivemq.com/demos/websocket-client/
    private static final int NOTIFICATION_ID = 42;
    private static ArrayList<MessageObserver> observerList = new ArrayList<>();
    private static String questionIdString;

    private MqttAndroidClient client;
    private Context context;
    //    private String brokerURL = "tcp://broker.mqttdashboard.com";
    private String brokerURL = "tcp://kassiopeia.mt.haw-hamburg.de";
    //tcp://broker.hivemq.com:1883
    private static StartActivity.GameStartStatus startStatus;
    public static int[] idList;
    public static int maxQuestionsToBeAnswered = 10;
    private ArrayList<Integer> questionSequence;
    private boolean isFirstQuestion = true;

    public ClientHandler(Context c) {
        this.context = c;
        connectWithServer();
        startStatus = StartActivity.GameStartStatus.READY;
    }

    /**
     * connect to server
     */
    public void connectWithServer() {
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(context, brokerURL, clientId);
        client.setCallback(this);
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
            client.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();
                    toSubscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }


    }

    /**
     * method to publish a topic (if the correct answer is selected)
     */
    //TODO:wofür toPublish(View v)??? siehe ytvideo
    public void toPublish(View v, String pubMessage) {
        String topic = context.getString(R.string.topic);

        try {
            if (!client.isConnected()) {
                Toast.makeText(context, "not connected", Toast.LENGTH_SHORT).show();
            }
            if (client.isConnected()) {
                client.publish(topic, pubMessage.getBytes(), 0, false);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to subscribe a topic
     */
    public void toSubscribe() {
        //TODO: App stürzt ab Lösung: https://stackoverflow.com/questions/43038597/android-studio-mqtt-not-connecting
        //TODO: andere verwendung von IMqttActionListener
        try {
            String topic = context.getString(R.string.topic);
            client.subscribe(topic, 0);
        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(context, "Subscribe lässt App abstürzen", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO: client schließen nach jedem Spiel
    public void toClose() {
        client.close();
    }

    @Override
    public void connectionLost(Throwable cause) {
        Toast.makeText(context, "ConnectionLost Handler", Toast.LENGTH_SHORT).show();
    }

    /**
     * when message arrived in subscribed topic
     *
     * @param topic
     * @param message
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        if (topic.equals(context.getString(R.string.topic))) {
            String bodymessage = new String(message.getPayload()); //modymessage inhalt der gepublishten message kann weiterverarbeitetet werden
            Toast.makeText(context, "Handler arrived Message: " + bodymessage, Toast.LENGTH_SHORT).show();
            //STATUS: READY
            if (bodymessage.equals(context.getResources().getString(R.string.pub_waiting_start))) {
                setStartStatus(StartActivity.GameStartStatus.WAITING);
                Toast.makeText(context, context.getString(R.string.local_status_waiting), Toast.LENGTH_SHORT).show();
            }
            //STATUS: WAITING
            else if (bodymessage.equals(context.getResources().getString(R.string.pub_started_join))) {
                startGame();
            }
            //STATUS: BLOCKED
            else if (bodymessage.equals(context.getResources().getString(R.string.pub_end_game))) {
                setStartStatus(StartActivity.GameStartStatus.READY);
            }
            //QuestionArray
            else if (bodymessage.startsWith("#")) {
                notifyMessageObserver(bodymessage);
            }
            //Start next question
            else if (bodymessage.equalsIgnoreCase(context.getResources().getString(R.string.msg_go))) {
                if (isFirstQuestion) {
                    startGame();
                    isFirstQuestion = false;
                }
                notifyMessageObserver(bodymessage);
            }
            else if(bodymessage.startsWith(context.getString(R.string.time))){
                notifyMessageObserver(bodymessage);
            }
            else if(bodymessage.equals(context.getString(R.string.msg_tie))){
                Toast.makeText(context, R.string.txt_tie, Toast.LENGTH_SHORT).show();
            }
            //TODO: PLAYER Messages
        }
    }

    /**
     * start game -> first question is displayed
     */
    private void startGame() {
        setStartStatus(StartActivity.GameStartStatus.BLOCKED);
        Intent startToGame = new Intent(context, GameActivity.class);
        //TODO:Geht nur bei Nougat, Lösung finden für Marshmallow:
        startToGame.putExtra(QUESTION_KEY, questionIdString);
        context.startActivity(startToGame);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    /**
     * @return startStatus: READY, WAITING, BLOCKED
     */
    public static StartActivity.GameStartStatus getStartStatus() {
        return ClientHandler.startStatus;
    }

    /**
     * sets startStatus in ClientHandler
     *
     * @param startStatus READY, WAITING, BLOCKED
     */
    public static void setStartStatus(StartActivity.GameStartStatus startStatus) {
        ClientHandler.startStatus = startStatus;
    }

    /**
     * sets the questionSequence
     *
     * @param questionSequence generated sequence with which question order is determinated
     */
    public void setQuestionSequence(ArrayList<Integer> questionSequence) {
        this.questionSequence = questionSequence;
    }

    //TODO: Methode aufrufen, wenn die nächste Nummer geschickt werden soll

    /**
     * send the QuestionNumber at time
     *
     * @param id
     */
    public void sendQuestionNumber(int id) {
        //Question value as String
        String s = "#" + String.valueOf(questionSequence.get(id));
        toPublish(null, s);
    }

    /*** OBSERVER **********************************************************************************/
    /**
     * Add new observer to list
     *
     * @param observer new MESSAGE_OBSERVER
     */
    public static void addMessageObserver(MessageObserver observer) {
        observerList.add(observer);
    }

    /**
     * Notify all messageObservers when new message came in
     *
     * @param msg new message
     */
    public static void notifyMessageObserver(String msg) {
        questionIdString = msg;
        for (MessageObserver observer : observerList) {
            observer.updateMessage(msg);
        }
    }
}
