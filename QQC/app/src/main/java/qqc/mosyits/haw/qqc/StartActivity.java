package qqc.mosyits.haw.qqc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.Database.DatabaseHandler;
import qqc.mosyits.haw.qqc.Database.QuizDataSource;
import qqc.mosyits.haw.qqc.Questions.QuestionInserts;

/**
 * StartActivity before the Games starts
 * Player can start or join a game
 */
public class StartActivity extends AppCompatActivity {
    private final boolean DEBUG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (DEBUG) {
            deleteDatabase(new DatabaseHandler(this).DATABASE_NAME);
        }
        new QuestionInserts(this);
    }
}
