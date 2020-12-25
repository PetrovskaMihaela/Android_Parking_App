package com.example.easypark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class RecyclerPark extends AppCompatActivity {

    private DatabaseHelper mDB;
    private RecyclerView recycler;
    private parkAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_park);

        mDB = new DatabaseHelper(this);

        String city="";
        String date="";
        String hour="";
        String user="";
        /*Bundle extras = getIntent().getExtras();
        if(extras != null){
            city = extras.getString("city");
            date = extras.getString("date");
            time = extras.getString("time");
        }*/

        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        date = intent.getStringExtra("date");
        hour = intent.getStringExtra("hour");
        user = intent.getStringExtra("user");

        recycler = findViewById(R.id.parkingplaces);

        setAdapter(city, date, hour, user);




    }
    private void setAdapter(String city, String date, String hour, String user) {
        //setOnClickListener();
        parkAdapter adapter = new parkAdapter(this, mDB, R.layout.park_row, listener,city, date, hour, user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }


}