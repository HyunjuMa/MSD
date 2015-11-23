package com.example.mahyunju.map_1108;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mahyunju on 20/11/15.
 */
public class NewActivity extends ListActivity implements android.view.View.OnClickListener{

    //ListView list1;
    Button btnGetAll;


    @Override
    public void onClick(View view) {
        if(view == findViewById(R.id.btnGetAll)) {
            PlaceRepo repo = new PlaceRepo(this);
            ArrayList<HashMap<String, String>> placelist = repo.getPlaceList();
            if(placelist.size()!=0) {
                /*ListView lv = getListView();

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //nothing happens when clicked, just showing a listed sets of cities and comments
                        //placeid = (TextView) view.findViewById(R.id.placeid);
                        //String ID = placeid.getText().toString();
                        //
                    }
                });
                */
                ListAdapter adapter2 = new SimpleAdapter(NewActivity.this, placelist, R.layout.view_place_entry, new String[] {"city", "comment"}, new int[] {R.id.city, R.id.comment});
                setListAdapter(adapter2);
            }
            else {
                Toast.makeText(this, "No Data Found : Add Your Place!", Toast.LENGTH_LONG).show();
            }
        }
        else {
            //finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        String markerClicked = getIntent().getExtras().getString("theplace");
        TextView thePlace = (TextView)(findViewById(R.id.placeclicked));
        thePlace.setText(markerClicked);

        // ImageView
        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
    }
}
