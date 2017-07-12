package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import qqc.mosyits.haw.qqc.Networking.ClientHandler;
import qqc.mosyits.haw.qqc.Networking.MessageObserver;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener, MessageObserver {
    private final String TAG = getClass().getSimpleName();
    private Button buttonRestart;
    private int ratedNumberQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_result);
        int amountCorrectAnswers = getIntent().getExtras().getInt(GameActivity.AMOUNT_OF_CORRECT_ANSWERS);
        int totalNumberQuestions = ClientHandler.maxQuestionsToBeAnswered;

        ClientHandler.addMessageObserver(this);

        TextView txtAmountCorrectAnswers = (TextView) findViewById(R.id.amount_correct_answers);
        TextView txtRatedAnswers = (TextView) findViewById(R.id.rated_answers);
        txtAmountCorrectAnswers.setText(String.valueOf(amountCorrectAnswers) + " von " + String.valueOf(totalNumberQuestions) + " richtige Antworten");
        txtRatedAnswers.setText(String.valueOf(ratedNumberQuestions) + " von " + String.valueOf(amountCorrectAnswers) + " wurden gewertet");
        buttonRestart = (Button) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(this);
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
                //TODO: Modulo funktioniert?
                if (clientHandler.getRound() < 1) { //TODO: Anzahl ändern, je nachdem wie viele Fragen [Runden -1]
                    clientHandler.setRound((clientHandler.getRound() + 1));
                } else {
                    clientHandler.setRound(0);
                }
                Intent resultToStart = new Intent(this, StartActivity.class);
                startActivity(resultToStart);
                break;

        }
    }

    @Override
    public void updateMessage(String msg, ClientHandler clientHandler) {
        //set number of rated Questions/Answers
        if (msg.equals(getString(R.string.rated_answers1))) {
            if (StartActivity.player == StartActivity.Player.PLAYER_1) {
                ratedNumberQuestions = Integer.valueOf(msg.substring(6));
            }
        } else if (msg.equals(getString(R.string.rated_answers2))) {
            if (StartActivity.player == StartActivity.Player.PLAYER_2) {
                ratedNumberQuestions = Integer.valueOf(msg.substring(6));
            }
        }
    }
}
