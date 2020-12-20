package com.example.easypark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


public class RecyclerCities extends AppCompatActivity {


    private DatabaseHelper mDB;
    private RecyclerView recycler;
    private cityAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_cities);

        mDB = new DatabaseHelper(this);

        recycler = findViewById(R.id.cities);
        setAdapter();


    }

    private void setAdapter() {
        //setOnClickListener();
        cityAdapter adapter = new cityAdapter(this, mDB, R.layout.city_row, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }


}