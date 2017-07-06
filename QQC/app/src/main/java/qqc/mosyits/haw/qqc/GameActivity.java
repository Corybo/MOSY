package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;

import qqc.mosyits.haw.qqc.Networking.ClientHandler;
import qqc.mosyits.haw.qqc.Networking.MessageObserver;
import qqc.mosyits.haw.qqc.Networking.ProgressTask;
import qqc.mosyits.haw.qqc.Networking.TimeHandler;
import qqc.mosyits.haw.qqc.Questions.Question;
import qqc.mosyits.haw.qqc.Questions.QuestionHandler;

import static qqc.mosyits.haw.qqc.Networking.ClientHandler.QUESTION_KEY;
import static qqc.mosyits.haw.qqc.Networking.ClientHandler.maxQuestionsToBeAnswered;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, IMqttActionListener, MessageObserver {

    private final String TAG = getClass().getSimpleName();
    private TextView questionField;
    private Button answerA;
    private Button answerB;
    private Button answerC;
    private Button answerD;
    private Question currentQuestion;
    private int questionsAsked = 1;
    private int correctAnswers = 0;
    private ClientHandler handler;
    private String player;
    private int questionId;
    private TimeHandler timeHandler;
    private TextView timer;
    private TimeHandler.QQCCountDownTimer cdt;
    private ProgressBar progressSpinner;
    private ProgressTask progressTask;
    private boolean firstTime = true;
    private int colorRes;
    private Button clickedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_game);

        //add this class as observer
        ClientHandler.addMessageObserver(this);

