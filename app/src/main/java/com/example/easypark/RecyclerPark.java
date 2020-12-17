package com.example.easypark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerPark extends AppCompatActivity {

    private RecyclerView recycler;
    private ArrayList<City> parkingList;
    private parkAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_park);
        recycler = findViewById(R.id.parkingplaces);
        parkingList = new ArrayList<>();
        setParkInfo();
        setAdapter();
    }

    private void setAdapter() {
        //setOnClickListener();
        parkAdapter adapter = new parkAdapter(parkingList, R.layout.park_row, this, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }

    private void setParkInfo() {
        parkingList.add(new City("Скопје"));
        parkingList.add(new City("Куманово"));
        parkingList.add(new City("Велес"));
        parkingList.add(new City("Штип"));
        parkingList.add(new City("Гевгелија"));
        parkingList.add(new City("Струмица"));
        parkingList.add(new City("Прилеп"));
        parkingList.add(new City("Битола"));
        parkingList.add(new City("Охрид"));
        parkingList.add(new City("Гостивар"));
        parkingList.add(new City("Тетово"));
    }
}