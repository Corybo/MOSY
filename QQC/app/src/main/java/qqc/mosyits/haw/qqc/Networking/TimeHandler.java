package qqc.mosyits.haw.qqc.Networking;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import qqc.mosyits.haw.qqc.R;
import qqc.mosyits.haw.qqc.StartActivity;

/**
 * Handles timer issues of player1 and player22
 */

public class TimeHandler implements MessageObserver {
    private final String TAG = getClass().getSimpleName();
    private static final int MAX_TIME_IN_SECONDS = 20;
    private Context context;
    private int timePlayer1 = -1;
    private int timePlayer2 = -1;
    private int player1Rated;
    private int player2Rated;

    public TimeHandler(Context context) {
        Log.i(TAG, "TimeHandler: constructor");
        this.context = context;
        ClientHandler.addMessageObserver(this);
    }

    @Override
    public void updateMessage(String msg, ClientHandler clientHandler) {
        Log.i(TAG, "updateMessage: msg=" + msg);
        //only Player1 compares the time
        if (StartActivity.player == StartActivity.Player.PLAYER_1) {
            Log.i(TAG, "updateMessage: player1");
            //Message time_[playerId]_[miliseconds]
            if (msg.startsWith(context.getString(R.string.time))) {
                Log.i(TAG, "updateMessage: timeMessage");
                String timeMessage = msg.substring(5);
                //Time Player 1
                if (timeMessage.startsWith("1")) {
                    Log.i(TAG, "updateMessage: timeMessage player1");
                    timePlayer1 = Integer.valueOf(timeMessage.substring(2));
                }
                //Time Player 2
                else if (timeMessage.startsWith("2")) {
                    Log.i(TAG, "updateMessage: timeMessage player2");
                    timePlayer2 = Integer.valueOf(timeMessage.substring(2));
                }
                sendMessageFasterPlayer(clientHandler);
            }
        }
    }

    /**
     * publishes Player1 or Player2 according to the faster one
     *
     * @param clientHandler
     */
    private void sendMessageFasterPlayer(ClientHandler clientHandler) {
        Log.i(TAG, "sendMessageFasterPlayer");
        //Both players selected right answer or one of them
        if (((timePlayer1 > 0) && (timePlayer2 > 0)) || ((timePlayer1 == 0) && (timePlayer2 > 0)) || ((timePlayer1 > 0) && (timePlayer2 == 0))) {
            Log.i(TAG, "sendMessageFasterPlayer: timePlayer1=" + timePlayer1 + ", timePlayer2=" + timePlayer2);
            StartActivity.Player fasterPlayer = compareTime(timePlayer1, timePlayer2);
            if (fasterPlayer != null) {
                if (fasterPlayer == StartActivity.Player.PLAYER_1) {
                    Log.i(TAG, "sendMessageFasterPlayer: publish Player1");
                    clientHandler.toPublish(null, context.getString(R.string.player_1));
                    player1Rated++;
                } else {
                    Log.i(TAG, "sendMessageFasterPlayer: publish Player2");
                    clientHandler.toPublish(null, context.getString(R.string.player_2));
                    player2Rated++;
                }
            } else {
                Log.i(TAG, "sendMessageFasterPlayer: publish Gleichstand gleiche Punkte");
                clientHandler.toPublish(null, context.getString(R.string.msg_tie));
            }
            resetTime();
        }
        //Both players selected wrong answer or answered too slow
        else if ((timePlayer1 == 0) && (timePlayer2 == 0)) {
            Log.i(TAG, "sendMessageFasterPlayer: publish Gleichstand beide 0");
            clientHandler.toPublish(null, context.getString(R.string.msg_tie));
            resetTime();
        }
        //one player has not selected yet
        else {
            //do nothing
        }
    }

    /**
     * After each comparison the times of both players is reset to -1
     */
    private void resetTime() {
        Log.i(TAG, "resetTime");
        timePlayer1 = -1;
        timePlayer2 = -1;
    }

    /**
     * compares time the player needed to click the correct answer
     * time which is higher wins because it's a countdown
     *
     * @param timePlayer1 time in ms of player 1
     * @param timePlayer2 time in ms of player 2
     * @return PLAYER1 if he was faster, Player 2 if he was faster, null if they had the same time
     */
    private StartActivity.Player compareTime(int timePlayer1, int timePlayer2) {
        Log.i(TAG, "compareTime");
        if (timePlayer1 > timePlayer2) {
            Log.i(TAG, "compareTime: player1 > player 2");
            return StartActivity.Player.PLAYER_1;
        } else if (timePlayer2 > timePlayer1) {
            Log.i(TAG, "compareTime: player2 > player 1");
            return StartActivity.Player.PLAYER_2;
        } else {
            Log.i(TAG, "compareTime: Gleichstand");
            return null;
        }
    }

    /**
     * method to start countdown
     *
     * @param timerTextView textView which displays the countdown
     * @param clientHandler clientHandler from GameActivity
     * @return started instance of QQCCountDownTimer
     */
    public QQCCountDownTimer startTimer(final TextView timerTextView, ClientHandler clientHandler) {
        Log.i(TAG, "startTimer");
        QQCCountDownTimer cdt = new QQCCountDownTimer(MAX_TIME_IN_SECONDS * 1000, 1000, timerTextView, clientHandler);
        cdt = (QQCCountDownTimer) cdt.start();
        return cdt;
    }

    /**
     * Class QQCCountDownTimer extended from CountdownTimer to implement stop() method
     */
    public class QQCCountDownTimer extends CountDownTimer {

        private final TextView timerTextView;
        private final ClientHandler clientHandler;
        private long millisUntilFinished;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         * @param timerTextView
         * @param clientHandler
         */
        public QQCCountDownTimer(long millisInFuture, long countDownInterval, TextView timerTextView, ClientHandler clientHandler) {
            super(millisInFuture, countDownInterval);
            Log.i(TAG, "QQCCountDownTimer: constructor");
            this.timerTextView = timerTextView;
            this.clientHandler = clientHandler;
        }

        public void onTick(long millisUntilFinished) {
            this.millisUntilFinished = millisUntilFinished;
            timerTextView.setText("seconds remaining: " + millisUntilFinished / 1000);
        }

        /**
         * stops the countdown
         *
         * @return millis
         */
        public long stop() {
            Log.i(TAG, "stop: millis=" + millisUntilFinished);
            long stoppedMillis = millisUntilFinished;
            cancel();
            return stoppedMillis;
        }


        public void onFinish() {
            timerTextView.setText("done!");
            millisUntilFinished = 0;
            publishRequiredTime(millisUntilFinished, context, clientHandler);
        }
    }

    /**
     * publishes the time the current needed to answer the correct question
     *
     * @param requiredTime
     * @param context
     * @param clientHandler
     */
    public static void publishRequiredTime(long requiredTime, Context context, ClientHandler clientHandler) {
        Log.i("TimeHandler", "publishRequiredTime");
        String timeMessage = context.getString(R.string.time);
        if (StartActivity.player == StartActivity.Player.PLAYER_1) {
            Log.i("TimeHandler", "checkAnswer: publish Time for Player 1");
            clientHandler.toPublish(null, timeMessage + "_1_" + requiredTime);
        } else {
            Log.i("TimeHandler", "checkAnswer: publish Time for Player 2");
            clientHandler.toPublish(null, timeMessage + "_2_" + requiredTime);
        }
    }

    public int getPlayer1Rated() {
        return player1Rated;
    }

    public int getPlayer2Rated() {
        return player2Rated;
    }
}
