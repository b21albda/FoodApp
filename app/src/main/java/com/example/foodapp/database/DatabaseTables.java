package com.example.foodapp.database;

public class DatabaseTables {
    public static class FAVORITES {
        public static final String TABLE_NAME = "favorites";
        static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SIZE = "size";
        public static final String COLUMN_NAME_COST = "cost";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMG = "img";
        public static final String COLUMN_NAME_TYPE = "type";
    }

    static final String CREATE_TABLE_FAVORITES =
            "CREATE TABLE " + FAVORITES.TABLE_NAME + " (" +
                    FAVORITES.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    FAVORITES.COLUMN_NAME_NAME + " TEXT,"+
                    FAVORITES.COLUMN_NAME_SIZE + " INT," +
                    FAVORITES.COLUMN_NAME_COST + " INT," +
                    FAVORITES.COLUMN_NAME_CATEGORY + " TEXT," +
                    FAVORITES.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    FAVORITES.COLUMN_NAME_IMG + " TEXT," +
                    FAVORITES.COLUMN_NAME_TYPE + " TEXT)";

    static final String DELETE_TABLE_FAVORITES =
            "DROP TABLE IF EXISTS " + FAVORITES.TABLE_NAME;
}