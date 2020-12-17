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


public class FragmentSpinner extends Fragment {



    public FragmentSpinner() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b = (Button) getActivity().findViewById(R.id.makersv_btn);
        b.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                Toast.makeText(getActivity().getApplicationContext(), "Одбрани датум и време!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity().getApplicationContext(), RecyclerPark.class);
                startActivity(i);
            }
        });

        final Spinner s = (Spinner) getActivity().findViewById(R.id.spinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "Вие избравте: " + s.getSelectedItem(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity().getApplicationContext(), "Не избравте ништо", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the activity_reservation_confirmation for this fragment
        return inflater.inflate(R.layout.fragment_spinner, container, false);
    }
}