package qqc.mosyits.haw.qqc.Questions;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.Database.QuizDataSource;

/**
 * Declaration of a Question and insert into database
 */

public class Question {
    private final String TAG = getClass().getSimpleName();
    private long thisQuestionId;
    private String rightAnswer;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;

    public Question(){

    }
    public Question(Context context, String question, String rightAnswer, String answer1, String answer2, String answer3, int id) {
        QuizDataSource dataSource = new QuizDataSource(context);
        thisQuestionId = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.rightAnswer = rightAnswer;
        Log.i(TAG, "Question: " + this.toString());

        //insert into database
        try {
            dataSource.open();
            dataSource.insertQuestion(this);
            dataSource.close();
        } catch (Exception ex) {
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public long getThisQuestionId() {
        return thisQuestionId;
    }

    public void setThisQuestionId(long questionId) {
        thisQuestionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    @Override
    public String toString() {
        return "Id: " + thisQuestionId + ", question: " + question;

    }
}
