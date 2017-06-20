package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.Database.DatabaseHandler;
import qqc.mosyits.haw.qqc.Networking.ClientHandler;
import qqc.mosyits.haw.qqc.Questions.QuestionInserts;
import qqc.mosyits.haw.qqc.Questions.QuestionSequence;

/**
 * StartActivity before the Games starts
 * Player can start or join a game
 */
@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class StartActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {
    private static final int NOTIFICATION_ID = 42;
    private final boolean DEBUG = true;

    private Button buttonStart;
    private Button buttonJoin;
    private Button buttonSendNotification;
    private EditText editNotification;
    private ClientHandler handler;
    public enum GameStartStatus {READY, WAITING, BLOCKED}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        buttonStart = (Button) findViewById(R.id.button_start);
        buttonJoin = (Button) findViewById(R.id.button_join);
        buttonSendNotification = (Button) findViewById(R.id.button_send_notification);
        editNotification = (EditText) findViewById(R.id.edit_notification);

        buttonStart.setOnClickListener(this);
        buttonJoin.setOnClickListener(this);
        buttonSendNotification.setOnClickListener(this);
        editNotification.setOnEditorActionListener(this);

        handler = new ClientHandler(this.getApplicationContext());


        if (DEBUG) {
            deleteDatabase(new DatabaseHandler(this).DATABASE_NAME);
        }
        new QuestionInserts(this);
        Toast.makeText(this, "Version 3", Toast.LENGTH_SHORT).show();
    }

    /**
     * goes to GameActivity
     */
    public void toGameActivity() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                Toast.makeText(this, "Start Game", Toast.LENGTH_SHORT).show();
                switch (ClientHandler.getStartStatus()){
                    //READY = Bereit zu spielen, warten auf Gegenspieler
                    case READY:
                        handler.toPublish(editNotification, getString(R.string.pub_waiting));
                        handler.setPlayer(getString(R.string.player_1));
                        QuestionSequence questionSequence = new QuestionSequence(this);
//                        Toast.makeText(this, questionSequence.toString(), Toast.LENGTH_SHORT).show();
                        handler.toPublish(editNotification, questionSequence.toString());
//                        Toast.makeText(this, R.string.local_status_ready, Toast.LENGTH_SHORT).show();
                        break;
                    //WAITING = Spieler 1 hat Spiel gestartet, Spieler 2 kann joinen
                    case WAITING:
                        handler.toPublish(editNotification, getString(R.string.pub_started));
                        handler.setPlayer(getString(R.string.player_2));
                        Toast.makeText(this, R.string.local_status_waiting, Toast.LENGTH_SHORT).show();
                        break;
                    //BLOCKED = Fragen anzeigen, Kein weiterer Spieler kann joinen
                    case BLOCKED:
                        Toast.makeText(this, R.string.game_blocked, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
            case R.id.button_join:
                Toast.makeText(this, "Join Game", Toast.LENGTH_SHORT).show();
                toGameActivity();
                break;
            case R.id.button_send_notification:
                //TODO: Send notification
                handler.toPublish(editNotification, getString(R.string.msg_start_game));
        }
    }

    //performs a click on button_send_notification
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_GO){
            buttonSendNotification.performClick();
            return true;
        }
        return false;
    }
}
