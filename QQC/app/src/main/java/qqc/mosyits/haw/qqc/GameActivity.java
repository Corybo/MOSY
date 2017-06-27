package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.Arrays;
import java.util.Collections;

import qqc.mosyits.haw.qqc.Networking.ClientHandler;
import qqc.mosyits.haw.qqc.Questions.Question;
import qqc.mosyits.haw.qqc.Questions.QuestionHandler;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, IMqttActionListener {

    private static final java.lang.String PLAYER_KEY = "player_key";
    private TextView questionField;
    private Button answerA;
    private Button answerB;
    private Button answerC;
    private Button answerD;
    private Button buttonResult;
    private Question currentQuestion;
    private int idSelect = 0;
    private int questionsAsked = 1;
    private int correctAnswers = 0;
    private ClientHandler handler;
    private String player;
    private int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        handler = new ClientHandler(this.getApplicationContext());

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


        player = getIntent().getExtras().getString(PLAYER_KEY);

    }
    /**
     * select next Question
     * @param questionId
     */
    public void askNextQuestion(int questionId) {
        //TODO: Time delay 5s
        if (questionsAsked < ClientHandler.maxQuestionsToBeAnswered) {
            setQuestion(questionId);
        } else {
            Toast.makeText(this, "show Result", Toast.LENGTH_SHORT).show();
            Intent gameToResult = new Intent(this, ResultActivity.class);
            gameToResult.putExtra("AMOUNT_OF_CORRECT_ANSWERS", correctAnswers);
            startActivity(gameToResult);
        }

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
        long questionId = ClientHandler.idList[idSelect++];
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
            correctAnswers++;
            handler.toPublish(answer, player); //publishs a topic because the answer is right
            handler.toPublish(null, getString(R.string.msg_go));
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
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        // We are connected
        Toast.makeText(GameActivity.this, "connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        // Something went wrong e.g. connection timeout or firewall problems
        Toast.makeText(GameActivity.this, "connection failed, Exception: " + exception.toString(), Toast.LENGTH_LONG).show();
    }
}
