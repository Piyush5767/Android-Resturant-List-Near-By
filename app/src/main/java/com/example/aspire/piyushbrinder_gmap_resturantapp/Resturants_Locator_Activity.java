package com.example.aspire.piyushbrinder_gmap_resturantapp;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.IOException;
import java.util.Iterator;
public class Resturants_Locator_Activity extends AppCompatActivity {

    Double IndianResturant_Coordinates[][]={{43.6504265,-79.7364565},{43.6393605,-79.7160951},{43.6865646,-79.7366632},{43.6504265,-79.7364565},};
    Double ItalianResturant_Coordinates[][]={{43.6841361,-79.7595431},{43.685047,-79.761029},{43.6854137,-79.7622482},{43.696897,-79.748232}};

    Double ChinneseResturant_Coordinates[][]={{43.6510752,-79.7354635},{43.658044,-79.725311},{43.6599333,-79.7261987},{43.658931,-79.721368}};
    Double AmericanResturant_Coordinates[][]={{43.5886103,-79.7235966},{43.6275145,-79.6273918},{43.6385318,-79.6921392},{43.6553503,-79.3844711}};
    String indianResturant[][]={{"Indian Sweet Master","43.6504265","-79.7364565"},{"Doaba Sweets & Restaurant","43.6504265","-79.7364565"},
            {"Guru Lukshmi","43.6393605","-79.7160951"},{"Royal India Sweets & Restaurant","43.6865646","-79.7366632"}};

    String italianResturant[][]={{"Vesuvio Ristorante","43.6841361","-79.7595431"},{"Fanzorelli's Restaurant & Wine Bar","43.685047","-79.761029"},
            {"La Capannina Restaurant","43.6854137","-79.7622482"},{"Canelli's Italian Eatery","43.696897","-79.748232"}};

    String americanResturant[][]={{"Bobby's Hideaway","43.5886103","-79.7235966"},{"La Bonita","43.6275145","-79.6273918"},
            {"Turtle Jack's Muskoka Grill","43.6385318","-79.6921392"},{"Denny's","43.6553503","-79.3844711"}};

    String chineseResturant[][]={{"Fook Hing Restaurant","43.6510752","-79.7354635"},{"Hakka Delight","43.658044","-79.725311"},
            {"Wok of Fame Restaurant","43.6599333","-79.7261987"},{"New China Garden Restaurant","43.658931","-79.721368"}};


    String message = "0";
    Double Lati = 43.6504265;
    Double Long = -79.7364565;
    String title = "Indian Sweet Master";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturants_list);
        // Get the Intent that started this activity and extract the string

        Intent intent = getIntent();
        message = intent.getStringExtra("EXTRA_MESSAGE");
        ListView listView1 = (ListView) findViewById(R.id.android_list);
        Geocoder coder = new Geocoder(this);
        String[] items = new String[3];

        for(int i=0; i<3; i++) {
            String locInfo = "";
            try {
                locInfo+= getData(i);
                Iterator<Address> addresses = coder.getFromLocation(Lati,Long,3).iterator();
                if (addresses != null) {
                    Address namedLoc = addresses.next();
                    int addIdx = namedLoc.getMaxAddressLineIndex();
                    for (int idx = 0; idx <= addIdx; idx++) {
                        String addLine = namedLoc.getAddressLine(idx);
                        locInfo += String.format("\n%s", addLine);
                    }
                }
            } catch (IOException e) {
                Log.e("GPS", "Failed to get address", e);
            }
            items[i] = locInfo;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items){
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
                title = String.valueOf(getData(position));
                Intent intentObj;
                intentObj = new Intent(Resturants_Locator_Activity.this, Gmaps.class);
                intentObj.putExtra("lati",String.valueOf(Lati));
                intentObj.putExtra("long",String.valueOf(Long));
                intentObj.putExtra("title",title);
                startActivity(intentObj);
            }
        });
    }


   public String getData(int i){
        String info = "";
        if(message.equals("0")){
            info = String.format("%s", indianResturant[i][0]);
            Lati = IndianResturant_Coordinates[i][0];
            Long =  IndianResturant_Coordinates[i][1];
        } else if(message.equals("1"))
        {
            info = String.format("%s", italianResturant[i][0]);
            Lati = ItalianResturant_Coordinates[i][0];
            Long =  ItalianResturant_Coordinates[i][1];
        }else if(message.equals("2"))
        {
            info = String.format("%s", americanResturant[i][0]);
            Lati = AmericanResturant_Coordinates[i][0];
            Long =  AmericanResturant_Coordinates[i][1];
        }else if(message.equals("3"))
        {
            info = String.format("%s", chineseResturant[i][0]);
            Lati = ChinneseResturant_Coordinates[i][0];
            Long =  ChinneseResturant_Coordinates[i][1];
        }
        return info;
    }
}


