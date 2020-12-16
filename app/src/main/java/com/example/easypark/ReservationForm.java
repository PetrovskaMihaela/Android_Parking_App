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
        Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragmentspin);


    }
}