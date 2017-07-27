package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by lilla on 26/07/17.
 */

public final class HabitContract {

    private HabitContract() {
    }

    public static class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_GYM = "gym";
        public static final String COLUMN_HABIT_EAT_FRUITS = "eat fruits";
        public static final String COLUMN_HABIT_NO_SUGAR = "no sugar";
        public static final String COLUMN_HABIT_VITAMINS_TAKEN = "vitamins taken";

        public static final String HABIT_VITAMIN_A = "vitamin a";
        public static final String HABIT_VITAMIN_B6 = "vitamin b6";
        public static final String HABIT_VITAMIN_C = "vitamin c";
        public static final int YES = 1;
        public static final int NO = 0;

    }
}
