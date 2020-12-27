package com.example.easypark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class RecyclerPark extends AppCompatActivity {

    private DatabaseHelper mDB;
    private RecyclerView recycler;
    private parkAdapter.RecyclerViewClickListener listener;

    String city="";
    String date="";
    String hour="";
    String user="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_park);

        mDB = new DatabaseHelper(this);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user = extras.getString("username");
            city = extras.getString("city");
            date = extras.getString("date");
            hour = extras.getString("hour");
        }


        recycler = findViewById(R.id.parkingplaces);

        setAdapter(city, date, hour, user);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_reserv, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.myreservations:
                Intent intent = new Intent(this, MyReservations.class);
                intent.putExtra("user", user);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void setAdapter(String city, String date, String hour, String user) {

        parkAdapter adapter = new parkAdapter(this, mDB, R.layout.park_row, listener,city, date, hour, user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }


}