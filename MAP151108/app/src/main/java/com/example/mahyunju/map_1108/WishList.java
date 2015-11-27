package com.example.mahyunju.map_1108;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

// activity that will show Wish list when the user clicks on wish marker

public class WishList extends ListActivity implements android.view.View.OnClickListener{

    TextView title;
    Button btnGetAll;

    @Override
    public void onClick(View view) {
        if(view == findViewById(R.id.btnGetAll)) {
            PlaceRepo repo = new PlaceRepo(this);
            ArrayList<HashMap<String, String>> placelist = repo.getPlaceList();
            ArrayList<String> wishedonlylist = new ArrayList<>();

            if(placelist.size()!=0) {
                for (int x = 0; x < placelist.size(); x++ ) {
                    String myCity = placelist.get(x).get("city");
                    int myWish = Integer.valueOf(placelist.get(x).get("wish"));

                    if(myWish>0) {
                        // will put this item to wishlist (wishedonlylist)
                        wishedonlylist.add(myCity);
                        //Toast.makeText(WishList.this, myCity, Toast.LENGTH_SHORT).show();
                    }
                }

                if(wishedonlylist.size()!=0) {
                    ListView wishlist;
                    wishlist = (ListView)findViewById(android.R.id.list);
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<>(WishList.this, android.R.layout.simple_list_item_1, wishedonlylist);
                    wishlist.setAdapter(adapter3);
                }
                else {
                    Toast.makeText(this, "No Data Found : Add Your Wish Place!", Toast.LENGTH_LONG).show();
                }

            }
            else {
                Toast.makeText(this, "No Data Found : Add Your Place!", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);

        String markerClicked = getIntent().getExtras().getString("wishplace");
        title = (TextView)(findViewById(R.id.title));
        title.setText("Your Wish Places");
        TextView thePlace = (TextView)(findViewById(R.id.placeclicked));
        thePlace.setText(markerClicked);

        // ImageView
        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
    }
}
