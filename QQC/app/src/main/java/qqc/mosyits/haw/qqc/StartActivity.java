package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.Database.DatabaseHandler;
import qqc.mosyits.haw.qqc.Questions.QuestionInserts;

/**
 * StartActivity before the Games starts
 * Player can start or join a game
 */
public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    private final boolean DEBUG = true;

    private Button buttonStart;
    private Button buttonJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        buttonStart = (Button) findViewById(R.id.button_start);
        buttonJoin = (Button) findViewById(R.id.button_join);

        buttonStart.setOnClickListener(this);
        buttonJoin.setOnClickListener(this);

        if (DEBUG) {
            deleteDatabase(new DatabaseHandler(this).DATABASE_NAME);
        }
        new QuestionInserts(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                Toast.makeText(this, "Start Game", Toast.LENGTH_SHORT).show();
                toGameActivity();
                break;
            case R.id.button_join:
                Toast.makeText(this, "Join Game", Toast.LENGTH_SHORT).show();
                toGameActivity();
                break;
        }
    }

    //goes to GameActivity
    public void toGameActivity() {
        Intent startToGame = new Intent(this, GameActivity.class);
        startActivity(startToGame);
    }
}
