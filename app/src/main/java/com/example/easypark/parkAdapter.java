package com.example.easypark;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class parkAdapter extends RecyclerView.Adapter<parkAdapter.parkViewHolder> {


    private ArrayList<City> parkingList;
    private int parkrowLayout;
    private Context parkContext;
    parkAdapter.RecyclerViewClickListener listener;

    public parkAdapter(ArrayList<City> parkingList, int rowLayout, Context parkContext, parkAdapter.RecyclerViewClickListener listener) {
        this.parkingList = parkingList;
        this.parkrowLayout = rowLayout;
        this.parkContext = parkContext;
        this.listener = listener;
    }

    public class parkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView parkName;
        public ImageView parkPic;
        public Button greenbtn;
        public Button redbtn;

        public parkViewHolder(final View itemView) {
            super(itemView);
            parkName = (TextView) itemView.findViewById(R.id.parkname);
            parkPic = (ImageView) itemView.findViewById(R.id.parkpicture);
            greenbtn = (Button) itemView.findViewById(R.id.greenbtn);
            greenbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /* Intent intent = new Intent(v.getContext(), ReservationForm.class);
                   intent.putExtra("city",  cityName.getText());
                   v.getContext().startActivity(intent);*/
                    Toast.makeText(itemView.getContext(), "број на слободни места", Toast.LENGTH_SHORT).show();
                }
            });
            //itemView.setOnClickListener(this);
            redbtn = (Button) itemView.findViewById(R.id.redbtn);
            redbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /* Intent intent = new Intent(v.getContext(), ReservationForm.class);
                   intent.putExtra("city",  cityName.getText());
                   v.getContext().startActivity(intent);*/
                    Toast.makeText(itemView.getContext(), "број на зафатени места", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    @Override
    public parkAdapter.parkViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(parkrowLayout, parent, false);
        return new parkAdapter.parkViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final parkAdapter.parkViewHolder viewHolder, final int position) {
        String parkk = parkingList.get(position).getCity();
        viewHolder.parkName.setText(parkk);
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
        return  parkingList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}


