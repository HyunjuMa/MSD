package com.example.mahyunju.dbformyapp;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends ListActivity implements android.view.View.OnClickListener{

    ListView list1;
    TextView placeid;
    Button btnAdd, btnGetAll;

    @Override
    public void onClick(View view) {
        if (view==findViewById(R.id.btnAdd)) {
            Intent intent1 = new Intent(this, PlaceDetail.class);
            intent1.putExtra("placeid", 0);
            startActivity(intent1);
        }
        else {
            PlaceRepo repo = new PlaceRepo(this);
            ArrayList<HashMap<String, String>> placelist = repo.getPlaceList();
            if(placelist.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        placeid = (TextView) view.findViewById(R.id.placeid);
                        String ID = placeid.getText().toString();
                        Intent objIntent = new Intent(getApplicationContext(), PlaceDetail.class);
                        objIntent.putExtra("placeid", Integer.parseInt(ID));
                        startActivity(objIntent);

                    }
                });
                // retrieving db
                //ListAdapter adapter2 = new SimpleAdapter(MainActivity.this, placelist, R.layout.view_place_entry, new String[] {"city", "comment"}, new int[] {R.id.city, R.id.comment} );
                //setListAdapter(adapter2);
                for (int a = 0; a < placelist.size(); a++ ) {
                    HashMap<String, String> tmpData = placelist.get(a);
                    Set<String> name = tmpData.keySet();
                    Iterator iterator1 = name.iterator();
                    while (iterator1.hasNext()) {
                        String hmKey = (String) iterator1.next();
                        String hmCityName = "";
                        String hmComment = "";
                        if (hmKey.equals("city")) {
                            hmCityName = tmpData.get(hmKey);
                            Toast.makeText(this, "key : " + hmKey + " / name : " + hmCityName, Toast.LENGTH_SHORT).show();
                        }
                        else if (hmKey.equals("comment")) {
                            hmComment = tmpData.get(hmKey);
                            Toast.makeText(this, "key : " + hmKey + " / comment : " + hmComment, Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(this, "city : " + hmCityName + " / comment : " + hmComment, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(this, " No City ", Toast.LENGTH_LONG).show();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button)findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
    }
}
