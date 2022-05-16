package com.example.foodapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodapp.data.Auxdata;
import com.example.foodapp.data.Food;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Favorites.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseTables.CREATE_TABLE_FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DatabaseTables.DELETE_TABLE_FAVORITES);
        onCreate(sqLiteDatabase);
    }

    /**
     * Insert a food object to the db
     *
     * @param food The object
     */
    public void insert(Food food) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Auxdata auxdata = food.getAuxdata();

        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_NAME, food.getName());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_SIZE, food.getSize());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_COST, food.getCost());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_CATEGORY, food.getCategory());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_DESCRIPTION, auxdata.getDescription());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_IMG, auxdata.getImg());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_TYPE, auxdata.getType());

        db.insert(DatabaseTables.FAVORITES.TABLE_NAME, null, values);
    }
}
