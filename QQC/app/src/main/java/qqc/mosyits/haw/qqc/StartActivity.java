package qqc.mosyits.haw.qqc;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.Database.DatabaseHandler;
import qqc.mosyits.haw.qqc.Networking.ClientHandler;
import qqc.mosyits.haw.qqc.Networking.TimeHandler;
import qqc.mosyits.haw.qqc.Questions.QuestionInserts;
import qqc.mosyits.haw.qqc.Questions.QuestionSequence;

/**
 * StartActivity before the Games starts
 * Player can start or join a game
 */
@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int NOTIFICATION_ID = 42;
    private final boolean DEBUG = true;

    private ClientHandler handler;
    public static Player player;
    private TimeHandler.QQCCountDownTimer cdt;

    public enum GameStartStatus {READY, WAITING, BLOCKED}

    public enum Player {PLAYER_1, PLAYER_2}

    TimeHandler timeHandler = new TimeHandler(this); //TODO TEST

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button buttonStart = (Button) findViewById(R.id.button_start);
        buttonStart.setOnClickListener(this);

        Button btnTestTimer = (Button) findViewById(R.id.button_timer_test);
        btnTestTimer.setOnClickListener(this);

        handler = new ClientHandler(this);

        if (DEBUG) {
            deleteDatabase(new DatabaseHandler(this).DATABASE_NAME);
        }
        new QuestionInserts(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_timer_test: //TODO TEST
                Toast.makeText(this, String.valueOf(cdt.stop()), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_start:
                switch (ClientHandler.getStartStatus()) {
                    //READY = Bereit zu spielen, warten auf Gegenspieler
                    case READY:
                        //TODO: TEST
                        cdt = timeHandler.startTimer((TextView) findViewById(R.id.tv_timer));

                        Toast.makeText(this, "Start Game", Toast.LENGTH_SHORT).show();
                        //set PLAYER_1
                        player = Player.PLAYER_1;
                        //publish start
                        handler.toPublish(null, getString(R.string.pub_waiting_start));
                        //generate questionSequence and set it in ClientHandler
                        QuestionSequence questionSequence = new QuestionSequence(this);
                        handler.setQuestionSequence(questionSequence.getArrayList());
                        handler.sendQuestionNumber(0);
                        break;
                    //WAITING = Spieler 1 hat Spiel gestartet, Spieler 2 kann joinen
                    case WAITING:
                        player = Player.PLAYER_2;
                        handler.toPublish(null, getString(R.string.pub_started_join));
                        //Todo: "go" zum testen, wird letztendelich vom Raspberry geschickt
                        handler.toPublish(null, getString(R.string.msg_go));
                        break;
                    //BLOCKED = Fragen anzeigen, Kein weiterer Spieler kann joinen
                    case BLOCKED:
                        Toast.makeText(this, R.string.game_blocked, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
        }
    }
}
