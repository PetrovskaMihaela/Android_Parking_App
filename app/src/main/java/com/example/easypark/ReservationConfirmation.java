package com.example.easypark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;

public class ReservationConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
            Fragment frag3 = getFragmentManager().findFragmentById(R.id.fragment3);
        }else{
            Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
            Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragment2);
        }

    }
}