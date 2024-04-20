package com.example.receti;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecipeDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Recipe.db";

    public static final String TABLE_RECIPE = "recipe";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ORIGIN = "origin";
    public static final String COLUMN_RECIPE = "recipe";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_RECIPE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_ORIGIN + " TEXT," +
                    COLUMN_RECIPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_RECIPE;

    public RecipeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
