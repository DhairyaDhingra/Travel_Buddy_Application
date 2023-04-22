package com.example.travelbuddy.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuddy.Interface.ItemClickListener;
import com.example.travelbuddy.R;

public class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView price;
    public ImageView img;
    public ItemClickListener itemClickListener;
    public CardView c1;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        price= itemView.findViewById(R.id.cityname);
        img = itemView.findViewById(R.id.cityimg);
        c1 = itemView.findViewById(R.id.c1);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.itemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }
}