//        handler = new ClientHandler(this.getApplicationContext());
        handler = ClientHandler.getClientHandler();
        timeHandler = new TimeHandler(this);

        questionField = (TextView) findViewById(R.id.question_field);
        answerA = (Button) findViewById(R.id.answer_a);
        answerB = (Button) findViewById(R.id.answer_b);
        answerC = (Button) findViewById(R.id.answer_c);
        answerD = (Button) findViewById(R.id.answer_d);

        timer = (TextView) findViewById(R.id.tv_timer);

        progressSpinner = (ProgressBar) findViewById(R.id.progress_spinner_game);
        progressTask = new ProgressTask(this, progressSpinner);

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);

        //Player1 or Player2 as string
        //TODO: stürzt manchmal ab weil StartActivity.player = null
        if (StartActivity.player.equals(StartActivity.Player.PLAYER_1)) {
            player = getString(R.string.player_1);
            colorRes = R.color.colorPlayer1;
            setPlayerColor(colorRes);
        } else if (StartActivity.player.equals(StartActivity.Player.PLAYER_2)) {
            player = getString(R.string.player_2);
            colorRes = R.color.colorPlayer2;
            setPlayerColor(colorRes);
        }

        //TODO: TEST updateMessage
        updateMessage(getIntent().getExtras().getString(QUESTION_KEY), handler);
        updateMessage(getString(R.string.msg_go), handler);
    }

    //set activityColor for each player
    private void setPlayerColor(int colorRes) {
        timer.setTextColor(colorRes);
        ColorDrawable colDraw = new ColorDrawable(Color.parseColor(getString(colorRes)));
        getSupportActionBar().setBackgroundDrawable(colDraw);
    }

    /**
     * select next Question
     */
    private void askNextQuestion() {
        Log.i(TAG, "askNextQuestion");
        //TODO: Time delay 5s
        if (questionsAsked < ClientHandler.maxQuestionsToBeAnswered) {
            Log.i(TAG, "askNextQuestion: maxQuestions noch nicht erreicht, questionAsked = " + questionsAsked + ", maxQuestions: " + maxQuestionsToBeAnswered);
            setQuestion(questionId);
            questionsAsked++;
            //Start timer
            cdt = timeHandler.startTimer((TextView) findViewById(R.id.tv_timer), handler);
        } else {
            Log.i(TAG, "askNextQuestion: maxQuestions erreicht");
            handler.toClose();
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
        Log.i(TAG, "setQuestion");
        QuestionHandler questionHandler = new QuestionHandler(this);
        currentQuestion = questionHandler.getQuestionFromDb(id);
        if (currentQuestion != null) {
            Log.i(TAG, "setQuestion: currentQuestion != null");
            questionField.setText(currentQuestion.getQuestion());
            String[] answers = {currentQuestion.getRightAnswer(), currentQuestion.getAnswer1(), currentQuestion.getAnswer2(), currentQuestion.getAnswer3()};
            Collections.shuffle(Arrays.asList(answers));
            answerA.setText(answers[0]);
            answerB.setText(answers[1]);
            answerC.setText(answers[2]);
            answerD.setText(answers[3]);
        } else {
            Log.i(TAG, "setQuestion: currentQuestion = null");
            //TODO: Fehler beheben, wenn currentQuestion = null
            Toast.makeText(this, "currentQuestion = null", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Check if answer is right or wrong
     *
     * @param answer       Button which was clicked
     * @param requiredTime
     * @return true: right answer, false: wrong answer
     */
    private void checkAnswer(Button answer, long requiredTime) {
        clickedButton = answer;
        clickedButton.setBackgroundResource(colorRes);

        //ANSWER CORRECT
        Log.i(TAG, "checkAnswer");
        if (answer.getText().equals(currentQuestion.getRightAnswer())) {
            Log.i(TAG, "checkAnswer: correctAnswer");
            correctAnswers++;
        }
        //ANSWER FALSE
        else {
            //Wrong answer = Badest Time
            Log.i(TAG, "checkAnswer; wrongAnswer");
            requiredTime = 0;
        }
        //publish message with required time
        TimeHandler.publishRequiredTime(requiredTime, this, handler);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick");
        //stop timer and get required Time
        long requiredTime = cdt.stop();
        Toast.makeText(this, String.valueOf(requiredTime), Toast.LENGTH_SHORT).show();
        switch (v.getId()) {
            case R.id.answer_a:
                Log.i(TAG, "onClick: answer_a");
                checkAnswer(answerA, requiredTime);
                break;
            case R.id.answer_b:
                Log.i(TAG, "onClick: answer_b");
                checkAnswer(answerB, requiredTime);
                break;
            case R.id.answer_c:
                Log.i(TAG, "onClick: answer_c");
                checkAnswer(answerC, requiredTime);
                break;
            case R.id.answer_d:
                Log.i(TAG, "onClick: answer_d");
                checkAnswer(answerD, requiredTime);
                break;
        }

        //buttons unclickable
        setAllButtonsClickable(false);



        //TODO: FEHLERBEHEBUNG PROGRESS SPINNER
        //start Task to show progressSpinner
//        if (progressTask.isCancelled()) {
//            Log.i(TAG, "onClick: Progresstask cancelled");
//            progressTask.execute();
//        }else{
//            Log.i(TAG, "onClick: Progresstask not cancelled");
//            progressTask.setTaskProgress(true);
//        }
    }

    private void setAllButtonsClickable(boolean b) {
        answerA.setClickable(b);
        answerB.setClickable(b);
        answerC.setClickable(b);
        answerD.setClickable(b);
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        //TODO: Brauchen wir das?
        Log.i(TAG, "onSuccess - brauchen wir?");
        // We are connected
//        Toast.makeText(GameActivity.this, "connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        //TODO: Brauchen wir das?
        Log.i(TAG, "onFailure - brauchen wir?");
        // Something went wrong e.g. connection timeout or firewall problems
//        Toast.makeText(GameActivity.this, "connection failed, Exception: " + exception.toString(), Toast.LENGTH_LONG).show();
    }

    /**
     * get messages from ClientHandler when they arrive
     *
     * @param msg           message that is lately arrived
     * @param clientHandler
     */
    @Override
    public void updateMessage(String msg, ClientHandler clientHandler) {
        Log.i(TAG, "updateMessage");
        //if message starts with #, it is the number for the next question and saved as questionId
        if (msg.startsWith("#")) {
            Log.i(TAG, "updateMessage, message=" + msg);
            String strId = msg.substring(1);
            questionId = Integer.valueOf(strId);
        }
        //if message is go, then the next question is displayed
        else if (msg.equalsIgnoreCase(getString(R.string.msg_go))) {
            if(clickedButton !=null) {
                clickedButton.setBackgroundResource(R.color.colorQuizButton);
            }
            setAllButtonsClickable(true);
            Log.i(TAG, "updateMessage, message=" + msg);
            if(!firstTime) {
                progressTask.setTaskProgress(false);
            }else{
                firstTime = false;
            }
            askNextQuestion();
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
        handler.toClose();
    }
}