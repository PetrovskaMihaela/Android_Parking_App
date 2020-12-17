package com.example.easypark;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class ReservationFromFragment extends Fragment {

    public ReservationFromFragment() {
        // Required empty public constructor
    }
    private static final String TAG = "ReservationFromFragment";
    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener datesetlistener;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditText cityname = getActivity().findViewById(R.id.formcity);

        String city = "city not set";

        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null){
            city = extras.getString("city");
        }
        cityname.setText(city);



        displayDate = (TextView) getActivity().findViewById(R.id.editTextDate);
        displayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog picker = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        datesetlistener,
                        year, month, day

                );
            picker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            picker.show();
            }

        });

        datesetlistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "/" + month + "/" + year;
                displayDate.setText(date);
            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the activity_reservation_confirmation for this fragment
        return inflater.inflate(R.layout.fragment_reservation_from, container, false);
    }

}