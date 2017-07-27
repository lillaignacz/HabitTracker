package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittracker.data.HabitContract;
import com.example.android.habittracker.data.HabitDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper = new HabitDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void insertHabits(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_GYM, HabitContract.HabitEntry.YES);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_EAT_FRUITS, HabitContract.HabitEntry.YES);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NO_SUGAR, HabitContract.HabitEntry.NO);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_VITAMINS_TAKEN, HabitContract.HabitEntry.HABIT_VITAMIN_C);

        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
    }

    private void queryHabits(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_GYM,
                HabitContract.HabitEntry.COLUMN_HABIT_EAT_FRUITS,
                HabitContract.HabitEntry.COLUMN_HABIT_NO_SUGAR,
                HabitContract.HabitEntry.COLUMN_HABIT_VITAMINS_TAKEN
        };

        Cursor cursor = db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        List habitIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long habitId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(HabitContract.HabitEntry._ID));
            habitIds.add(habitId);
        }
        cursor.close();
    }
}
