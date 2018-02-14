package com.example.aspire.piyushbrinder_gmap_resturantapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CuisineList extends AppCompatActivity {

    String[] rest = new String[] {
            "Indian",
            "Chinese",
            "Italian",
            "American"
    };

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine_list);

        ListView listView1 = (ListView) findViewById(R.id.android_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, rest){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.parseColor("#ffffff"));
                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

                // Return the view
                return view;
            }
        };

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // ListView Clicked item index
                //int itemPosition = position;

                intent = new Intent(CuisineList.this, Resturants_Locator_Activity.class);
                intent.putExtra("EXTRA_MESSAGE",Integer.toString(position));
                startActivity(intent);
            }
        });
    }
}

