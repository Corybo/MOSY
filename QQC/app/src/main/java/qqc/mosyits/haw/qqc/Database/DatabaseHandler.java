package qqc.mosyits.haw.qqc.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Creates and Upgrades the database
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "qqc_database";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.QUESTION_TABLE);
        db.execSQL(DatabaseConstants.CREATE_QUESTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
