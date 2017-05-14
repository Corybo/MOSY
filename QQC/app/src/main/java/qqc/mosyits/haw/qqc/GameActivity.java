package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private Button answerA;
    private Button answerB;
    private Button answerC;
    private Button answerD;
    private Button buttonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        answerA = (Button) findViewById(R.id.answer_a);
        answerB = (Button) findViewById(R.id.answer_b);
        answerC = (Button) findViewById(R.id.answer_c);
        answerD = (Button) findViewById(R.id.answer_d);
        buttonResult = (Button) findViewById(R.id.button_result);

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        buttonResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.answer_a:
                Toast.makeText(this, "A", Toast.LENGTH_SHORT).show();
                break;
            case R.id.answer_b:
                Toast.makeText(this, "B", Toast.LENGTH_SHORT).show();
                break;
            case R.id.answer_c:
                Toast.makeText(this, "C", Toast.LENGTH_SHORT).show();
                break;
            case R.id.answer_d:
                Toast.makeText(this, "D", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_result:
                Toast.makeText(this, "show Result", Toast.LENGTH_SHORT).show();
                Intent gameToResult = new Intent(this, ResultActivity.class);
                startActivity(gameToResult);
                break;
        }
    }
}
