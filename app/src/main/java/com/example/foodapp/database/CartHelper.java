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

public class CartHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Cart.db";

    public CartHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseTables.CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DatabaseTables.DELETE_TABLE_CART);
        onCreate(sqLiteDatabase);
    }

    /**
     * Insert a food object to the db
     *
     * @param food The object
     */
    public long insert(Food food) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Auxdata auxdata = food.getAuxdata();

        values.put(DatabaseTables.CART.COLUMN_NAME_ID, food.getID());
        values.put(DatabaseTables.CART.COLUMN_NAME_NAME, food.getName());
        values.put(DatabaseTables.CART.COLUMN_NAME_SIZE, food.getSize());
        values.put(DatabaseTables.CART.COLUMN_NAME_COST, food.getCost());
        values.put(DatabaseTables.CART.COLUMN_NAME_CATEGORY, food.getCategory());
        values.put(DatabaseTables.CART.COLUMN_NAME_DESCRIPTION, auxdata.getDescription());
        values.put(DatabaseTables.CART.COLUMN_NAME_IMG, auxdata.getImg());
        values.put(DatabaseTables.CART.COLUMN_NAME_TYPE, auxdata.getType());

        return db.insert(DatabaseTables.CART.TABLE_NAME, null, values);
    }

    /**
     * Get all the records in the database
     *
     * @return The list of records
     */
    public List<Food> getAll() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DatabaseTables.CART.TABLE_NAME, null, null, null, null, null, null);
        List<Food> favorites = new ArrayList<>();

        while (cursor.moveToNext()) {
            Auxdata auxdata = new Auxdata(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.CART.COLUMN_NAME_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.CART.COLUMN_NAME_IMG)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.CART.COLUMN_NAME_TYPE))
            );

            Food food = new Food(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.CART.COLUMN_NAME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.CART.COLUMN_NAME_NAME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTables.CART.COLUMN_NAME_SIZE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTables.CART.COLUMN_NAME_COST)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.CART.COLUMN_NAME_CATEGORY)),
                    auxdata,
                    cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseTables.CART.COLUMN_NAME_DB_ID))
            );
            favorites.add(food);
        }
        cursor.close();
        return favorites;
    }

    /**
     * Delete a record from the database
     *
     * @param id The id of the record to be deleted
     */
    public void delete(String id) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = DatabaseTables.CART.COLUMN_NAME_DB_ID + " LIKE ?";
        String[] selectionArgs = {id};

        db.delete(DatabaseTables.CART.TABLE_NAME, selection, selectionArgs);
    }

    /**
     * Get the total price of the items in the cart
     *
     * @return The total
     */
    public double getTotal() {
        SQLiteDatabase db = getReadableDatabase();

        double amount;

        String query = "SELECT SUM(" + DatabaseTables.CART.COLUMN_NAME_COST + ") FROM " + DatabaseTables.CART.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            amount = cursor.getDouble(0);
        } else {
            amount = 0;
        }

        cursor.close();

        return amount;
    }

    /**
     * Get the count of items in the database
     *
     * @return The count
     */
    public int getCount() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT COUNT(" + DatabaseTables.CART.COLUMN_NAME_ID + ") FROM " + DatabaseTables.CART.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        int amount;
        if (cursor.moveToFirst()) {
            amount = cursor.getInt(0);
        } else {
            amount = 0;
        }

        cursor.close();

        return amount;
    }

    /**
     * Clear the cart table
     */
    public void clear() {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(DatabaseTables.CART.TABLE_NAME, null, null);
    }
}
