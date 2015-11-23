package com.example.mahyunju.map_1108;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

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

//    GoogleMapOptions options = new GoogleMapOptions().liteMode(true);

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
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
    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

//        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(10, 10)).title("Marker2"));

        final LatLng PERTH = new LatLng(-31.90, 115.86);
        mMap.addMarker(new MarkerOptions()
                        .position(PERTH)
                        .title("Perth, test")
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag3))
                        .anchor(0.0f, 1.0f)
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
