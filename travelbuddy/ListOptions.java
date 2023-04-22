package com.example.travelbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.travelbuddy.Modal.citymodal;
import com.example.travelbuddy.Modal.listoptionmodal;
import com.example.travelbuddy.ViewHolder.CityDetailsViewHolder;
import com.example.travelbuddy.ViewHolder.CityViewHolder;
import com.example.travelbuddy.ViewHolder.ListOptionViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListOptions extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Intent i;
    String str;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_options);

        i = getIntent();
        str = i.getStringExtra("cityname");

        tb = findViewById(R.id.commontoolbar);
        tb.setTitle(str);
        setSupportActionBar(tb);

        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Option");
        FirebaseRecyclerOptions<listoptionmodal> options = new FirebaseRecyclerOptions.Builder<listoptionmodal>()
                .setQuery(ref, listoptionmodal.class).build();
        FirebaseRecyclerAdapter<listoptionmodal, ListOptionViewHolder> adapter =
                new FirebaseRecyclerAdapter<listoptionmodal, ListOptionViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ListOptionViewHolder holder, int position, @NonNull final listoptionmodal model) {
                        Glide.with(ListOptions.this).load(model.getImage()).into(holder.img);
                        holder.name.setText(model.getName());
                        holder.c1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ListOptions.this,CityDetails.class);
                                i.putExtra("cityname",str);
                                i.putExtra("option",model.getKeyy());
                                i.putExtra("optionid",model.getName());
                                startActivity(i);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ListOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listoptioncardview,parent,false);
                        ListOptionViewHolder listOptionViewHolder = new ListOptionViewHolder(view);
                        return listOptionViewHolder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
