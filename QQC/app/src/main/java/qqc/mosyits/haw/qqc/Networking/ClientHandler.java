package qqc.mosyits.haw.qqc.Networking;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
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

import qqc.mosyits.haw.qqc.GameActivity;
import qqc.mosyits.haw.qqc.R;
import qqc.mosyits.haw.qqc.StartActivity;

/**
 * Created by Mona on 05.06.2017.
 */

public class ClientHandler implements MqttCallback {

//    http://www.hivemq.com/demos/websocket-client/
    private static final int NOTIFICATION_ID = 42;

    private MqttAndroidClient client;
    private Context c;
    private String brokerURL = "tcp://broker.mqttdashboard.com";
//    private String brokerURL = "kassiopeia.mt.haw-hamburg.de";
    private boolean gameStarted = false;

    //tcp://broker.hivemq.com:1883

    public ClientHandler(Context c) {
        //this.getApplicationContext() für c
        this.c = c;
        connectWithServer();
    }

    //outsourced method to connect to the server
    public void connectWithServer() {
        String clientId = MqttClient.generateClientId();


        client = new MqttAndroidClient(c, brokerURL, clientId);
        client.setCallback(this);
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);

            options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);


            client.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(c, "connected", Toast.LENGTH_SHORT).show();
                    toSubscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(c, "connection failed", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }


    }

    //method to publish a topic (if the correct answer is selected)
    //????wofür toPublish(View v)??? siehe ytvideo
    public void toPublish(View v, int msgStringRes) {
        Toast.makeText(c, "Publish from Handler", Toast.LENGTH_SHORT).show();
        String topic = c.getString(R.string.topic);
        //String message = "player1"; wird vom konstruktor übergeben

        try {
            if (!client.isConnected()) {
                Toast.makeText(c, "not connected", Toast.LENGTH_SHORT).show();
            }
            if (client.isConnected()) {
                client.publish(topic, c.getString(msgStringRes).getBytes(), 0, false);
                Toast.makeText(c, "publish successful", Toast.LENGTH_SHORT).show();
            }

//        try {
//            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void toSubscribe() {
        Toast.makeText(c, "Subscribe method from handler", Toast.LENGTH_SHORT).show();
        //App stürzt ab Lösung: https://stackoverflow.com/questions/43038597/android-studio-mqtt-not-connecting
        //andere verwendung von IMqttActionListener
        try {
            String topic = c.getString(R.string.topic);
            client.subscribe(topic, 0);
        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(c, "Subscribe lässt ab abstürzen", Toast.LENGTH_SHORT).show();
        }
    }

    public void toClose() {
        client.close();
    }

    @Override
    public void connectionLost(Throwable cause) {
        Toast.makeText(c, "ConnectionLost Handler", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        if (topic.equals(c.getString(R.string.topic))) {
            String bodymessage = new String(message.getPayload()); //modymessage inhalt der gepublishten message kann weiterverarbeitetet werden
            if (bodymessage.equals(c.getString(R.string.msg_start_game))) {
                gameStarted = true;
                Toast.makeText(c, "Handler arrived Message: " + bodymessage, Toast.LENGTH_SHORT).show();
                createAndSendNotification("subscribe - message arrived: " + bodymessage);
            } else if (bodymessage.equals(c.getString(R.string.msg_join_game))) {
                Toast.makeText(c, "Handler arrived Message: " + bodymessage, Toast.LENGTH_SHORT).show();
                Intent startToGame = new Intent(c, GameActivity.class);
                gameStarted = false;
                c.startActivity(startToGame);
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    /**
     * creates the notification
     *
     * @param stringNotification message which will be shown
     */
    private void createAndSendNotification(String stringNotification) {
        //Reaction to notification
        PendingIntent reply = PendingIntent.getActivity(c, 0, new Intent(c, StartActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        //Builder for notification
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(c)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentTitle(c.getString(R.string.app_name))
                .setContentText(stringNotification)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(reply)
                .setDefaults(Notification.DEFAULT_VIBRATE);
        //Show notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(c);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    public boolean startMessageSent() {
        return gameStarted;
    }
}
