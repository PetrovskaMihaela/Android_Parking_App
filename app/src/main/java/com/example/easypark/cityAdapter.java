package com.example.easypark;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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

public class cityAdapter extends RecyclerView.Adapter<cityAdapter.cityViewHolder> {

    private int cityrowLayout;
    private Context cityContext;
    private RecyclerViewClickListener listener;
    DatabaseHelper mDB;

    public cityAdapter(Context cityContext, DatabaseHelper db, int rowLayout, RecyclerViewClickListener listener) {

        this.cityrowLayout = rowLayout;
        this.cityContext = cityContext;
        this.listener = listener;
        mDB = db;
    }

    public class cityViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        public TextView cityName;
        public ImageView cityPic;
        public Button rsrvButton;
        public TextView cityInfo;
        public cityViewHolder(final View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.cityname);
            cityInfo = (TextView) itemView.findViewById(R.id.cityinfo);
            cityPic = (ImageView) itemView.findViewById(R.id.citypicture);
            rsrvButton = (Button) itemView.findViewById(R.id.rsrvbutton);
            rsrvButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ReservationForm.class);
                    intent.putExtra("city",  cityName.getText());
                    v.getContext().startActivity(intent);
                }
            });
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    @Override
    public cityAdapter.cityViewHolder onCreateViewHolder(ViewGroup parent, int i){
        View v = LayoutInflater.from(parent.getContext()).inflate(cityrowLayout, parent,false);
        return new cityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final cityViewHolder viewHolder, final int position){

        City current =  mDB.query(position);

        viewHolder.cityName.setText(current.getCityName());
        //viewHolder.cityInfo.setText(current.getCityNumber());
        /*if(viewHolder.cityName.getText() == "Скопје") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Велес") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Куманово") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Штип") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Гевгелија") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Струмица") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Битола") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Прилеп") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Охрид") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Гостивар") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }
        if(viewHolder.cityName.getText() == "Тетово") {
            viewHolder.cityPic.setImageResource(R.drawable.logo);
        }*/
        final cityViewHolder h = viewHolder;



        viewHolder.cityName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView tv = (TextView) v;
                Toast.makeText(cityContext, tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount(){
        return (int) mDB.count();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
