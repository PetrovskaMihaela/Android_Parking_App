package com.example.easypark;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class parkAdapter extends RecyclerView.Adapter<parkAdapter.parkViewHolder> {

    class parkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView parkName;
        public ImageView parkPic;
        public Button greenbtn;
        public Button redbtn;

        public parkViewHolder(final View itemView) {
            super(itemView);
            parkName = (TextView) itemView.findViewById(R.id.parkname);
            parkPic = (ImageView) itemView.findViewById(R.id.parkpicture);
            greenbtn = (Button) itemView.findViewById(R.id.greenbtn);
            redbtn = (Button) itemView.findViewById(R.id.redbtn);
            greenbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "број на слободни места", Toast.LENGTH_SHORT).show();
                }
            });
            //itemView.setOnClickListener(this);
            redbtn = (Button) itemView.findViewById(R.id.redbtn);
            redbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "број на зафатени места", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    private static final String TAG = parkAdapter.class.getSimpleName();


    DatabaseHelper mDB;
    private int parkrowlayout;
    private Context parkContext;
    parkAdapter.RecyclerViewClickListener listener;

    public parkAdapter(Context parkContext, DatabaseHelper db,  int rowLayout, parkAdapter.RecyclerViewClickListener listener){
        this.parkrowlayout = rowLayout;
        this.parkContext = parkContext;
        this.listener = listener;
        mDB = db;
    }

    @Override
    public parkAdapter.parkViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(parkrowlayout, parent, false);
        return new parkViewHolder(v);
    }

    @Override
    public void onBindViewHolder(parkViewHolder viewHolder,int position) {

        String city = "city not set";




        final parkViewHolder h = viewHolder;

        Parking current = mDB.querypark(position);
        viewHolder.parkName.setText(current.getParkName());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(v.getContext(), ReservationConfirmation.class);
                v.getContext().startActivity(i);
                //extra
            }
        });


        viewHolder.parkName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(parkContext, tv.getText(), Toast.LENGTH_SHORT).show();

            }
        });


    }
    @Override
    public int getItemCount(){
        return (int) mDB.countpark();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}


