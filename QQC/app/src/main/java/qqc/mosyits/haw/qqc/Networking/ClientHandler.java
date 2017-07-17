package qqc.mosyits.haw.qqc.Networking;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

import qqc.mosyits.haw.qqc.Database.DatabaseHandler;
import qqc.mosyits.haw.qqc.GameActivity;
import qqc.mosyits.haw.qqc.Questions.QuestionInserts;
import qqc.mosyits.haw.qqc.R;
import qqc.mosyits.haw.qqc.StartActivity;

/**
 * Handles all network interaction, publishing and subscribing
 */

public class ClientHandler implements MqttCallback {
    private static ClientHandler staticClientHandler = null;
    private final String TAG = getClass().getSimpleName();

    private static ArrayList<MessageObserver> observerList = new ArrayList<>();

    private MqttAndroidClient client;
    private Context context;
    private String brokerURL = "tcp://kassiopeia.mt.haw-hamburg.de";
    private static StartActivity.GameStartStatus startStatus;
    public static int maxQuestionsToBeAnswered = 10;
    private boolean isFirstQuestion = true;
    private boolean firstStart = true;
    private int round = 0;

    public ClientHandler(Context c) {
        Log.i(TAG, "ClientHandler: constructor");
        this.context = c;
        connectWithServer();
        startStatus = StartActivity.GameStartStatus.READY;
    }

    /**
     * importent that only one ClientHandler object exists
     *
     * @return the saved static ClientHandler object
     */
    public static ClientHandler getClientHandler() {
        Log.i("ClientHandler", "getClientHandler");
        return staticClientHandler;
    }

    /**
     * saves the clientHandler object as static variable
     *
     * @param clientHandler
     */
    public static void setClientHandler(ClientHandler clientHandler) {
        Log.i("ClientHandler", "setClientHandler: " + clientHandler.toString());
        staticClientHandler = clientHandler;
    }

