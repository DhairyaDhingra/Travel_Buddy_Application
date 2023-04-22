package com.example.travelbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegPage extends AppCompatActivity {

    EditText name,email,pass;
    Button regbtn;
    TextView regact;
    String strname,stremail,strpass;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_page);
        name = findViewById(R.id.name);
        email = findViewById(R.id.emailid);
        pass = findViewById(R.id.pass);
        regbtn = findViewById(R.id.regbtn);
        regact = findViewById(R.id.reg);

        tb = findViewById(R.id.commontoolbar);
        tb.setTitle("Registration");
        setSupportActionBar(tb);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        regact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void upload(){
        strname = name.getText().toString();
        stremail = email.getText().toString();
        strpass = pass.getText().toString();
        if(strpass.isEmpty() || stremail.isEmpty() || strname.isEmpty()){
            Toast.makeText(RegPage.this,"Invalid credentials",Toast.LENGTH_LONG).show();
        }
        else {
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("UserName",strname);
                    map.put("Contact",stremail);
                    map.put("Password",strpass);
                    ref.child("UserData").child(stremail).updateChildren(map)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegPage.this,"Registration successful",Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                }
                            });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}
