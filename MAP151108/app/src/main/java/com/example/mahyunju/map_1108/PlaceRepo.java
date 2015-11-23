package com.example.mahyunju.map_1108;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mahyunju on 23/11/15.
 */
public class PlaceRepo {
    private DBHelper dbHelper;

    public PlaceRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(MyPlaces place) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyPlaces.COLUMN_NAME_CITY, place.city);
        values.put(MyPlaces.COLUMN_NAME_COMMENT, place.comment);
        values.put(MyPlaces.COLUMN_NAME_IMAGE, place.image);

        // inserting a row
        long placeid = db.insert(MyPlaces.TABLE, null, values);
        db.close();
        return (int) placeid;
    }

    public ArrayList<HashMap<String,String>> getPlaceList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                MyPlaces.COLUMN_ROWID + ", " +
                MyPlaces.COLUMN_NAME_CITY + ", " +
                MyPlaces.COLUMN_NAME_COMMENT +
                " FROM " + MyPlaces.TABLE;
        ArrayList<HashMap<String, String>> placelist = new ArrayList<HashMap<String, String>>();

        Cursor cursor1 = db.rawQuery(selectQuery, null);

        if(cursor1.moveToFirst()) {
            do {
                HashMap<String, String> place = new HashMap<String, String>();
                place.put("city", cursor1.getString(cursor1.getColumnIndex(MyPlaces.COLUMN_NAME_CITY)));
                place.put("comment", cursor1.getString(cursor1.getColumnIndex(MyPlaces.COLUMN_NAME_COMMENT)));
                placelist.add(place);
            } while (cursor1.moveToNext());
        }

        cursor1.close();
        db.close();
        return placelist;
}

    /*
    public MyPlaces getPlaceById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                MyPlaces.COLUMN_ROWID + ", " +
                MyPlaces.COLUMN_NAME_CITY + ", " +
                MyPlaces.COLUMN_NAME_COMMENT +
                " FROM " + MyPlaces.TABLE +
                " WHERE " + MyPlaces.COLUMN_ROWID + "=?" ;
        int iCount = 0;
        MyPlaces place = new MyPlaces();

        Cursor cursor1 = db.rawQuery(selectQuery, new String[] {String.valueOf(Id)} );
        if (cursor1.moveToFirst()) {
            do {
                place.city = cursor1.getString(cursor1.getColumnIndex(MyPlaces.COLUMN_NAME_CITY));
                place.comment = cursor1.getString(cursor1.getColumnIndex(MyPlaces.COLUMN_NAME_COMMENT));
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        db.close();
        return place;
    }

    */
}


