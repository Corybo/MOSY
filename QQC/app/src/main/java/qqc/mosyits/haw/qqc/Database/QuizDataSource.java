package qqc.mosyits.haw.qqc.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.Questions.Question;

/**
 * Handles data for database
 */
public class QuizDataSource {
    private final Context context;
    private DatabaseHandler dbHandler;
    private SQLiteDatabase database;
    private Question questionCount;

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
     * Get specified question by its id
     *
     * @param id from question which will be fetched from database
     * @return question
     */
    public Question getQuestionById(long id) {
        Cursor cursor = database.query(DatabaseConstants.QUESTION_TABLE, DatabaseConstants.QUESTION_COLUMNS, DatabaseConstants.QUESTION_ID + " = " + String.valueOf(id), null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return null;
        }
        Question question = setQuestionInformation(cursor);
        cursor.close();
        return question;
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

    /**
     * Insert information from database into a question Object
     *
     * @param cursor from query
     * @return question
     */
    private Question setQuestionInformation(Cursor cursor) {
        Question question = new Question();
        question.setThisQuestionId(cursor.getLong(0));
        question.setQuestion(cursor.getString(1));
        question.setRightAnswer(cursor.getString(2));
        question.setAnswer1(cursor.getString(3));
        question.setAnswer2(cursor.getString(4));
        question.setAnswer3(cursor.getString(5));
        return question;
    }

    /**
     * Get count of questions in database
     *
     * @return count
     */
    public int getQuestionCount() {
        Cursor cursor = database.query(DatabaseConstants.QUESTION_TABLE, DatabaseConstants.QUESTION_COLUMNS, null, null, null, null, null);
        Toast.makeText(context, "QustionCount: " + cursor.getCount(), Toast.LENGTH_SHORT).show();
        return cursor.getCount();
    }
}
