package com.example.easypark;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentConf2 extends Fragment {
    public FragmentConf2() {
        // Required empty public constructor
    }

    DatabaseHelper db;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b = (Button) getActivity().findViewById(R.id.navbtn);
        final TextView park = getActivity().findViewById(R.id.help);
        final String parkname = String.valueOf(park);
        db = new DatabaseHelper(getContext());
        b.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                Toast.makeText(getActivity().getApplicationContext(), "навигација", Toast.LENGTH_SHORT).show();

                double lat = db.latitude(parkname);
                double lng = db.longitude(parkname);
                        //"google.navigation:q=42.005529,21.392129&mode=d"
                Intent i = new Intent (Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + lat + "," + lng + "&mode=d"));
                i.setPackage("com.google.android.apps.maps");
                if(i.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(i);
                }

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the activity_reservation_confirmation for this fragment
        return inflater.inflate(R.layout.fragment_conf2, container, false);
    }

}