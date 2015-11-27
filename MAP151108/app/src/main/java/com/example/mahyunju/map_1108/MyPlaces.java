package com.example.mahyunju.map_1108;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by mahyunju on 23/11/15.
 */
// Only one table is needed for my app : MyPlaces
// that will store name of the city, comment of it, point(on the map), and if it is wished or actually visited
public class MyPlaces {

    public static final String TABLE = "MyPlaces";

    // table columns names
    public static final String COLUMN_ROWID = "id";
    public static final String COLUMN_NAME_POINT = "point";
//    public static final String COLUMN_NAME_POINT_LAT = "point_lat";
//    public static final String COLUMN_NAME_POINT_LNG = "point_lng";
    public static final String COLUMN_NAME_CITY = "city";
    public static final String COLUMN_NAME_COMMENT = "comment";
//    public static final String COLUMN_NAME_IMAGE = "image";
//    public static final String COLUMN_NAME_IMAGE_2 = "image2";
//    public static final String COLUMN_NAME_IMAGE_3 = "image3";
    public static final String COLUMN_NAME_WISH = "wish";

//    public int placeid;
    public String point;
    public double point_lat;
    public double point_lng;
    public String city;
    public String comment;
    //public int image;
    //public int image2;
    //public int image3;
    public boolean wish;
}
