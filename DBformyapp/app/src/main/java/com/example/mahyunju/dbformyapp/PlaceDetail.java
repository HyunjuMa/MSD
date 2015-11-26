package com.example.mahyunju.dbformyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mahyunju on 22/11/15.
 */
public class PlaceDetail extends AppCompatActivity implements android.view.View.OnClickListener {
    Button btnSave;
    Button btnClose;
    EditText editTextName;
    EditText editTextComment;
    private int _place_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_datail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextComment = (EditText) findViewById(R.id.editTextComment);

        btnSave.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        _place_id = 0;
        Intent intent1 = getIntent();
        _place_id = intent1.getIntExtra("placeid", 0);
        PlaceRepo repo = new PlaceRepo(this);
        MyPlaces place = new MyPlaces();
        place = repo.getPlaceById(_place_id);

        editTextName.setText(place.city);
        editTextComment.setText(place.comment);
    }

    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)) {
            PlaceRepo repo = new PlaceRepo(this);
            MyPlaces place = new MyPlaces();

            place.city = editTextName.getText().toString();
            place.comment = editTextComment.getText().toString();
            place.placeid = _place_id;

            //if (_place_id==0) {
                _place_id = repo.insert(place);
                Toast.makeText(this, "New Place inserted", Toast.LENGTH_LONG).show();
            //}
        }
        else if(view == findViewById(R.id.btnClose)) {
            finish();
        }
    }
}
