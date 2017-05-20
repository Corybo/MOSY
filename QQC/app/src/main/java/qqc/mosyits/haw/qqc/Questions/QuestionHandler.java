package qqc.mosyits.haw.qqc.Questions;

import android.content.Context;
import android.widget.Toast;

import qqc.mosyits.haw.qqc.Database.DatabaseHandler;
import qqc.mosyits.haw.qqc.Database.QuizDataSource;
import qqc.mosyits.haw.qqc.R;

/**
 * This class is responisble for the current Question which is shown
 */

public class QuestionHandler {
    private final Context context;

    public QuestionHandler(Context context) {
        this.context = context;
    }

    public Question getQuestionFromDb(long id) {
        QuizDataSource dataSource = new QuizDataSource(context);
        Question question = new Question();
        try {
            dataSource.open();
            question = dataSource.getQuestionById(id);
            dataSource.close();
        } catch (Exception ex) {
            Toast.makeText(context, R.string.error_database + ": " + ex, Toast.LENGTH_SHORT).show();
        }
        return question;
    }
}
