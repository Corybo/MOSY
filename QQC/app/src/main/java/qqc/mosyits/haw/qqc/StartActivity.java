package qqc.mosyits.haw.qqc;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
    private final String TAG = getClass().getSimpleName();
    private final boolean DEBUG = true;

    private ClientHandler handler;
    public static Player player;
    private TimeHandler.QQCCountDownTimer cdt;

    private ProgressBar progressSpinner;
    private boolean progress = true;

    public enum GameStartStatus {READY, WAITING, BLOCKED}
    public enum Player {PLAYER_1, PLAYER_2}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_start);

        Button buttonStart = (Button) findViewById(R.id.button_start);
        buttonStart.setOnClickListener(this);

        progressSpinner = (ProgressBar) findViewById(R.id.progress_spinner);

        handler = new ClientHandler(this);

        if (DEBUG) {
            deleteDatabase(new DatabaseHandler(this).DATABASE_NAME);
        }
        new QuestionInserts(this);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick");
        switch (v.getId()) {
            case R.id.button_start:
                Log.i(TAG, "onClick: button_start");
                switch (ClientHandler.getStartStatus()) {
                    //READY = Bereit zu spielen, warten auf Gegenspieler
                    case READY:
                        Log.i(TAG, "onClick: button_start: READY");
                        new ProgressTask().execute();
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
                        Log.i(TAG, "onClick: button_start: WAITING");
                        progress = false;
                        player = Player.PLAYER_2;
                        handler.toPublish(null, getString(R.string.pub_started_join));
                        //TODO: go eigentlich vom Raspberry
//                        handler.toPublish(null, getString(R.string.msg_go));
                        break;
                    //BLOCKED = Fragen anzeigen, Kein weiterer Spieler kann joinen
                    case BLOCKED:
                        Log.i(TAG, "onClick: button_start: BLOCKED");
                        Toast.makeText(this, R.string.game_blocked, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
        handler.toClose();
    }

    public class ProgressTask extends AsyncTask<Void, Void, String> {

        private String tag = getClass().getSimpleName();

        public ProgressTask(){
            Toast.makeText(StartActivity.this, "Task startet", Toast.LENGTH_SHORT).show();
           // onPreExecute();
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(StartActivity.this, "Task PreExe", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
            progressSpinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
           // Toast.makeText(StartActivity.this, "Task InBackground", Toast.LENGTH_SHORT).show();
            Log.i(tag, "in Background");
            int i= 0;
            while(progress){

                i++;
            }

            return "Progress beendet";
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(StartActivity.this, "Task Post", Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
            progressSpinner.setVisibility(View.INVISIBLE);
        }
    }

}
