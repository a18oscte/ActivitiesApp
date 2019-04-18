package com.example.brom.activitiesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] mountainNames = {"Matterhorn","Mont Blanc","Denali"};
    private String[] mountainLocations = {"Alps","Alps","Alaska"};
    private int[] mountainHeights ={4478,4808,6190};
    public static final String EXTRA_MESSAGE = "com.example.brom.activitiesapp.extra.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.brom.activitiesapp.extra.MESSAGE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<String> listData = new ArrayList<>();

        final ArrayList<Mountain> mountainArr = new ArrayList();

        Mountain m = new Mountain("Kebnekaise", "Skanderna", 2106);

        mountainArr.add(m);

        for(int i = 0; i < mountainNames.length; i++) {
            mountainArr.add(new Mountain(mountainNames[i], mountainLocations[i], mountainHeights[i]));
        }

        for(int i = 0; i < mountainArr.size(); i++) {

            listData.add(mountainArr.get(i).namn());
        }


        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.list_item_textview,R.id.list_item_textview,listData);




        ListView myListView = (ListView)findViewById(R.id.my_listview);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = mountainArr.get(position).info();
                String Title = mountainArr.get(position).namn();
                launchSecondActivity(view, message, Title);
            }
        });

    }
    public void launchSecondActivity(View view, String n, String t) {
        Intent intent = new Intent(this, MountainDetailsActivity.class);
        intent.putExtra(EXTRA_MESSAGE, n);
        intent.putExtra(EXTRA_MESSAGE2, t);
        startActivity(intent);
    }
}
