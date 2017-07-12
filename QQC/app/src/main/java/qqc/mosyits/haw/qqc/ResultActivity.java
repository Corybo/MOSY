package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import qqc.mosyits.haw.qqc.Networking.ClientHandler;
import qqc.mosyits.haw.qqc.Networking.MessageObserver;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();
    private Button buttonRestart;
    private int ratedNumberQuestions;
    private String winOrLose;
    private String player;
    private int colorRes;
    MediaPlayer mediaPlayer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_result);
        int amountCorrectAnswers = getIntent().getExtras().getInt(GameActivity.AMOUNT_OF_CORRECT_ANSWERS);
        int totalNumberQuestions = ClientHandler.maxQuestionsToBeAnswered;

        if (getIntent().getExtras().getString(GameActivity.WINNER_PLAYER_1) != null) {
            setWinnerValues(getIntent().getExtras().getString(GameActivity.WINNER_PLAYER_1));

        }
        if (getIntent().getExtras().getString(GameActivity.WINNER_PLAYER_2) != null) {

            setWinnerValues(getIntent().getExtras().getString(GameActivity.WINNER_PLAYER_2));
        }
        if (getIntent().getExtras().getString(GameActivity.WINNER_PLAYER_TIE) != null) {

            setWinnerValues(getIntent().getExtras().getString(GameActivity.WINNER_PLAYER_TIE));
        }
        if (getIntent().getExtras().getString(GameActivity.RATED_1) != null) {
            setWinnerValues(getIntent().getExtras().getString(GameActivity.RATED_1));

        }
        if (getIntent().getExtras().getString(GameActivity.RATED_2) != null) {
            setWinnerValues(getIntent().getExtras().getString(GameActivity.RATED_2));

        }


        // für raw auf res new resource dictionary type : raw
        //https://www.youtube.com/watch?v=RSi959Xyw-Q
        mediaPlayer = MediaPlayer.create(this, R.raw.cheers_applause);
        mediaPlayer.start();

        TextView txtAmountCorrectAnswers = (TextView) findViewById(R.id.amount_correct_answers);
        TextView txtRatedAnswers = (TextView) findViewById(R.id.rated_answers);
        txtAmountCorrectAnswers.setText(String.valueOf(amountCorrectAnswers) + " von " + String.valueOf(totalNumberQuestions) + " richtige Antworten");
        TextView txtWinOrLoose = (TextView) findViewById(R.id.status);
        txtWinOrLoose.setText(winOrLose);
        txtRatedAnswers.setText(String.valueOf(ratedNumberQuestions) + " von " + String.valueOf(amountCorrectAnswers) + " wurden gewertet");
        buttonRestart = (Button) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(this);

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
    }

    //set activityColor for each player
    private void setPlayerColor(int colorRes) {
        ColorDrawable colDraw = new ColorDrawable(Color.parseColor(getString(colorRes)));
        getSupportActionBar().setBackgroundDrawable(colDraw);
        buttonRestart.setBackgroundResource(colorRes);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick");
        switch (v.getId()) {
            case R.id.button_restart:
                Log.i(TAG, "onClick: restart");
                ClientHandler.setStartStatus(StartActivity.GameStartStatus.READY);
                ClientHandler clientHandler = ClientHandler.getClientHandler();
                clientHandler.toClose();
                clientHandler.setI(0);
                if (clientHandler.getRound() < 1) { //TODO: Anzahl ändern, je nachdem wie viele Fragen [Runden -1]
                    clientHandler.setRound((clientHandler.getRound() + 1));
                } else {
                    clientHandler.setRound(0);
                }
                Intent resultToStart = new Intent(this, StartActivity.class);
                startActivity(resultToStart);
                finish();
                break;

        }
    }

    public void setWinnerValues(String msg) {
        //set number of rated Questions/Answers
        if (msg.equals(getString(R.string.rated_answers1))) {
            if (StartActivity.player == StartActivity.Player.PLAYER_1) {
                ratedNumberQuestions = Integer.valueOf(msg.substring(6));
                Log.i(TAG, "updateMessage: setNumberRated_answers1 " + ratedNumberQuestions);
            }
        } else if (msg.equals(getString(R.string.rated_answers2))) {
            if (StartActivity.player == StartActivity.Player.PLAYER_2) {
                ratedNumberQuestions = Integer.valueOf(msg.substring(6));
                Log.i(TAG, "updateMessage: setNumberRated_answers2 " + ratedNumberQuestions);
            }
        }
        //show if player won or lost
        else if (msg.equals(getString(R.string.winner_tie))) {
            winOrLose = "Gleichstand!";
            Log.i(TAG, "updateMessage: who Won " + msg.substring(3));
        } else if (msg.equals(getString(R.string.winner_player1))) {
            if (StartActivity.player == StartActivity.Player.PLAYER_1) {
                winOrLose = "Gewonnen!";
                Log.i(TAG, "updateMessage: who Won " + msg.substring(3));
            } else if (StartActivity.player == StartActivity.Player.PLAYER_2) {
                winOrLose = "Verloren!";
                Log.i(TAG, "updateMessage: who Won " + msg.substring(3));
            }
        } else if (msg.equals(getString(R.string.winner_player2))) {
            if (StartActivity.player == StartActivity.Player.PLAYER_2) {
                winOrLose = "Gewonnen!";
                Log.i(TAG, "updateMessage: who Won " + msg.substring(3));
            } else if (StartActivity.player == StartActivity.Player.PLAYER_1) {
                winOrLose = "Verloren!";
                Log.i(TAG, "updateMessage: who Won " + msg.substring(3));
            }

        }
    }
}
