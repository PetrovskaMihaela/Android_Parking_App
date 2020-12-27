package com.example.easypark;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class reservationAdapter extends RecyclerView.Adapter<reservationAdapter.resViewHolder>{


    class resViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView city;
        public TextView park;
        public TextView date;
        public TextView time;
        public Button navbtn;

        public resViewHolder(final View itemView) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.city_myres);
            park = (TextView) itemView.findViewById(R.id.park_myres);
            date = (TextView) itemView.findViewById(R.id.date_myres);
            time = (TextView) itemView.findViewById(R.id.hour_myres);
            navbtn = (Button) itemView.findViewById(R.id.myres_btn);



        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    DatabaseHelper mDB;
    private int resrowlayout;
    private Context resContext;
    reservationAdapter.RecyclerViewClickListener listener;

    String user = "";

    public reservationAdapter(Context resContext, DatabaseHelper db, int rowLayout, reservationAdapter.RecyclerViewClickListener listener, String user) {
        this.resrowlayout = rowLayout;
        this.resContext = resContext;
        this.listener = listener;
        mDB = db;
        this.user = user;

    }

    @NonNull
    @Override
    public reservationAdapter.resViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resrowlayout, parent, false);
        return new reservationAdapter.resViewHolder(v);
    }

    @Override
    public void onBindViewHolder( resViewHolder holder, int position) {

        Reservation current = mDB.queryreservation(position, user);


            holder.city.setText(current.getCityR());
            holder.park.setText(current.getParkR());
            holder.date.setText(current.getDateR());
            holder.time.setText(current.getTimeR());


       final double latt = mDB.latitude(current.getParkR());
       final double longg = mDB.longitude(current.getParkR());

        holder.navbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(resContext, "navigation", Toast.LENGTH_SHORT).show();
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%s,%s", latt, longg);
                i.setData(Uri.parse(uri));
                i.setPackage("com.google.android.apps.maps");
                    resContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount(){
        return (int) mDB.countreservation();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

}





