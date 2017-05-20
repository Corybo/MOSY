package qqc.mosyits.haw.qqc.Questions;

import android.content.Context;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.Database.QuizDataSource;

/**
 * Declaration of a Question and insert into database
 */

public class Question {
    private static long questionId = 0;
    private long thisQuestionId;
    private String rightAnswer;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;

    public Question(){

    }
    public Question(Context context, String question, String rightAnswer, String answer1, String answer2, String answer3) {
        QuizDataSource dataSource = new QuizDataSource(context);
        thisQuestionId = questionId;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.rightAnswer = rightAnswer;

        //insert into database
        try {
            dataSource.open();
            dataSource.insertQuestion(this);
            dataSource.close();
        } catch (Exception ex) {
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
        questionId++;
    }

    public long getThisQuestionId() {
        return thisQuestionId;
    }

    public static void setThisQuestionId(long questionId) {
        Question.questionId = questionId;
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
}
