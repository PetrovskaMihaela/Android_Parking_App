package com.example.easypark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;


public class MyReservations extends AppCompatActivity {
    String user = "";
    String city = "";
    String park = "";
    String date = "";
    String time = "";
    int i = 0;

    private DatabaseHelper db;
    private RecyclerView recycler;
    private reservationAdapter.RecyclerViewClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        recycler = findViewById(R.id.my_reservations);
        db = new DatabaseHelper(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);



        Bundle extras = getIntent().getExtras();
        if(extras != null){
            user = extras.getString("user");
        }

        setAdapter(user);

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_reserv, menu);
        return true;
    }

    private void setAdapter(String user) {

        reservationAdapter adapter = new reservationAdapter(this, db, R.layout.reservation_row, listener, user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }

}