package qqc.mosyits.haw.qqc.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import qqc.mosyits.haw.qqc.Questions.Question;

/**
 * Handles data for database
 */
public class QuizDataSource {
    private final Context context;
    private DatabaseHandler dbHandler;
    private SQLiteDatabase database;

    public QuizDataSource(Context context) {
        dbHandler = new DatabaseHandler(context);
        this.context = context;
    }

    /**
     * Opens a writable Database
     *
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHandler.getWritableDatabase();
    }

    /**
     * closes the database
     */
    public void close() {
        dbHandler.close();
    }

    /**
     * @param question which will be inserted into database
     */
    public void insertQuestion(Question question) {
        database.insert(DatabaseConstants.QUESTION_TABLE, null, questionValues(question));
    }

    /**
     * gets the attributes of the question which will be inserted into database
     *
     * @param question which will be inserted
     * @return questionValues for insertion into database
     */
    private ContentValues questionValues(Question question) {
        ContentValues questionValues = new ContentValues();
        questionValues.put(DatabaseConstants.QUESTION_ID, question.getThisQuestionId());
        questionValues.put(DatabaseConstants.QUESTION, question.getQuestion());
        questionValues.put(DatabaseConstants.RIGHT_ANSWER, question.getRightAnswer());
        questionValues.put(DatabaseConstants.ANSWER_1, question.getAnswer1());
        questionValues.put(DatabaseConstants.ANSWER_2, question.getAnswer2());
        questionValues.put(DatabaseConstants.ANSWER_3, question.getAnswer3());
        return questionValues;
    }
}
