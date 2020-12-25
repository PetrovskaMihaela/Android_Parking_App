package com.example.easypark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ReservationConfirmation extends AppCompatActivity {

    String city = "";
    String date = "";
    String hour = "";
    String user = "";
    String park = "";
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);

        db = new DatabaseHelper(this);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
            Fragment frag3 = getFragmentManager().findFragmentById(R.id.fragment3);
        }else{
            Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
            Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragment2);
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

        TextView park2 = findViewById(R.id.help);

        citytext.setText(city);
        parktext.setText(park);
        datetext.setText(date);
        timetext.setText(hour);

        park2.setText(park);

    }

    public void functionConfirm(View v){

        final Button confirm = findViewById(R.id.potvrda);

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             //   int rpu = db.numberResPerUser(user);
             //   if (rpu < 3) {
                    Boolean insert = db.insertReservation(user, city, park, date, hour);
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "Your reservation has been successfully made! ", Toast.LENGTH_SHORT).show();
                    }
             /*   } else {
                    Toast.makeText(getApplicationContext(), "Only 3 reservations are allowed per user!", Toast.LENGTH_SHORT).show();
                } */
            }
        });

    }
}
