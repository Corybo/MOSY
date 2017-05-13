package qqc.mosyits.haw.qqc.Questions;

import android.content.Context;

/**
 * Drawing up the questions for database
 */

public class QuestionInserts {
    private boolean alreadyAddedToDatabase = false;
    //QUESTION 1
    private static final String Q1 = "Welches Getränkemarke hat die Farbe rot";
    private static final String Q1AR = "CocaCola";
    private static final String Q1A1 = "Fanta";
    private static final String Q1A2 = "Sprite";
    private static final String Q1A3 = "Hella naturell";

    public QuestionInserts(Context context) {
        new Question(context, Q1, Q1AR, Q1A1, Q1A2, Q1A3);
        alreadyAddedToDatabase = true;
    }
}
