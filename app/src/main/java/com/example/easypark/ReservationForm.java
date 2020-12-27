package com.example.easypark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class ReservationForm extends AppCompatActivity {
    String city = "";
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);



        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragmentforma);
        Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragmentspin);

        EditText cityname = (EditText) findViewById(R.id.formcity);



        Bundle extras = getIntent().getExtras();
        if(extras != null){
            city = extras.getString("cityname");
           username = extras.getString("username");
        }
        cityname.setText(city);

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
                intent.putExtra("user", username);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void functionClick(View v){

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);


        TextView datepicker = (TextView) findViewById(R.id.editTextDate);
        String hour = spinner.getSelectedItem().toString();
        String date = datepicker.getText().toString();

        Intent i = new Intent(this, RecyclerPark.class);
        i.putExtra("city", city);
        i.putExtra("username", username);
        i.putExtra("date", date);
        i.putExtra("hour", hour);
        startActivity(i);


    }
}