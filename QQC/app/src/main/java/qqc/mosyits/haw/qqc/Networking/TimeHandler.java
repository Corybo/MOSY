package qqc.mosyits.haw.qqc.Networking;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import qqc.mosyits.haw.qqc.R;
import qqc.mosyits.haw.qqc.StartActivity;

/**
 * Handles timer issues of player1 and player22
 */

public class TimeHandler implements MessageObserver {
    private static final int MAX_TIME_IN_SECONDS = 20;
    private Context context;
    private int timePlayer1 = 0;
    private int timePlayer2 = 0;
    private ClientHandler clientHandler;

    public TimeHandler(Context context) {
        this.context = context;
        ClientHandler.addMessageObserver(this);
    }

    @Override
    public void updateMessage(String msg) {
        //only Player1 compares the time
        if (StartActivity.player == StartActivity.Player.PLAYER_1) {
            //Message time_[playerId]_[miliseconds]
            if (msg.startsWith(context.getString(R.string.time))) {
                String timeMessage = msg.substring(5);
                //Time Player 1
                if (timeMessage.equals("1")) {
                    timePlayer1 = Integer.valueOf(timeMessage.substring(7));
                }
                //Time Player 2
                else if (timeMessage.equals("2")) {
                    timePlayer2 = Integer.valueOf(timeMessage.substring(7));
                }
                sendMessageFasterPlayer();
            }
        }
    }

    /**
     * publishes Player1 or Player2 according to the faster one
     */
    private void sendMessageFasterPlayer() {
        if (timePlayer1 != 0 && timePlayer2 != 0) {
            StartActivity.Player fasterPlayer = compareTime(timePlayer1, timePlayer2);
            if (fasterPlayer != null) {
                if (fasterPlayer == StartActivity.Player.PLAYER_1) {
                    clientHandler.toPublish(null, context.getString(R.string.player_1));
                } else {
                    clientHandler.toPublish(null, context.getString(R.string.player_2));
                }
            } else {
                clientHandler.toPublish(null, context.getString(R.string.msg_tie));
            }
        }
    }

    /**
     * compares time the player needed to click the correct answer
     *
     * @param timePlayer1 time in ms of player 1
     * @param timePlayer2 time in ms of player 2
     * @return PLAYER1 if he was faster, Player 2 if he was faster, null if they had the same time
     */
    private StartActivity.Player compareTime(int timePlayer1, int timePlayer2) {
        if (timePlayer1 < timePlayer2) {
            return StartActivity.Player.PLAYER_1;
        } else if (timePlayer2 < timePlayer1) {
            return StartActivity.Player.PLAYER_2;
        } else {
            return null;
        }
    }

    //TODO: TEST
    public MyCountDownTimer startTimer(final TextView timerTextView) {
        MyCountDownTimer cdt = new MyCountDownTimer(MAX_TIME_IN_SECONDS * 1000, 1000, timerTextView);
        cdt = (MyCountDownTimer) cdt.start();
        return cdt;
    }
    public class MyCountDownTimer extends CountDownTimer{

        private final TextView timerTextView;
        private long millisUntilFinished;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView timerTextView) {
            super(millisInFuture, countDownInterval);
            this.timerTextView = timerTextView;
        }

        public void onTick(long millisUntilFinished) {
                this.millisUntilFinished = millisUntilFinished;
                timerTextView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public long stop(){
                long stoppedMillis = millisUntilFinished;
                cancel();
                return stoppedMillis;
            }


            public void onFinish() {
                timerTextView.setText("done!");
            }
        }

}
