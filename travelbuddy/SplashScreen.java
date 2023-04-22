package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String share = "share";
    public static final String boolkey = "text2";
    boolean s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences = getSharedPreferences(share, MODE_PRIVATE);
        s = sharedPreferences.getBoolean(boolkey,false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(s) {
                    Intent i = new Intent(SplashScreen.this, CityPage.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(SplashScreen.this, LoginPage.class);
                    startActivity(i);
                }

            }
        },2500);
    }
}
