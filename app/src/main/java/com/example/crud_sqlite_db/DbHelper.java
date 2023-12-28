package com.example.crud_sqlite_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "Products.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableProducts = "CREATE TABLE tbl_products(PRODUCT_ID VARCHAR(10), PRODUCT_NAME VARCHAR(30), PRODUCT_CATEGORY VARCHAR(20)," +
                "PRODUCT_QUANTITY VARCHAR(10), PRODUCT_AVAILABILITY VARCHAR(10), PRODUCT_PRICE INTEGER)";
        db.execSQL(createTableProducts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
