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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.travelbuddy.Modal.citymodal;
import com.example.travelbuddy.ViewHolder.CityViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CityPage extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Toolbar tb;
    Intent i;

    SharedPreferences sharedPreferences;
    public static final String share = "share";
    public static final String namekey = "text1";
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_page);

        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sharedPreferences = getSharedPreferences(share, MODE_PRIVATE);
        str = sharedPreferences.getString(namekey,"");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("UserData").child(str);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb = findViewById(R.id.maintoolbar);
                tb.setTitle("Welcome, "+snapshot.child("UserName").getValue().toString());
                setSupportActionBar(tb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.maintbmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){

            case R.id.iti:
                i = new Intent(CityPage.this, Itinerary.class);
                startActivity(i);
                return true;

        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Details");
        FirebaseRecyclerOptions<citymodal> options = new FirebaseRecyclerOptions.Builder<citymodal>()
                .setQuery(ref, citymodal.class).build();
        FirebaseRecyclerAdapter<citymodal, CityViewHolder> adapter =
                new FirebaseRecyclerAdapter<citymodal, CityViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CityViewHolder holder, int position, @NonNull final citymodal model) {
                        Glide.with(CityPage.this).load(model.getImage()).into(holder.img);
                        holder.price.setText(model.getName());
                        holder.c1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(CityPage.this,ListOptions.class);
                                i.putExtra("cityname",model.getName());
                                startActivity(i);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.citycardview,parent,false);
                        CityViewHolder cityViewHolder = new CityViewHolder(view);
                        return cityViewHolder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        finishAffinity();
    }
}
