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

        setUpMapIfNeeded();
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

        PlaceRepo repo = new PlaceRepo(this);
        ArrayList<HashMap<String,String>> placelist = repo.getPlaceList();

        // when setting up the map, I had to get information about myplaces so that I could put the flag image on the map
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
                // this part is ia little messy but it was for making a LatLng to String, String to Double and making it a LatLng again.

                LatLng MYPLACEPOSITION = new LatLng(point_lat2, point_lng2);
                String MYPLACETITLE = myCity;

                // if it is tha place the user actually visited, I will make it full alpha.
                // If not, I will make alpha of the marker 0.5 so that it can be quite transparent.
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

        // when marker is clicked on, it will open different activity ( either wishlist or list of places the user visitied )
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                float alphaofmarker = marker.getAlpha();
                if(alphaofmarker<1) {
                    Intent intentwish = new Intent(MapsActivity.this, WishList.class);
                    intentwish.putExtra("wishplace", marker.getTitle().toString());
                    startActivity(intentwish);
                    return true;
                }
                else {
                    Intent intent1 = new Intent(MapsActivity.this, NewActivity.class);
                    intent1.putExtra("theplace", marker.getTitle().toString());
                    startActivity(intent1);
                    return true;
                }
            }
        });

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        mMap.setOnMapLongClickListener(this);


    }

    @Override
    public void onMapClick(LatLng point) {
        Toast.makeText(getApplicationContext(), "pressed, point=" + point, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLongClick(LatLng point) {
        Toast.makeText(getApplicationContext(), "long pressed, point=" + point, Toast.LENGTH_SHORT).show();

        // Add a marker when long clicked on certain point of the map (Add this point to one of my places or wish lists)
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
