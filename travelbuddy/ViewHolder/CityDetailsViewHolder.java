package com.example.travelbuddy.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuddy.Interface.ItemClickListener;
import com.example.travelbuddy.R;

public class CityDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cityname,cityloc,cityweb,cityprice;
    public ImageView cityimg;
    public Button btn;

    public ItemClickListener itemClickListener;

    public CityDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        cityname = itemView.findViewById(R.id.cityname);
        cityloc = itemView.findViewById(R.id.cityloc);
        cityweb = itemView.findViewById(R.id.cityweb);
        cityimg = itemView.findViewById(R.id.cityimg);
        cityprice = itemView.findViewById(R.id.cityprice);
        btn = itemView.findViewById(R.id.btn);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.itemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }
}
