package com.example.mahyunju.map_1108;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapsActivity extends FragmentActivity implements OnMapClickListener, OnMapLongClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
/*
        mMap.setOnMapLongClickListener(new OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(getApplicationContext(), latLng.toString(), Toast.LENGTH_SHORT).show();
            }
        });
*/
        setUpMapIfNeeded();
       // mMap =((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
       // mMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

        //getMyPlacesFromDB();

        /*
        PlaceRepo repo = new PlaceRepo(this);
        ArrayList<HashMap<String,String>> placelist = repo.getPlaceList();
        if(placelist.size()!=0) {
            for(HashMap<String, String> myplace : placelist) {
                for(Map.Entry<String, String> placeEntry : myplace.entrySet()) {
                    String point =
                }
            }
        }
        */

        PlaceRepo repo = new PlaceRepo(this);
        ArrayList<HashMap<String,String>> placelist = repo.getPlaceList();

        if(placelist.size()!=0) {
            for (int x = 0; x < placelist.size(); x++ ) {
                String myCity = placelist.get(x).get("city");
                String myPoint = placelist.get(x).get("point");
                int myWish = Integer.valueOf(placelist.get(x).get("wish"));

                int index = myPoint.indexOf(",");
                String point_lat = myPoint.substring(0, index).trim();
                String point_lng = myPoint.substring(index + 1).trim();

                Double point_lat2 = Double.parseDouble(point_lat);
                Double point_lng2 = Double.parseDouble(point_lng);

                LatLng MYPLACEPOSITION = new LatLng(point_lat2, point_lng2);
                String MYPLACETITLE = myCity;

                if(myWish>0) {
                    mMap.addMarker(new MarkerOptions()
                                    .position(MYPLACEPOSITION)
                                    .title(MYPLACETITLE)
                                    .draggable(true)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag3))
                                    .anchor(0.0f, 1.0f)
                                    .alpha(0.5f)
                    );
                }
                else {
                    mMap.addMarker(new MarkerOptions()
                                    .position(MYPLACEPOSITION)
                                    .title(MYPLACETITLE)
                                    .draggable(true)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag3))
                                    .anchor(0.0f, 1.0f)
                    );
                }
            }
        }

        final LatLng PERTH = new LatLng(-31.90, 115.86);
        mMap.addMarker(new MarkerOptions()
                        .position(PERTH)
                        .title("Perth, test")
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag3))
                        .anchor(0.0f, 1.0f)
                        .alpha(0.5f)
                        // alpha for wishplace
        );

        final LatLng BARCELONA = new LatLng(41.39,2.07);
        mMap.addMarker(new MarkerOptions()
                        .position(BARCELONA)
                        .title("Barcelona, test2")
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag3))
                        .anchor(0.0f, 1.0f)
        );


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapsActivity.this, marker.getPosition().toString(), Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(MapsActivity.this, NewActivity.class);
                intent1.putExtra("theplace", marker.getTitle());
                startActivity(intent1);
                return true;
            }
        });

//        mMap.setMyLocationEnabled(true); // not needed
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        mMap.setOnMapLongClickListener(this);


    }

    //title(name), latlng 반환하는 함수를 여기 말고 PlaceRepo에다가 넣어야 함!!!


    @Override
    public void onMapClick(LatLng point) {
        Toast.makeText(getApplicationContext(), "pressed, point=" + point, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLongClick(LatLng point) {
        Toast.makeText(getApplicationContext(), "long pressed, point=" + point, Toast.LENGTH_SHORT).show();


        // Add a marker when long clicked
        final LatLng longclickedpoint = point;
        mMap.addMarker(new MarkerOptions()
                        .position(longclickedpoint)
                        .title("My Place")
                        //.draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag3))
                        .anchor(0.0f, 1.0f)
        );

        Intent intent2 = new Intent(MapsActivity.this, AddMyPlace.class);
        intent2.putExtra("thepoint", point.toString());

        startActivity(intent2);
    }
}
