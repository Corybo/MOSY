package qqc.mosyits.haw.qqc.Networking;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.List;

import qqc.mosyits.haw.qqc.GameActivity;
import qqc.mosyits.haw.qqc.R;
import qqc.mosyits.haw.qqc.StartActivity;

/**
 * Created by Mona on 05.06.2017.
 */

public class ClientHandler implements MqttCallback {
    private static final String PLAYER_KEY = "player_key";

//    http://www.hivemq.com/demos/websocket-client/
    private static final int NOTIFICATION_ID = 42;

    private MqttAndroidClient client;
    private Context context;
    //    private String brokerURL = "tcp://broker.mqttdashboard.com";
    private String brokerURL = "tcp://kassiopeia.mt.haw-hamburg.de";
    //tcp://broker.hivemq.com:1883
    private static StartActivity.GameStartStatus startStatus;
    private String player;
    public static int[] idList;
    public static int maxQuestionsToBeAnswered = 10;

    public ClientHandler(Context c) {
        //this.getApplicationContext() für context
        this.context = c;
        connectWithServer();
        startStatus = StartActivity.GameStartStatus.READY;
    }

    //outsourced method to connect to the server
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

    //method to publish a topic (if the correct answer is selected)
    //????wofür toPublish(View v)??? siehe ytvideo
    public void toPublish(View v, String pubMessage) {
//        Toast.makeText(context, "Publish from Handler", Toast.LENGTH_SHORT).show();
        String topic = "haw/dmi/mt/its/ss17/qqc";
        //String message = "player1"; wird vom konstruktor übergeben

        try {
            if (!client.isConnected()) {
                Toast.makeText(context, "not connected", Toast.LENGTH_SHORT).show();
            }
            if (client.isConnected()) {
                client.publish(topic, pubMessage.getBytes(), 0, false);
//                Toast.makeText(context, "publish successful", Toast.LENGTH_SHORT).show();
            }

//        try {
//            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void toSubscribe() {
        Toast.makeText(context, "Subscribe method from handler", Toast.LENGTH_SHORT).show();
        //App stürzt ab Lösung: https://stackoverflow.com/questions/43038597/android-studio-mqtt-not-connecting
        //andere verwendung von IMqttActionListener
        try {
            String topic = context.getString(R.string.topic);
            client.subscribe(topic, 0);
        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(context, "Subscribe lässt App abstürzen", Toast.LENGTH_SHORT).show();
        }
    }

    public void toClose() {
        client.close();
    }

    @Override
    public void connectionLost(Throwable cause) {
        Toast.makeText(context, "ConnectionLost Handler", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        if (topic.equals(context.getString(R.string.topic))) {
            String bodymessage = new String(message.getPayload()); //modymessage inhalt der gepublishten message kann weiterverarbeitetet werden
            Toast.makeText(context, "Handler arrived Message: " + bodymessage, Toast.LENGTH_SHORT).show();
            //STATUS: READY
            if (bodymessage.equals(context.getResources().getString(R.string.pub_waiting))) {
                setStartStatus(StartActivity.GameStartStatus.WAITING);
                Toast.makeText(context, R.string.waiting_for_player_2, Toast.LENGTH_SHORT).show();
            }
            //STATUS: WAITING
            else if (bodymessage.equals(context.getResources().getString(R.string.pub_started))) {
                setStartStatus(StartActivity.GameStartStatus.BLOCKED);
                Toast.makeText(context, R.string.game_started, Toast.LENGTH_SHORT).show();
                Intent startToGame = new Intent(context, GameActivity.class);
                //TODO:Geht nur bei Nougat, Lösung finden für Marshmallow:
                startToGame.putExtra(PLAYER_KEY, player);
                context.startActivity(startToGame);
            }
            //STATUS: BLOCKED
            else if (bodymessage.equals(context.getResources().getString(R.string.pub_end_game))) {
                setStartStatus(StartActivity.GameStartStatus.READY);
            }
            //QuestionArray
            else if (bodymessage.startsWith("#")) {
                String strId = bodymessage.substring(1,2);
                Toast.makeText(context, "ohne Hashtag: " + strId, Toast.LENGTH_SHORT).show();
                int id = Integer.valueOf(strId);
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    public static StartActivity.GameStartStatus getStartStatus() {
        return ClientHandler.startStatus;
    }

    public static void setStartStatus(StartActivity.GameStartStatus startStatus) {
        ClientHandler.startStatus = startStatus;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
