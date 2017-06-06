package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.os.Bundle;
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

/**
 * StartActivity before the Games starts
 * Player can start or join a game
 */
public class StartActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {
    private static final int NOTIFICATION_ID = 42;
    private final boolean DEBUG = true;

    private Button buttonStart;
    private Button buttonJoin;
    private Button buttonSendNotification;
    private EditText editNotification;
    private ClientHandler clientHandler;

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

        clientHandler = new ClientHandler(this);

        if (DEBUG) {
            deleteDatabase(new DatabaseHandler(this).DATABASE_NAME);
        }
        new QuestionInserts(this);
    }

    /**
     * goes to GameActivity
     */
    public void toGameActivity() {
        Intent startToGame = new Intent(this, GameActivity.class);
        startActivity(startToGame);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                Toast.makeText(this, "Start Game", Toast.LENGTH_SHORT).show();
                if(clientHandler.startMessageSent()){
                    clientHandler.toPublish(editNotification, R.string.msg_join_game);
                }else{
                    clientHandler.toPublish(editNotification, R.string.msg_start_game);
                }
//                toGameActivity();
                break;
            case R.id.button_join:
                Toast.makeText(this, "Join Game", Toast.LENGTH_SHORT).show();
                toGameActivity();
                break;
            case R.id.button_send_notification:
                //TODO: Send notification
                clientHandler.toPublish(editNotification, R.string.msg_start_game);
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