    /**
     * connect to server
     */
    public void connectWithServer() {
        Log.i(TAG, "connectWithServer");
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(context, brokerURL, clientId);
        client.setCallback(this);
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
            options.setUserName(context.getString(R.string.server_username));
            options.setPassword(context.getString(R.string.server_password).toCharArray());
            client.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "connectWithServer: onSuccess");
                    // We are connected
                    Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();
                    if (firstStart) {
                        toPublish(null, context.getString(R.string.ask_for_start_status));
                        firstStart = false;
                    }
                    toSubscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG, "connectWithServer: onFailure");
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }


    }

    /**
     * method to publish a message under a topic (if the correct answer is selected)
     */
    //TODO: view am Ende rausnehmen
    public void toPublish(View v, String pubMessage) {
        Log.i(TAG, "toPublish: " + pubMessage);
        String topic = context.getString(R.string.topic);
        try {
            if (!client.isConnected()) {
                Log.i(TAG, "toPublish: not connected");
            }
            if (client.isConnected()) {
                Log.i(TAG, "toPublish: connected");
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
        try {
            Log.i(TAG, "toSubscribe");
            String topic = context.getString(R.string.topic);
            client.subscribe(topic, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void toClose() {
        Log.i(TAG, "toClose");
        client.close();
    }

    @Override
    public void connectionLost(Throwable cause) {
        Log.i(TAG, "connectionLost");
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
        Log.i(TAG, "messageArrived");
        if (topic.equals(context.getString(R.string.topic))) {
            Log.i(TAG, "messageArrived: topic=" + topic);
            String bodymessage = new String(message.getPayload());
            //STATUS REQUEST
            if (bodymessage.equals(context.getString(R.string.ask_for_start_status))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                if (StartActivity.player == StartActivity.Player.PLAYER_1) {
                    String reply = "error";
                    switch (getStartStatus()) {
                        case READY:
                            reply = context.getString(R.string.reply_ready_status);
                            break;
                        case WAITING:
                            reply = context.getString(R.string.reply_waiting_status);
                            break;
                        case BLOCKED:
                            reply = context.getString(R.string.reply_blocked_status);
                            break;
                    }
                    toPublish(null, reply);
                }
                //Reply status request: READY
            } else if (bodymessage.equals(context.getString(R.string.reply_ready_status))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                setStartStatus(StartActivity.GameStartStatus.READY);
                //Reply status request: WAITING
            } else if (bodymessage.equals(context.getString(R.string.reply_waiting_status))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                setStartStatus(StartActivity.GameStartStatus.WAITING);
                //Reply status request: BLOCKED
            } else if (bodymessage.equals(context.getString(R.string.reply_blocked_status))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                setStartStatus(StartActivity.GameStartStatus.BLOCKED);
                //STATUS: READY
            } else if (bodymessage.equals(context.getResources().getString(R.string.pub_waiting_start))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                setStartStatus(StartActivity.GameStartStatus.WAITING);
            }
            //STATUS: WAITING
            else if (bodymessage.equals(context.getResources().getString(R.string.pub_started_join))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                notifyMessageObserver(bodymessage, this);
            }
            //STATUS: BLOCKED
            else if (bodymessage.equals(context.getResources().getString(R.string.pub_end_game))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                notifyMessageObserver(bodymessage, this);
                setStartStatus(StartActivity.GameStartStatus.READY);
            }
            //QuestionArray
            else if (bodymessage.startsWith("#")) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                context.deleteDatabase(new DatabaseHandler(context).DATABASE_NAME);
                String s = bodymessage.substring(1);
                int round = Integer.valueOf(s);
                setRound(round);
                new QuestionInserts(context, round);
                Log.i(TAG, "onCreate: round=" + getRound());
            }
            //Start next question
            else if (bodymessage.equalsIgnoreCase(context.getResources().getString(R.string.msg_go))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                if (isFirstQuestion) {
                    Log.i(TAG, "messageArrived: " + bodymessage + " isFirstQuestion");
                    startGame();
                    isFirstQuestion = false;
                } else {
                    notifyMessageObserver(bodymessage, this);
                }
            } else if (bodymessage.startsWith(context.getString(R.string.time))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                notifyMessageObserver(bodymessage, this);
            } else if (bodymessage.equals(context.getString(R.string.msg_tie))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
                Toast.makeText(context, R.string.txt_tie, Toast.LENGTH_SHORT).show();
            } else if (bodymessage.equals(context.getString(R.string.player_1))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
            } else if (bodymessage.equals(context.getString(R.string.player_2))) {
                Log.i(TAG, "messageArrived: " + bodymessage);
            }
            //set number of rated Answers for each player
            else if (bodymessage.startsWith(context.getString(R.string.rated_answers1))) {
                notifyMessageObserver(bodymessage, this);
            } else if (bodymessage.startsWith(context.getString(R.string.rated_answers2))) {
                notifyMessageObserver(bodymessage, this);
            }
            //stop appliction
            else if (bodymessage.equals(context.getString(R.string.emergency_stop))) {
                System.exit(0);
            }
            // check who won the game
            else if (bodymessage.startsWith("win")) {
                notifyMessageObserver(bodymessage, this);
            }
        }
    }

    /**
     * start game -> first question is displayed
     */
    private void startGame() {
        Log.i(TAG, "startGame");
        setStartStatus(StartActivity.GameStartStatus.BLOCKED);
        Intent startToGame = new Intent(context, GameActivity.class);
        context.startActivity(startToGame);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.i(TAG, "deliveryComplete");
    }

    /**
     * @return startStatus: READY, WAITING, BLOCKED
     */
    public static StartActivity.GameStartStatus getStartStatus() {
        Log.i("ClientHandler", "getStartStatus");
        return ClientHandler.startStatus;
    }

    /**
     * sets startStatus in ClientHandler
     *
     * @param startStatus READY, WAITING, BLOCKED
     */
    public static void setStartStatus(StartActivity.GameStartStatus startStatus) {
        Log.i("ClientHandler", "setStartStatus:" + startStatus.toString());
        ClientHandler.startStatus = startStatus;
    }

    /*** OBSERVER **********************************************************************************/
    /**
     * Add new observer to list
     *
     * @param observer new MESSAGE_OBSERVER
     */
    public static void addMessageObserver(MessageObserver observer) {
        Log.i("ClientHandler", "addMessageObserver");
        observerList.add(observer);
    }

    /**
     * Remove observer from List
     *
     * @param observer which will be removed
     */
    public static void removeMessageObserver(MessageObserver observer) {
        Log.i("ClientHandler", "removeMessageObserver: observer=" + observer.toString());
        observerList.remove(observer);
    }

    /**
     * Notify all messageObservers when new message came in
     *
     * @param msg           new message
     * @param clientHandler
     */
    public static void notifyMessageObserver(String msg, ClientHandler clientHandler) {
        Log.i("ClientHandler", "notifyMessageObserver");
        for (MessageObserver observer : observerList) {
            Log.i("ClientHandler", "notifyMessageObserver: observer=" + observer.toString() + ", clienthandler=" + clientHandler.toString());
            observer.updateMessage(msg, clientHandler);
        }
    }

    /**
     * when a new round is started set true
     *
     * @param isFirstQuestion true: when a new round is started
     */
    public void setIsFirstQuestion(boolean isFirstQuestion) {
        this.isFirstQuestion = isFirstQuestion;
    }

    /**
     * Gets the number of current round
     *
     * @return round (0 to 7)
     */
    public int getRound() {
        Log.i(TAG, "getRound: " + round);
        return round;
    }

    /**
     * sets the current round
     *
     * @param round (0 to 7)
     */
    public void setRound(int round) {
        Log.i(TAG, "setRound: " + round);
        this.round = round;
    }
}
