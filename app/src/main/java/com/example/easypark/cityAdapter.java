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
    String user;

    public cityAdapter(Context cityContext, DatabaseHelper db, int rowLayout, RecyclerViewClickListener listener, String user) {

        this.cityrowLayout = rowLayout;
        this.cityContext = cityContext;
        this.listener = listener;
        mDB = db;
        this.user = user;
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
                    intent.putExtra("cityname",  cityName.getText());
                    intent.putExtra( "username", user);
                    v.getContext().startActivity(intent);
                }
            });

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

        viewHolder.cityInfo.setText(String.valueOf(current.getCityNumber()));

        if(viewHolder.cityName.getText().equals("Скопје") ) {
            viewHolder.cityPic.setImageResource(R.drawable.skopje);
        }
        else if(viewHolder.cityName.getText().equals("Велес") ){
            viewHolder.cityPic.setImageResource(R.drawable.veles);
        }
        else if(viewHolder.cityName.getText().equals("Куманово")) {
            viewHolder.cityPic.setImageResource(R.drawable.kumanovo);
        }
        else if(viewHolder.cityName.getText().equals("Штип")) {
            viewHolder.cityPic.setImageResource(R.drawable.stip);
        }
        else if(viewHolder.cityName.getText().equals("Гевгелија")) {
            viewHolder.cityPic.setImageResource(R.drawable.gevgelija);
        }
        else if(viewHolder.cityName.getText().equals("Струмица")) {
            viewHolder.cityPic.setImageResource(R.drawable.strumica);
        }
        else if(viewHolder.cityName.getText().equals( "Битола") ){
            viewHolder.cityPic.setImageResource(R.drawable.bitola);
        }
        else if(viewHolder.cityName.getText().equals("Прилеп") ){
            viewHolder.cityPic.setImageResource(R.drawable.prilep);
        }
        else if(viewHolder.cityName.getText().equals( "Охрид")) {
            viewHolder.cityPic.setImageResource(R.drawable.ohrid);
        }
        else if(viewHolder.cityName.getText().equals("Гостивар")) {
            viewHolder.cityPic.setImageResource(R.drawable.gostivar);
        }
        else /*(viewHolder.cityName.getText().equals("Тетово"))*/ {
            viewHolder.cityPic.setImageResource(R.drawable.tetovo);
        }
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
