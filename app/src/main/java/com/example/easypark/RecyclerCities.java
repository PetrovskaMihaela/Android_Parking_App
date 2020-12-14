package com.example.easypark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class RecyclerCities extends AppCompatActivity {

    private RecyclerView recycler;
    private ArrayList<City> cityList;
    private cityAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_cities);
        recycler = findViewById(R.id.cities);
        cityList = new ArrayList<>();
        setCityInfo();
        setAdapter();
    }

    private void setAdapter() {
        //setOnClickListener();
        cityAdapter adapter = new cityAdapter(cityList, R.layout.city_row, this, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }

   /* private void setOnClickListener() {
        listener = new cityAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ReservationForm.class);
                intent.putExtra("city", cityList.get(position).getCity());
                startActivity(intent);
            }
        };
    }*/

    private void setCityInfo() {
        cityList.add(new City("Скопје"));
        cityList.add(new City("Куманово"));
        cityList.add(new City("Велес"));
        cityList.add(new City("Штип"));
        cityList.add(new City("Гевгелија"));
        cityList.add(new City("Струмица"));
        cityList.add(new City("Прилеп"));
        cityList.add(new City("Битола"));
        cityList.add(new City("Охрид"));
        cityList.add(new City("Гостивар"));
        cityList.add(new City("Тетово"));
    }
}