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

public class cityAdapter extends RecyclerView.Adapter<cityAdapter.cityViewHolder> {
    private ArrayList<City> cityList;
    private int cityrowLayout;
    private Context cityContext;
    private RecyclerViewClickListener listener;

    public cityAdapter(ArrayList<City> cityList, int rowLayout, Context cityContext, RecyclerViewClickListener listener) {
        this.cityList = cityList;
        this.cityrowLayout = rowLayout;
        this.cityContext = cityContext;
        this.listener = listener;
    }

    public class cityViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{


        public TextView cityName;
        public ImageView cityPic;
        public Button rsrvButton;
        public cityViewHolder(final View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.cityname);
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
        String cityy = cityList.get(position).getCity();
        viewHolder.cityName.setText(cityy);

        viewHolder.cityName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView tv = (TextView) v;
                Toast.makeText(cityContext, tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });

       /* viewHolder.rsrvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener= new RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        Intent intent = new Intent(v.getContext(), ReservationForm.class);
                        intent.putExtra("city",  viewHolder.cityName.getText());
                        v.getContext().startActivity(intent);

                    }
                };


            }
        });*/
    }
    @Override
    public int getItemCount(){
        return  cityList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
