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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.travelbuddy.Modal.citydetailsmodal;
import com.example.travelbuddy.Modal.citymodal;
import com.example.travelbuddy.ViewHolder.CityDetailsViewHolder;
import com.example.travelbuddy.ViewHolder.CityViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CityDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String str,str1,str2,str100;
    Intent i;
    SharedPreferences sharedPreferences;
    public static final String share = "share";
    public static final String namekey = "text1";
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        i = getIntent();
        str = i.getStringExtra("cityname");
        str1 = i.getStringExtra("option");
        str2 = i.getStringExtra("optionid");

        tb = findViewById(R.id.commontoolbar);
        tb.setTitle(str2);
        setSupportActionBar(tb);

        sharedPreferences = getSharedPreferences(share, MODE_PRIVATE);
        str100 = sharedPreferences.getString(namekey,"");

        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("CityData").child(str).child(str1);
        FirebaseRecyclerOptions<citydetailsmodal> options = new FirebaseRecyclerOptions.Builder<citydetailsmodal>()
                .setQuery(ref, citydetailsmodal.class).build();
        FirebaseRecyclerAdapter<citydetailsmodal, CityDetailsViewHolder> adapter = new FirebaseRecyclerAdapter<citydetailsmodal, CityDetailsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CityDetailsViewHolder holder, int position, @NonNull final citydetailsmodal model) {
                if(str1.equals("Attraction") || str1.equals("Local")) {
                    holder.cityprice.setVisibility(View.GONE);
                    holder.cityweb.setVisibility(View.GONE);
                }
                else if(str1.equals("Restaurant")){
                    holder.cityweb.setVisibility(View.GONE);
                }
                Glide.with(CityDetails.this).load(model.getImage()).into(holder.cityimg);
                holder.cityname.setText(model.getName());
                holder.cityweb.setText(model.getWebsite());
                holder.cityloc.setText(model.getLocation());
                holder.cityprice.setText("\u20B9"+model.getPrice());
                holder.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("UserData");
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                final DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference();
                                final DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference();
                                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        HashMap<String,Object> map = new HashMap<>();
                                        map.put("name",model.getName());
                                        map.put("image",model.getImage());
                                        ref2.child("Itinerary").child(str100).updateChildren(map)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()) {
                                                            Toast.makeText(CityDetails.this,"Added Successfully",Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
            }

            @NonNull
            @Override
            public CityDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.citydetailscardview,parent,false);
                CityDetailsViewHolder cityDetailsViewHolder = new CityDetailsViewHolder(view);
                return cityDetailsViewHolder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
