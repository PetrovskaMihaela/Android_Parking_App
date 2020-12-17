package com.example.easypark;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class FragmentConf2 extends Fragment {
    public FragmentConf2() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b = (Button) getActivity().findViewById(R.id.navbtn);
        b.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                Toast.makeText(getActivity().getApplicationContext(), "навигација", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity().getApplicationContext(), Navigation.class);
                startActivity(i);
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