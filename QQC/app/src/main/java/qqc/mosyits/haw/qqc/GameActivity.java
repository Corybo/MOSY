package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import qqc.mosyits.haw.qqc.Database.QuizDataSource;
import qqc.mosyits.haw.qqc.Questions.Question;
import qqc.mosyits.haw.qqc.Questions.QuestionHandler;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionField;
    private Button answerA;
    private Button answerB;
    private Button answerC;
    private Button answerD;
    private Button buttonResult;
    private Question currentQuestion;
    private ArrayList<Integer> idList;
    private int idSelect = 0;
    private int amountQuestionsInDatabase;
    private int maxQuestionsToBeAnswered = 10;
    private int questionsAsked = 1;
    private MqttAndroidClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionField = (TextView) findViewById(R.id.question_field);
        answerA = (Button) findViewById(R.id.answer_a);
        answerB = (Button) findViewById(R.id.answer_b);
        answerC = (Button) findViewById(R.id.answer_c);
        answerD = (Button) findViewById(R.id.answer_d);
        buttonResult = (Button) findViewById(R.id.button_result);

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        buttonResult.setOnClickListener(this);

        amountQuestionsInDatabase = questionCount();
        setUpIdList(amountQuestionsInDatabase);
        askNextQuestion();

        connectWithServer();
    }

    //outsourced method to connect to the server
    public void connectWithServer(){
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883", clientId);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);


        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(GameActivity.this, "connected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(GameActivity.this, "connection failed", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    //method to publish a topic (if the correct answer is selected)
    //????wofür toPublish(View v)??? siehe ytvideo
    public void toPublish(){
        String topic = "haw/dmi/mt/its/ss17/qqc";
        String message = "test: hallo qqc entwickler: die Antwort ist richtig";

        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get questionCount from database
     *
     * @return questionCount
     */
    private int questionCount() {
        QuizDataSource dataSource = new QuizDataSource(this);
        int count = 0;
        try {
            dataSource.open();
            count = dataSource.getQuestionCount();
            dataSource.close();
        } catch (Exception ex) {
            Toast.makeText(this, R.string.error_database, Toast.LENGTH_SHORT).show();
        }
        return count;
    }

    /**
     * Fill array with values from 0 to how many questions are in the database and shuffle it
     *
     * @param questionCount amount of questions in database
     */
    private void setUpIdList(int questionCount) {
        idList = new ArrayList<>();
        for (int i = 0; i < questionCount; i++) {
            idList.add(i);
        }

        Collections.shuffle(idList);
        String debug = "";
        for (int i = 0; i < questionCount; i++) {
            debug += (idList.get(i) + " ");
        }
        Toast.makeText(this, "Shuffled question array: " + debug, Toast.LENGTH_SHORT).show();
    }

    /**
     * select next Question
     */
    private void askNextQuestion() {
        //TODO: Time delay 5s
        setQuestion(generateQuestionId());
    }

    /**
     * Setting the questions and their answers randomly on the buttons
     *
     * @param id
     */
    private void setQuestion(long id) {
        QuestionHandler questionHandler = new QuestionHandler(this);
        currentQuestion = questionHandler.getQuestionFromDb(id);
        questionField.setText(currentQuestion.getQuestion());
        String[] answers = {currentQuestion.getRightAnswer(), currentQuestion.getAnswer1(), currentQuestion.getAnswer2(), currentQuestion.getAnswer3()};
        Collections.shuffle(Arrays.asList(answers));
        answerA.setText(answers[0]);
        answerB.setText(answers[1]);
        answerC.setText(answers[2]);
        answerD.setText(answers[3]);
    }

    /**
     * Generate random number
     *
     * @return generated number for choosing question out of database
     */
    private long generateQuestionId() {
        long questionId = idList.get(idSelect++);
        Toast.makeText(this, "ArrayValue: " + questionId, Toast.LENGTH_SHORT).show();
        return questionId;
    }

    /**
     * Check if answer is right or wrong
     *
     * @param answer Button which was clicked
     * @return true: right answer, false: wrong answer
     */
    private boolean checkAnswer(Button answer) {
        if (answer.getText().equals(currentQuestion.getRightAnswer())) {
            Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
            toPublish(); //publishs a topic because the answer is right
            return true;

        } else {
            Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.answer_a:
                checkAnswer(answerA);
                break;
            case R.id.answer_b:
                checkAnswer(answerB);
                break;
            case R.id.answer_c:
                checkAnswer(answerC);
                break;
            case R.id.answer_d:
                checkAnswer(answerD);
                break;
            case R.id.button_result:
                Toast.makeText(this, "show Result", Toast.LENGTH_SHORT).show();
                Intent gameToResult = new Intent(this, ResultActivity.class);
                startActivity(gameToResult);
                break;
        }
        if (questionsAsked < maxQuestionsToBeAnswered) {
            askNextQuestion();
            questionsAsked++;
        } else {
            Toast.makeText(this, "show Result", Toast.LENGTH_SHORT).show();
            Intent gameToResult = new Intent(this, ResultActivity.class);
            startActivity(gameToResult);
        }
    }
}
