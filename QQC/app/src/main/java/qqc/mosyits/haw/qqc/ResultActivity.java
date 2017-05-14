package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        buttonRestart = (Button) findViewById(R.id.button_restart);

        buttonRestart.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_restart:
                Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show();
                Intent resultToStart = new Intent(this, StartActivity.class);
                startActivity(resultToStart);
                break;

        }
    }
}
