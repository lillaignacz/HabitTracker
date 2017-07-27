package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lilla on 26/07/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "habits";

    private static final String SQL_CREATE_HABITS_TABLE =
            "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " (" +
                    HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    HabitContract.HabitEntry.COLUMN_HABIT_GYM + " INTEGER," +
                    HabitContract.HabitEntry.COLUMN_HABIT_EAT_FRUITS + "INTEGER" +
                    HabitContract.HabitEntry.COLUMN_HABIT_NO_SUGAR + "INTEGER" +
                    HabitContract.HabitEntry.COLUMN_HABIT_VITAMINS_TAKEN + "TEXT" +
                    ")";

    private static final String SQL_DELETE_HABITS_TABLE =
            "DROP TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_HABITS_TABLE);
        onCreate(db);
    }
}
