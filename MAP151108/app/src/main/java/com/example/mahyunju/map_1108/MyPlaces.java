package com.example.mahyunju.map_1108;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by mahyunju on 23/11/15.
 */
public class MyPlaces {
    public static final String TABLE = "MyPlaces";

    // table columns names
    public static final String COLUMN_ROWID = "id";
    public static final String COLUMN_NAME_POINT = "point";
//    public static final String COLUMN_NAME_POINT_LAT = "point_lat";
//    public static final String COLUMN_NAME_POINT_LNG = "point_lng";
    public static final String COLUMN_NAME_CITY = "city";
    public static final String COLUMN_NAME_COMMENT = "comment";
    public static final String COLUMN_NAME_IMAGE = "image";
    public static final String COLUMN_NAME_IMAGE_2 = "image2";
    public static final String COLUMN_NAME_IMAGE_3 = "image3";

    public int placeid;
    public String point;
    public double point_lat;
    public double point_lng;
    public LatLng point_latlng;
    public String city;
    public String comment;
    public int image;
    public int image2;
    public int image3;
}
