package qqc.mosyits.haw.qqc.Questions;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import qqc.mosyits.haw.qqc.Database.QuizDataSource;
import qqc.mosyits.haw.qqc.R;

/**
 * Generating the Array for Question order
 */

public class QuestionSequence {
    private final Context context;
    private int amountQuestionsInDatabase;
    private ArrayList<Integer> idList;

    public QuestionSequence(Context context){
        this.context = context;
        amountQuestionsInDatabase = questionCount();
        setUpIdList(amountQuestionsInDatabase);
    }

    /**
     * Fill array with values from 0 to how many questions are in the database and shuffle it
     *
     * @param questionCount amount of questions in database
     */
    private void setUpIdList(int questionCount) {
        idList = new ArrayList<>();
        for (int i = 0; i < questionCount; i++) {
            idList.add(i);
        }
        Collections.shuffle(idList);
    }
    /**
     * Get questionCount from database
     *
     * @return questionCount
     */
    private int questionCount() {
        QuizDataSource dataSource = new QuizDataSource(context);
        int count = 0;
        try {
            dataSource.open();
            count = dataSource.getQuestionCount();
            dataSource.close();
        } catch (Exception ex) {
            Toast.makeText(context, R.string.error_database, Toast.LENGTH_SHORT).show();
        }
        return count;
    }

    @Override
    public String toString() {
        String s= "";
        for (int i:idList) {
            s += (String.valueOf(i) + ",");
        }
        //String arrayAsString = "{" + s + "}";
       // int[] strAry = {"1","2","3"};
        return "#" + s; // arrayAsString;
    }
}
