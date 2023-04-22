package com.example.travelbuddy.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuddy.Interface.ItemClickListener;
import com.example.travelbuddy.R;

public class ItineraryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cityname;
    public ImageView img;
    public ItemClickListener itemClickListener;
    public CardView c1;
    public Button donebtn;

    public ItineraryViewHolder(@NonNull View itemView) {
        super(itemView);
        cityname = itemView.findViewById(R.id.cityname);
        img = itemView.findViewById(R.id.cityimg);
        donebtn = itemView.findViewById(R.id.donebtn);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.itemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }
}
