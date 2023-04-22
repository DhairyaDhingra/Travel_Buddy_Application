package com.example.travelbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.travelbuddy.Modal.Itinerarymodal;
import com.example.travelbuddy.Modal.citymodal;
import com.example.travelbuddy.ViewHolder.CityViewHolder;
import com.example.travelbuddy.ViewHolder.ItineraryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Itinerary extends AppCompatActivity {

    Toolbar tb;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    SharedPreferences sharedPreferences;
    public static final String share = "share";
    public static final String namekey = "text1";
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        recyclerView = findViewById(R.id.recycle1);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sharedPreferences = getSharedPreferences(share, MODE_PRIVATE);
        str = sharedPreferences.getString(namekey,"");

        tb = findViewById(R.id.commontoolbar);
        tb.setTitle("Itinerary");
        setSupportActionBar(tb);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Details");
        FirebaseRecyclerOptions<Itinerarymodal> options = new FirebaseRecyclerOptions.Builder<Itinerarymodal>()
                .setQuery(ref, Itinerarymodal.class).build();
       FirebaseRecyclerAdapter<Itinerarymodal, ItineraryViewHolder> adapter
               = new FirebaseRecyclerAdapter<Itinerarymodal, ItineraryViewHolder>(options) {
           @Override
           protected void onBindViewHolder(@NonNull ItineraryViewHolder holder, int position, @NonNull Itinerarymodal model) {
               holder.cityname.setText(model.getName());
               Glide.with(Itinerary.this).load(model.getImage()).into(holder.img);
           }

           @NonNull
           @Override
           public ItineraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itinerarycardview,parent,false);
               ItineraryViewHolder itineraryViewHolder = new ItineraryViewHolder(view);
               return itineraryViewHolder;
           }
       };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
