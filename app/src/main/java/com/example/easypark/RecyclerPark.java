package com.example.easypark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;


public class RecyclerPark extends AppCompatActivity {

    private DatabaseHelper mDB;
    private RecyclerView recycler;
    private parkAdapter adapter;

    private parkAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_park);

        mDB = new DatabaseHelper(this);

        recycler = (RecyclerView) findViewById(R.id.parkingplaces);

        adapter = new parkAdapter(this, mDB, R.layout.park_row, listener );

        recycler.setAdapter(adapter);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        recycler.setItemAnimator(new DefaultItemAnimator());




    }


}