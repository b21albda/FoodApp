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
                    FAVORITES.COLUMN_NAME_TYPE + " TEXT)";

    static final String DELETE_TABLE_FAVORITES =
            "DROP TABLE IF EXISTS " + FAVORITES.TABLE_NAME;

    static class CART {
        static final String TABLE_NAME = "cart";
        static final String COLUMN_NAME_DB_ID = "db_id";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_SIZE = "size";
        static final String COLUMN_NAME_COST = "cost";
        static final String COLUMN_NAME_CATEGORY = "category";
        static final String COLUMN_NAME_DESCRIPTION = "description";
        static final String COLUMN_NAME_IMG = "img";
        static final String COLUMN_NAME_TYPE = "type";
    }

    static final String CREATE_TABLE_CART =
            "CREATE TABLE " + CART.TABLE_NAME + " (" +
                    CART.COLUMN_NAME_DB_ID + " INTEGER PRIMARY KEY," +
                    CART.COLUMN_NAME_ID + " Text," +
                    CART.COLUMN_NAME_NAME + " TEXT,"+
                    CART.COLUMN_NAME_SIZE + " INT," +
                    CART.COLUMN_NAME_COST + " INT," +
                    CART.COLUMN_NAME_CATEGORY + " TEXT," +
                    CART.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    CART.COLUMN_NAME_IMG + " TEXT," +
                    CART.COLUMN_NAME_TYPE + " TEXT)";

    static final String DELETE_TABLE_CART =
            "DROP TABLE IF EXISTS " + CART.TABLE_NAME;
}