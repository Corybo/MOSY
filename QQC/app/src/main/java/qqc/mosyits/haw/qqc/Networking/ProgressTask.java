package qqc.mosyits.haw.qqc.Networking;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.R;
import qqc.mosyits.haw.qqc.StartActivity;

/**
 * Class to execute ProgressSpinner
 */
public class ProgressTask extends AsyncTask<Void, Void, String> {
    private final String TAG = getClass().getSimpleName();
    private Context context;
    private ProgressBar progressSpinner;
    private boolean progress = true;
    private ImageView imageView;
    private AnimationDrawable animation;


    public ProgressTask(Context context, ProgressBar progressSpinner, ImageView imageView) {
        Log.i(TAG, "ProgressTask: constructor" + ", Progresstask=" + this.toString());
        this.context = context;
        this.progressSpinner = progressSpinner;
        this.imageView = imageView;
    }

    public ProgressTask(Context context, ProgressBar progressSpinner) {
        Log.i(TAG, "ProgressTask: constructor" + ", Progresstask=" + this.toString());
        this.context = context;
        this.progressSpinner = progressSpinner;
    }


    @Override
    protected void onPreExecute() {
        Log.i(TAG, "onPreExecute" + ", Progresstask=" + this.toString());
        super.onPreExecute();
        progress = true;
        playAnimation();
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.i(TAG, "doInBackground: progress = " + progress + ", Progresstask=" + this.toString());
        while (progress) {}
        Log.i(TAG, "doInBackground: progress stopped");
        return "Progress beendet";
    }

    @Override
    protected void onPostExecute(String s) {
        Log.i(TAG, "onPostExecute: String s=" + s + ", Progresstask=" + this.toString());
        super.onPostExecute(s);
        progressSpinner.setVisibility(View.INVISIBLE);
        cancel(true);
    }

    public void setTaskProgress(boolean progress) {
        Log.i(TAG, "setTaskProgress: " + progress + ", Progresstask=" + this.toString());
        this.progress = progress;
    }

    public void playAnimation() {
        imageView.setBackgroundResource(R.drawable.animation_propeller);
        animation = (AnimationDrawable) imageView.getBackground();
        animation.start();
    }


}

