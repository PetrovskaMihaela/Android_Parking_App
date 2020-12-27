package com.example.easypark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class ReservationConfirmation extends AppCompatActivity {

    String city = "";
    String date = "";
    String hour = "";
    String user = "";
    String park = "";
    DatabaseHelper db;

    double lat;
    double lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);

        db = new DatabaseHelper(this);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);

        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
            Fragment frag3 = getFragmentManager().findFragmentById(R.id.fragment3);
        }

       Bundle extras = getIntent().getExtras();
        if(extras != null){
            city = extras.getString("city");
            user = extras.getString("user");
            hour = extras.getString("hour");
            date = extras.getString("date");
            park = extras.getString("park");
        }

        TextView citytext = (TextView) findViewById(R.id.cityconf);
        TextView parktext = (TextView) findViewById(R.id.parkconf);
        TextView datetext = (TextView) findViewById(R.id.dateconf);
        TextView timetext = (TextView) findViewById(R.id.hourconf);



        citytext.setText(city);
        parktext.setText(park);
        datetext.setText(date);
        timetext.setText(hour);



        lat = db.latitude(park);
        lng = db.longitude(park);


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
                intent.putExtra("user", user);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void functionConfirm(View v){

        final Button confirm = findViewById(R.id.potvrda);

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int rpu;
                rpu = db.numberResPerUser(user);
                if (rpu < 3) {
                if(hour.equals("")||date.equals("")){
                    Toast.makeText(getApplicationContext(), "Some fields may be empty! Try again! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean insert = db.insertReservation(user, city, park, date, hour);
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "Your reservation has been successfully made! ", Toast.LENGTH_SHORT).show();
                    }
                }

              } else {
                    Toast.makeText(getApplicationContext(), "Only 3 reservations are allowed per user!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void functionNavigation(View v){

        final Button navigation = findViewById(R.id.navbtn);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "навигација", Toast.LENGTH_SHORT).show();


                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%s,%s", lat, lng);
                i.setData(Uri.parse(uri));
                i.setPackage("com.google.android.apps.maps");
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });
    }
}
