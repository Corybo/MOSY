package qqc.mosyits.haw.qqc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.Arrays;
import java.util.Collections;

import qqc.mosyits.haw.qqc.Networking.ClientHandler;
import qqc.mosyits.haw.qqc.Networking.MessageObserver;
import qqc.mosyits.haw.qqc.Networking.ProgressTask;
import qqc.mosyits.haw.qqc.Networking.TimeHandler;
import qqc.mosyits.haw.qqc.Questions.Question;
import qqc.mosyits.haw.qqc.Questions.QuestionHandler;

import static qqc.mosyits.haw.qqc.Networking.ClientHandler.maxQuestionsToBeAnswered;
import static qqc.mosyits.haw.qqc.Networking.ClientHandler.removeMessageObserver;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, IMqttActionListener, MessageObserver {

    public static final String AMOUNT_OF_CORRECT_ANSWERS = "amount_correct_answers";
    private final String TAG = getClass().getSimpleName();
    private TextView questionField;
    private Button answerA;
    private Button answerB;
    private Button answerC;
    private Button answerD;
    private Question currentQuestion;
    private int questionsAsked = 0;
    private int correctAnswers = 0;
    private ClientHandler handler;
    private String player;
    private TimeHandler timeHandler;
    private TextView timer;
    private TimeHandler.QQCCountDownTimer cdt;
    private ProgressBar progressSpinner;
    private int colorRes;
    private Button clickedButton;
    private Vibrator vibrator;
    private int i = 0;
    public static final String WINNER_PLAYER_1 = "WINNER_PLAYER_1";
    public static final String WINNER_PLAYER_2 = "WINNER_PLAYER_2";
    public static final String WINNER_PLAYER_TIE = "WINNER_PLAYER_TIE";
    public static final String RATED_1 = "RATED_1";
    public static final String RATED_2 = "RATED_2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_game);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //add this class as observer
        ClientHandler.addMessageObserver(this);
        handler = ClientHandler.getClientHandler();
        timeHandler = new TimeHandler(this);

        questionField = (TextView) findViewById(R.id.question_field);
        answerA = (Button) findViewById(R.id.answer_a);
        answerB = (Button) findViewById(R.id.answer_b);
        answerC = (Button) findViewById(R.id.answer_c);
        answerD = (Button) findViewById(R.id.answer_d);

        timer = (TextView) findViewById(R.id.tv_timer);

        progressSpinner = (ProgressBar) findViewById(R.id.progress_spinner_game);
        new ProgressTask(this, progressSpinner);

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);

        //Player1 or Player2 as string
        //TODO: stürzt manchmal ab weil StartActivity.player = null
        if (StartActivity.player.equals(StartActivity.Player.PLAYER_1)) {
            player = getString(R.string.player_1);
            colorRes = R.color.colorPlayer1;
            getSupportActionBar().setTitle("QQC - " + player);
            setPlayerColor(colorRes);
        } else if (StartActivity.player.equals(StartActivity.Player.PLAYER_2)) {
            player = getString(R.string.player_2);
            getSupportActionBar().setTitle("QQC - " + player);
            colorRes = R.color.colorPlayer2;
            setPlayerColor(colorRes);
        }

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
        if (clickedButton != null) {
            clickedButton.setBackgroundResource(R.color.colorQuizButton);
        }
        setAllButtonsClickable(true);

        // Vibrate for 300 milliseconds
        vibrator.vibrate(300);
        //TODO: Time delay 5s
        if (questionsAsked < ClientHandler.maxQuestionsToBeAnswered) {
            Log.i(TAG, "askNextQuestion: maxQuestions noch nicht erreicht, questionAsked = " + questionsAsked + ", maxQuestions: " + maxQuestionsToBeAnswered);
            setQuestion(i++);
            questionsAsked++;
            //Start timer
            cdt = timeHandler.startTimer((TextView) findViewById(R.id.tv_timer), handler);
        } else {
            Log.i(TAG, "askNextQuestion: maxQuestions erreicht");

            if (StartActivity.player == StartActivity.Player.PLAYER_1) {
                int player1Rated = timeHandler.getPlayer1Rated();
                int player2Rated = timeHandler.getPlayer2Rated();
                handler.toPublish(null, returnWinner(player1Rated, player2Rated));
                handler.toPublish(null, getString(R.string.rated_answers1) + player1Rated);
                handler.toPublish(null, getString(R.string.rated_answers2) + player2Rated);

                handler.toPublish(null, getString(R.string.pub_end_game));
            }

            handler.toClose();
        }
    }

    /**
     * compare values, who answered the most questions right and fast
     *
     * @param player1Rated
     * @param player2Rated
     * @return
     */
    private String returnWinner(int player1Rated, int player2Rated) {
        //Player 2 won
        if (player1Rated < player2Rated) {
            return getString(R.string.winner_player2);
        }
        //Player 1 won
        else if (player1Rated > player2Rated) {
            return getString(R.string.winner_player1);
        }
        //tie
        else {
            return getString(R.string.winner_tie);
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
            Toast toast = Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT);
            toast.show();

            correctAnswers++;
        }
        //ANSWER FALSE
        else {
            //Wrong answer = Badest Time
            Log.i(TAG, "checkAnswer: wrongAnswer");
            Toast toast = Toast.makeText(this, getString(R.string.correct_answer) + ": " + currentQuestion.getRightAnswer(), Toast.LENGTH_LONG);
            toast.show();
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
        Log.i(TAG, "onClick: requiredTime = " + requiredTime);
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
    }

    /**
     * set QuizButttons clickable or not
     *
     * @param b true: clickable, false: unclickable
     */
    private void setAllButtonsClickable(boolean b) {
        answerA.setClickable(b);
        answerB.setClickable(b);
        answerC.setClickable(b);
        answerD.setClickable(b);
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        Log.i(TAG, "onSuccess - brauchen wir?");
        // We are connected
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Log.i(TAG, "onFailure - brauchen wir?");
        // Something went wrong e.g. connection timeout or firewall problems
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
        Intent gameToResult = new Intent(this, ResultActivity.class);
        //if message is go, then the next question is displayed
        if (msg.equalsIgnoreCase(getString(R.string.msg_go))) {
            Log.i(TAG, "updateMessage, message=" + msg);
            askNextQuestion();
        } else if (msg.equals(getString(R.string.pub_end_game))) {
            Log.i(TAG, "updateMessage: " + msg);
            gameToResult.putExtra(AMOUNT_OF_CORRECT_ANSWERS, correctAnswers);
            startActivity(gameToResult);
        } else if (msg.equals(getString(R.string.winner_player1))) {
            gameToResult.putExtra(WINNER_PLAYER_1, msg);
        } else if (msg.equals(getString(R.string.winner_player2))) {
            gameToResult.putExtra(WINNER_PLAYER_2, msg);
        } else if (msg.equals(getString(R.string.winner_tie))) {
            gameToResult.putExtra(WINNER_PLAYER_TIE, msg);
        } else if (msg.equals(getString(R.string.rated_answers1))) {
            gameToResult.putExtra(RATED_1, msg);
        } else if (msg.equals(getString(R.string.rated_answers2))) {
            gameToResult.putExtra(RATED_2, msg);
        }
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
        handler.toClose();
        removeMessageObserver(this);
        removeMessageObserver(timeHandler);
    }
}