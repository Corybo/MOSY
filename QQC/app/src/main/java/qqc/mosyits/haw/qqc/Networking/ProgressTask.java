package qqc.mosyits.haw.qqc.Networking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.StartActivity;

/**
 * Created by Mona on 04.07.2017.
 */
public class ProgressTask extends AsyncTask<Void, Void, String> {

    private String tag = getClass().getSimpleName();
    private Context context;
    private ProgressBar progressSpinner;
    private Boolean progress;


    public ProgressTask(Context context, ProgressBar progressSpinner) {
        this.context = context;
        this.progressSpinner = progressSpinner;
        progress = true;

        Toast.makeText(context, "Task startet", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Task PreExe", Toast.LENGTH_SHORT).show();
        super.onPreExecute();
        progressSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {
        // Toast.makeText(StartActivity.this, "Task InBackground", Toast.LENGTH_SHORT).show();
        Log.i(tag, "in Background");
        int i = 0;
        while (progress) {

            i++;
        }

        return "Progress beendet";
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, "Task Post", Toast.LENGTH_SHORT).show();
        super.onPostExecute(s);
        progressSpinner.setVisibility(View.INVISIBLE);
    }


    public Boolean getTaskProgress() {
        return progress;
    }

    public void setTaskProgress(Boolean progress) {
        this.progress = progress;
    }
}

