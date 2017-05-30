package com.example.abv995.mqttapptest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MqttAndroidClient client;
    TextView sub_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_publish = (Button) findViewById(R.id.button_publish);
        button_publish.setOnClickListener(this);

        sub_text = (TextView) findViewById(R.id.textView_sub);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",
                clientId);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);


        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(MainActivity.this, "Connection failed", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        toSubscribe(sub_text);
    }


    public void toPublish(View v) {
        String topic = "haw/dmi/mt/its/ss17/qqc";
        String message = "halloooo tjarkoo";

        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void toSubscribe(View v) {
        String topic = "haw/dmi/mt/its/ss17/qqc";
        int qos = 1;
        try {
            if (client != null) {
                IMqttToken subToken = this.client.subscribe(topic, qos);
                subToken.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        // The message was published
                        Toast.makeText(MainActivity.this, "Receive topic", Toast.LENGTH_SHORT).show();
                        sub_text.setText("Receive");
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken,
                                          Throwable exception) {
                        // The subscription could not be performed, maybe the user was not
                        // authorized to subscribe on the specified topic e.g. using wildcards
                        Toast.makeText(MainActivity.this, "didn't receive ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_publish:
                Toast.makeText(this, "Publish", Toast.LENGTH_SHORT).show();
                toPublish(v);
        }

    }


}
