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


public class RecyclerCities extends AppCompatActivity {




    private DatabaseHelper mDB;
    private RecyclerView recycler;
    private cityAdapter.RecyclerViewClickListener listener;
    String pomUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_cities);

        mDB = new DatabaseHelper(this);

        recycler = findViewById(R.id.cities);


        String user="";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user = extras.getString("user");
        }
        setAdapter(user);

        pomUser = user;
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
        Intent intent = null;
        switch (item.getItemId())
        {
            case R.id.myreservations:
                intent = new Intent(this, MyReservations.class);
                intent.putExtra("user", pomUser);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setAdapter(String user) {
        cityAdapter adapter = new cityAdapter(this, mDB, R.layout.city_row, listener,user );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }


}