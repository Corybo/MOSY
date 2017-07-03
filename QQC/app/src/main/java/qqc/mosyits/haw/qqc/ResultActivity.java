package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int amountCorrectAnswers = getIntent().getExtras().getInt("AMOUNT_OF_CORRECT_ANSWERS");

        TextView txtAmountCorrectAnswers = (TextView) findViewById(R.id.amount_correct_answers);
        txtAmountCorrectAnswers.setText(String.valueOf(amountCorrectAnswers) + " richtige Antworten");
        buttonRestart = (Button) findViewById(R.id.button_restart);

        buttonRestart.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_restart:
                Intent resultToStart = new Intent(this, StartActivity.class);
                startActivity(resultToStart);
                break;

        }
    }
}
