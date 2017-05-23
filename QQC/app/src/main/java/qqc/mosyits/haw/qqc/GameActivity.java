package qqc.mosyits.haw.qqc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

import qqc.mosyits.haw.qqc.Questions.Question;
import qqc.mosyits.haw.qqc.Questions.QuestionHandler;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionField;
    private Button answerA;
    private Button answerB;
    private Button answerC;
    private Button answerD;
    private Button buttonResult;
    private Question currentQuestion;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionField = (TextView) findViewById(R.id.question_field);
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

        askNextQuestion();
    }

    /**
     * select next Question
     */
    private void askNextQuestion() {
        //TODO: Time delay 5s
        setQuestion(generateQuestionId());
    }

    /**
     * Setting the questions and their answers randomly on the buttons
     *
     * @param id
     */
    private void setQuestion(long id) {
        QuestionHandler questionHandler = new QuestionHandler(this);
        currentQuestion = questionHandler.getQuestionFromDb(id);
        questionField.setText(currentQuestion.getQuestion());
        String[] answers = {currentQuestion.getRightAnswer(), currentQuestion.getAnswer1(), currentQuestion.getAnswer2(), currentQuestion.getAnswer3()};
        Collections.shuffle(Arrays.asList(answers));
        answerA.setText(answers[0]);
        answerB.setText(answers[1]);
        answerC.setText(answers[2]);
        answerD.setText(answers[3]);
    }

    /**
     * Generate random number
     *
     * @return generated number for choosing question out of database
     */
    private long generateQuestionId() {
        //TODO: ZUFÄLLTIGE ZAHL je nach dem wie viele database entries es gibt generieren

        return id++;
    }

    /**
     * Check if answer is right or wrong
     *
     * @param answer Button which was clicked
     * @return true: right answer, false: wrong answer
     */
    private boolean checkAnswer(Button answer) {
        if (answer.getText().equals(currentQuestion.getRightAnswer())) {
            Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.answer_a:
                checkAnswer(answerA);
                break;
            case R.id.answer_b:
                checkAnswer(answerB);
                break;
            case R.id.answer_c:
                checkAnswer(answerC);
                break;
            case R.id.answer_d:
                checkAnswer(answerD);
                break;
            case R.id.button_result:
                Toast.makeText(this, "show Result", Toast.LENGTH_SHORT).show();
                Intent gameToResult = new Intent(this, ResultActivity.class);
                startActivity(gameToResult);
                break;
        }
        askNextQuestion();
    }
}
