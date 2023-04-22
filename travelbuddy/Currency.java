package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class Currency extends AppCompatActivity {

    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        tb = findViewById(R.id.commontoolbar);
        tb.setTitle("Currency");
        setSupportActionBar(tb);
    }
}
