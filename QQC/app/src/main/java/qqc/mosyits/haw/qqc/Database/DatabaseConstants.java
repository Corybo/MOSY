package qqc.mosyits.haw.qqc.Database;

/**
 * Declaration of tables for database
 */
public class DatabaseConstants {
    //TABLE QUESTIONS
    public static final String QUESTION_TABLE = "question_table";
    //column names
    public static final String QUESTION_ID = "question_id";
    public static final String QUESTION = "question";
    public static final String RIGHT_ANSWER = "right_answer";
    public static final String ANSWER_1 = "answer_1";
    public static final String ANSWER_2 = "answer_2";
    public static final String ANSWER_3 = "answer_3";

    public static final String CREATE_QUESTION_TABLE = "CREATE TABLE " + QUESTION_TABLE + " (" +
            QUESTION_ID + " INTEGER NOT NULL CONSTRAINT question_pk PRIMARY KEY, " +
            QUESTION + " TEXT NOT NULL, " +
            RIGHT_ANSWER + " TEXT NOT NULL, " +
            ANSWER_1 + " TEXT NOT NULL, " +
            ANSWER_2 + " TEXT NOT NULL, " +
            ANSWER_3 + " TEXT NOT NULL); ";
    public static final String[] QUESTION_COLUMNS = {QUESTION_ID, QUESTION, RIGHT_ANSWER, ANSWER_1, ANSWER_2, ANSWER_3};
}
