package com.example.mahyunju.map_1108;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mahyunju on 26/11/15.
 */
public class WishRepo {

    private DBHelper dbHelper;

    public WishRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(WishPlaces place) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WishPlaces.COLUMN_NAME_POINT, place.point);
        values.put(WishPlaces.COLUMN_NAME_CITY, place.city);

        // inserting a row
        long placeid = db.insert(WishPlaces.TABLE, null, values);
        db.close();
        return (int) placeid;
    }

    public ArrayList<HashMap<String,String>> getWishPlaceList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                WishPlaces.COLUMN_NAME_POINT + ", " +
                WishPlaces.COLUMN_NAME_CITY + ", " +
                " FROM " + WishPlaces.TABLE;
        ArrayList<HashMap<String, String>> placelist = new ArrayList<HashMap<String, String>>();

        Cursor cursor1 = db.rawQuery(selectQuery, null);

        if(cursor1.moveToFirst()) {
            do {
                HashMap<String, String> place = new HashMap<String, String>();
                place.put("point", cursor1.getString(cursor1.getColumnIndex(WishPlaces.COLUMN_NAME_POINT)));
                place.put("city", cursor1.getString(cursor1.getColumnIndex(WishPlaces.COLUMN_NAME_CITY)));
                placelist.add(place);
            } while (cursor1.moveToNext());
        }

        cursor1.close();
        db.close();
        return placelist;
    }
}
