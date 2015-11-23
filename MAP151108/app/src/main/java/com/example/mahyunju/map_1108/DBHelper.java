package com.example.mahyunju.map_1108;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mahyunju on 23/11/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myapp1.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + MyPlaces.TABLE + " (" +
                        MyPlaces.COLUMN_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MyPlaces.COLUMN_NAME_POINT + " TEXT, " +
                        MyPlaces.COLUMN_NAME_CITY + " TEXT, " +
                        MyPlaces.COLUMN_NAME_COMMENT + " TEXT, " +
                        MyPlaces.COLUMN_NAME_IMAGE + " INT, " +
                        MyPlaces.COLUMN_NAME_IMAGE_2 + " INT, " +
                        MyPlaces.COLUMN_NAME_IMAGE_3 + " INT )" ;
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onUpgrade(db, oldVersion, newVersion);
    }
}
