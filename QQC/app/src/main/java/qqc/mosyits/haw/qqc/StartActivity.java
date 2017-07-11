package qqc.mosyits.haw.qqc;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.Database.DatabaseHandler;
import qqc.mosyits.haw.qqc.Networking.ClientHandler;
import qqc.mosyits.haw.qqc.Networking.ProgressTask;
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
    private ProgressTask progressTask;
    private Button buttonStart;

    public enum GameStartStatus {READY, WAITING, BLOCKED}

    public enum Player {PLAYER_1, PLAYER_2}

    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_start);

        imageView = (ImageView)findViewById(R.id.imageView);
       // imageView.setMaxHeight(imageView.getWidth());
      // imageView.setLayoutParams(new ViewGroup.MarginLayoutParams(imageView.getWidth(), ViewGroup.LayoutParams.MATCH_PARENT));
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.propeller_01);

        buttonStart = (Button) findViewById(R.id.button_start);
        buttonStart.setOnClickListener(this);

        progressSpinner = (ProgressBar) findViewById(R.id.progress_spinner_start);
        progressTask = new ProgressTask(this, progressSpinner, imageView);

        handler = new ClientHandler(this);
        ClientHandler.setClientHandler(handler);

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
                        setPlayer(Player.PLAYER_1, R.string.player_1, R.color.colorPlayer1, R.color.colorPlayer2);
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
                        setPlayer(Player.PLAYER_2, R.string.player_2, R.color.colorPlayer2, R.color.colorPlayer1);
                        handler.toPublish(null, getString(R.string.pub_started_join));
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

    /**
     * sets player attributes
     * @param player Player_1 or Player_2
     * @param playerStringRes stringResource for each player
     * @param colorPlayerRes
     * @param colorSpinnerRes
     */
    private void setPlayer(Player player, int playerStringRes, int colorPlayerRes, int colorSpinnerRes) {
        getSupportActionBar().setTitle(playerStringRes);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(getString(colorPlayerRes)));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
       // buttonStart.setBackgroundResource(colorPlayerRes);
        buttonStart.setClickable(false);
        progressSpinner.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(this, colorSpinnerRes), PorterDuff.Mode.SRC_IN );
        progressTask.execute();
        this.player = player;
    }


    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
        handler.toClose();
        progressTask.setTaskProgress(false);
    }

}
