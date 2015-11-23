package com.example.mahyunju.map_1108;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

/**
 * Created by mahyunju on 20/11/15.
 */
public class AddMyPlace extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnSave;
    Button btnClose;
    EditText editTextName;
    EditText editTextComment;
    //private String _place_id ;
    public String[] pointClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmyplace);

        pointClicked = getIntent().getExtras().getString("thepoint").split(",");

        TextView addPlace = (TextView)(findViewById(R.id.newplace));
        addPlace.setText("Latitude: " + pointClicked[0] + ", Longitude: " + pointClicked[1]);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName); //name of the city
        editTextComment = (EditText) findViewById(R.id.editTextComment);

        btnSave.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        //Intent intent4 = getIntent();
        //_place_id = intent4.getExtras().getString("thepoint"); // place_id : point as a string

    }

    @Override
    public void onClick(View view) {
        if(view == findViewById(R.id.btnSave)) {
            PlaceRepo repo = new PlaceRepo(this);
            MyPlaces place = new MyPlaces();
            // ?? do sth to retrieve pointClicked?
            //pointClicked = getIntent().getExtras().getString("thepoint").split(",");

            TextView getpoint = (TextView)findViewById(R.id.newplace);
            String getpoint1 = (String) getpoint.getText();

            place.point = getpoint1;
            //place.point_lat = Double.parseDouble(pointClicked[0]);
            //place.point_lng = Double.parseDouble(pointClicked[1]);

            //place.point_latlng = (place.point_lat, place.point_lng);
            place.city = editTextName.getText().toString();
            place.comment = editTextComment.getText().toString();
            //place.placeid = Integer.parseInt(_place_id);

            //_place_id = repo.insert(place);

            int result = 0;
            result = repo.insert(place);

          //  if(result!=0) {
                Toast.makeText(this, "New Place inserted", Toast.LENGTH_LONG).show();
           // }
            //else {
              //  Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            //}
        }
        else if(view == findViewById(R.id.btnClose)) {
            finish();
        }
    }
}
