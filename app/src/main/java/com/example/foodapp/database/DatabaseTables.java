package com.example.foodapp.database;

public class DatabaseTables {
    static class FAVORITES {
        static final String TABLE_NAME = "favorites";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_SIZE = "size";
        static final String COLUMN_NAME_COST = "cost";
        static final String COLUMN_NAME_CATEGORY = "category";
        static final String COLUMN_NAME_DESCRIPTION = "description";
        static final String COLUMN_NAME_IMG = "img";
        static final String COLUMN_NAME_TYPE = "type";
    }

    static final String CREATE_TABLE_FAVORITES =
            "CREATE TABLE " + FAVORITES.TABLE_NAME + " (" +
                    FAVORITES.COLUMN_NAME_ID + " Text PRIMARY KEY," +
                    FAVORITES.COLUMN_NAME_NAME + " TEXT,"+
                    FAVORITES.COLUMN_NAME_SIZE + " INT," +
                    FAVORITES.COLUMN_NAME_COST + " INT," +
                    FAVORITES.COLUMN_NAME_CATEGORY + " TEXT," +
                    FAVORITES.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    FAVORITES.COLUMN_NAME_IMG + " TEXT," +
                    FAVORITES.COLUMN_NAME_TYPE + " TEXT," +
                    "unique(" + FAVORITES.COLUMN_NAME_ID + "))";

    static final String DELETE_TABLE_FAVORITES =
            "DROP TABLE IF EXISTS " + FAVORITES.TABLE_NAME;
}