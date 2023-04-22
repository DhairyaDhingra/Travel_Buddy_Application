package com.example.travelbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    EditText emailid,pass;
    Button logbtn;
    TextView loginact;
    String name,email,password;
    Toolbar tb;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String share = "share";
    public static final String namekey = "text1";
    public static final String boolkey = "text2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        emailid = findViewById(R.id.emailid);
        pass = findViewById(R.id.pass);
        logbtn = findViewById(R.id.logbtn);
        loginact = findViewById(R.id.login);

        tb = findViewById(R.id.commontoolbar);
        tb.setTitle("Login");
        setSupportActionBar(tb);



        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        loginact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginPage.this,RegPage.class);
                startActivity(i);
            }
        });
    }
    private void upload(){
        email = emailid.getText().toString();
        password = pass.getText().toString();

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginPage.this,"Invalid credentials",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(LoginPage.this,"Please wait",Toast.LENGTH_SHORT).show();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child("UserData").child(email).exists()) {
                        Toast.makeText(LoginPage.this,"Login successful",Toast.LENGTH_SHORT).show();
                        sharedPreferences = getSharedPreferences(share, MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString(namekey,email);
                        editor.putBoolean(boolkey,true);
                        editor.apply();
                        Intent i = new Intent(LoginPage.this, CityPage.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(LoginPage.this,"User does not exist",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
