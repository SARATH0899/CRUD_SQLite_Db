package com.example.crud_sqlite_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbAssist extends SQLiteOpenHelper {
    public DbAssist(@Nullable Context context) {
        super(context, "grocery.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUsers = "CREATE TABLE tbl_users(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(20), EMAIL VARCHAR(20)," +
                "MOBILE INTEGER, USERNAME VARCHAR(30), PASSWORD VARCHAR(30), CONFIRM_PASSWORD VARCHAR(20))";
        db.execSQL(createTableUsers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
