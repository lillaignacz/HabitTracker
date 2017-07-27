package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.habittracker.data.HabitContract;
import com.example.android.habittracker.data.HabitDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button insertButton = (Button) findViewById(R.id.insert_button);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertHabits();
            }
        });

        final Button queryButton = (Button) findViewById(R.id.query_button);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryHabits();
            }
        });
        mDbHelper = new HabitDbHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void insertHabits(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_GYM, HabitContract.HabitEntry.YES);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_EAT_FRUITS, HabitContract.HabitEntry.YES);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NO_SUGAR, HabitContract.HabitEntry.NO);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_VITAMINS_TAKEN, HabitContract.HabitEntry.HABIT_VITAMIN_C);
        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);

        Log.v("MainActivity", "Inserting row id:" + newRowId);
    }

    private Cursor executeQuery(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_GYM,
                HabitContract.HabitEntry.COLUMN_HABIT_EAT_FRUITS,
                HabitContract.HabitEntry.COLUMN_HABIT_NO_SUGAR,
                HabitContract.HabitEntry.COLUMN_HABIT_VITAMINS_TAKEN
        };

        return db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }

    public void queryHabits() {
        Cursor cursor = executeQuery();
        List habitIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long habitId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(HabitContract.HabitEntry._ID));
            habitIds.add(habitId);

            Log.v("MainActivity", "Query habit id:" + habitId);
        }
        cursor.close();
    }
}
