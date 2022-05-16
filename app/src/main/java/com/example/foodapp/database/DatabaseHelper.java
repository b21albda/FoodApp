package com.example.foodapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodapp.data.Auxdata;
import com.example.foodapp.data.Food;

import java.util.ArrayList;
import java.util.List;

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


        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_ID, food.getID());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_NAME, food.getName());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_SIZE, food.getSize());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_COST, food.getCost());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_CATEGORY, food.getCategory());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_DESCRIPTION, auxdata.getDescription());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_IMG, auxdata.getImg());
        values.put(DatabaseTables.FAVORITES.COLUMN_NAME_TYPE, auxdata.getType());

        db.insert(DatabaseTables.FAVORITES.TABLE_NAME, null, values);
    }

    /**
     * Get all the entries in the database
     *
     * @return The list of entries
     */
    public List<Food> getAll() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DatabaseTables.FAVORITES.TABLE_NAME, null, null, null, null, null, null);
        List<Food> favorites = new ArrayList<>();

        while (cursor.moveToNext()) {
            Auxdata auxdata = new Auxdata(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.FAVORITES.COLUMN_NAME_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.FAVORITES.COLUMN_NAME_IMG)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.FAVORITES.COLUMN_NAME_TYPE))
            );

            Food food = new Food(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.FAVORITES.COLUMN_NAME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.FAVORITES.COLUMN_NAME_NAME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTables.FAVORITES.COLUMN_NAME_SIZE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTables.FAVORITES.COLUMN_NAME_COST)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.FAVORITES.COLUMN_NAME_CATEGORY)),
                    auxdata
            );
            favorites.add(food);
        }
        cursor.close();
        return favorites;
    }

    /**
     * Check if the database contains a record with a specific id
     *
     * @param id The id
     * @return If the record exists
     */
    public boolean exists(String id) {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {DatabaseTables.FAVORITES.COLUMN_NAME_ID};
        String selection = DatabaseTables.FAVORITES.COLUMN_NAME_ID + " =?";
        String[] selectionArgs = { id };
        String limit = "1";

        Cursor cursor = db.query(DatabaseTables.FAVORITES.TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public void delete(String id) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = DatabaseTables.FAVORITES.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = {id};

        db.delete(DatabaseTables.FAVORITES.TABLE_NAME, selection, selectionArgs);
    }
}
