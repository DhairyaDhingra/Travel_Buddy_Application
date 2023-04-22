package com.example.travelbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.holderview> {

    private List<Model> mymodel;
    public Adapter(List<Model> mymodel) {
        this.mymodel = mymodel;
    }

    @NonNull
    @Override
    public holderview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new holderview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderview holder, int position) {
        final Model item=  mymodel.get(position);
        holder.t1.setText(item.getHeader());
//        holder.t2.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return mymodel.size();
    }

    public class holderview extends RecyclerView.ViewHolder {
        TextView t1,t2;
        public holderview(@NonNull View itemView) {
            super(itemView);
            t1= itemView.findViewById(R.id.h1);
            t2= itemView.findViewById(R.id.c1);
        }
    }
}
