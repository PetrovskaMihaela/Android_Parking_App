package com.example.easypark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ReservationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragmentforma);
        /*EditText cityname = findViewById(R.id.formcity);

        String city = "city not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            city = extras.getString("city");
        }

        cityname.setText(city);*/
    }
}