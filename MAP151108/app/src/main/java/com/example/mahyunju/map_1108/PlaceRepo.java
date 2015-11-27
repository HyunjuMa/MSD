package com.example.mahyunju.map_1108;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mahyunju on 23/11/15.
 */

// This is for Database queries and I referenced quite much of this part on the following website : http://instinctcoder.com/android-studio-sqlite-database-example/

public class PlaceRepo {
    private DBHelper dbHelper;

    public PlaceRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(MyPlaces place) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyPlaces.COLUMN_NAME_POINT, place.point);
        values.put(MyPlaces.COLUMN_NAME_CITY, place.city);
        values.put(MyPlaces.COLUMN_NAME_COMMENT, place.comment);
        //values.put(MyPlaces.COLUMN_NAME_IMAGE, place.image);
        values.put(MyPlaces.COLUMN_NAME_WISH, place.wish);

        // inserting a row
        long placeid = db.insert(MyPlaces.TABLE, null, values);
        db.close();
        return (int) placeid;
    }

    public ArrayList<HashMap<String,String>> getPlaceList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                MyPlaces.COLUMN_NAME_POINT + ", " +
                MyPlaces.COLUMN_NAME_CITY + ", " +
                MyPlaces.COLUMN_NAME_COMMENT + ", " +
                MyPlaces.COLUMN_NAME_WISH +
                " FROM " + MyPlaces.TABLE;
        ArrayList<HashMap<String, String>> placelist = new ArrayList<HashMap<String, String>>();

        Cursor cursor1 = db.rawQuery(selectQuery, null);

        if(cursor1.moveToFirst()) {
            do {
                HashMap<String, String> place = new HashMap<String, String>();
                place.put("point", cursor1.getString(cursor1.getColumnIndex(MyPlaces.COLUMN_NAME_POINT)));
                place.put("city", cursor1.getString(cursor1.getColumnIndex(MyPlaces.COLUMN_NAME_CITY)));
                place.put("comment", cursor1.getString(cursor1.getColumnIndex(MyPlaces.COLUMN_NAME_COMMENT)));
                place.put("wish", cursor1.getString(cursor1.getColumnIndex(MyPlaces.COLUMN_NAME_WISH)));
                placelist.add(place);
            } while (cursor1.moveToNext());
        }

        cursor1.close();
        db.close();
        return placelist;
}

    //title(name), latlng 반환하는 함수를 여기 말고 PlaceRepo에다가 넣어야 함!!!
  /*  public MyPlaces getMyPlacesFromDB() {
        // gets markers from DB using COLUMN_NAME_POINT / city,comment
        //dbHelper = new DBHelper;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT " +
                MyPlaces.COLUMN_NAME_POINT + ", " +
                MyPlaces.COLUMN_NAME_CITY + ", " +
                MyPlaces.COLUMN_NAME_COMMENT +
                " FROM " + MyPlaces.TABLE;

        Cursor cursor2 = db.rawQuery(selectQuery, null);

        if (cursor2.moveToFirst()) {
            do {
                String position_from_db = cursor2.getString(cursor2.getColumnIndex(MyPlaces.COLUMN_NAME_POINT));
                int index = position_from_db.indexOf(",");
                String point_lat = position_from_db.substring(0, index).trim();
                String point_lng = position_from_db.substring(index + 1).trim();

                Double point_lat2 = Double.parseDouble(point_lat);
                Double point_lng2 = Double.parseDouble(point_lng);

                LatLng MYPLACEPOSITION = new LatLng(point_lat2, point_lng2);
                String MYPLACETITLE = cursor2.getString(cursor2.getColumnIndex(MyPlaces.COLUMN_NAME_CITY));

            } while (cursor2.moveToNext());
        }
        cursor2.close();
        db.close();
        return placelist;
    }
*/

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


