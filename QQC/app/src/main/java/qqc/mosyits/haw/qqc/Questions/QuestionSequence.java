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
    private static int maxQuestion = 10;
    private final Context context;
    private ArrayList<Integer> idList;
    private static int id = 0;
    //TODO 3: done
    private static int[] questionArray0 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static int[] questionArray1 = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    private static int[] questionArray2 = {20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
    private static int[] questionArray3 = {30, 31, 32, 33, 34, 35, 36, 37, 38, 39};
    private static int[] questionArray4 = {40, 41, 42, 43, 44, 45, 46, 47, 48, 49};
    private static int[] questionArray5 = {50, 51, 52, 53, 54, 55, 56, 57, 58, 59};
    private static int[] questionArray6 = {60, 61, 62, 63, 64, 65, 66, 67, 68, 69};
    private static int[] questionArray7 = {70, 71, 72, 73, 74, 75, 76, 77, 78, 79};


    public QuestionSequence(Context context) {
        //TODO: löschen,weil nicht benutzt
        this.context = context;
        idList = setUpIdList(questionCountDatabase());
    }

    //TODO 4: done
    public static int[] getIdArray(String questionIdString) {
        int[] questionArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        return  questionArray;
//        if (questionIdString.equals("#0")) {
//            return questionArray0;
//        }
//        else if (questionIdString.equals("#1")) {return questionArray1;}
//        else if (questionIdString.equals("#2")) return questionArray2;
//        else if (questionIdString.equals("#3")) return questionArray3;
//        else if (questionIdString.equals("#4")) return questionArray4;
//        else if (questionIdString.equals("#5")) return questionArray5;
//        else if (questionIdString.equals("#6")) return questionArray6;
//        else if (questionIdString.equals("#7")) return questionArray7;
//        else {
//            return null;
//        }
    }

    /**
     * Fill array with values from 0 to how many questions are in the database and shuffle it
     *
     * @param questionCount amount of questions in database
     */
    private ArrayList<Integer> setUpIdList(int questionCount) {
        idList = new ArrayList<>();
        for (int i = id; i < maxQuestion; i++) {
            idList.add(i);
        }
//        Collections.shuffle(idList);
        return idList;
    }

    /**
     * Get questionCountDatabase from database
     *
     * @return questionCountDatabase
     */
    private int questionCountDatabase() {
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

    public ArrayList<Integer> getArrayList() {
        return idList;
    }

    public static int getId() {
        return QuestionSequence.id;
    }

    public static void setId(int id) {
        QuestionSequence.id = id;
        maxQuestion = id + 10;
    }
}
