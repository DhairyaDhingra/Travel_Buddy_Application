package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomePage extends AppCompatActivity {

//    String url="https://raw.githubusercontent.com/David-Haim-zz/CountriesToCitiesJSON/master/countriesToCities.json";

    RecyclerView recyclerView;
    Adapter adapter;
    List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView=findViewById(R.id.recycle);

//        list = new ArrayList<>();
//        adapter = new Adapter(list);
//        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);

    }

//    public void getdata(){
//        RequestQueue queue= Volley.newRequestQueue(this);
//        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(HomePage.this,"Response : "+response,Toast.LENGTH_LONG).show();
//
//                try {
//                    JSONObject object=new JSONObject(response);
//                    JSONArray array= object.getJSONArray("China");
//
//                    for(int i=0;i<=1;i++){
////                        JSONObject obj= array.getJSONObject(i);
//////                        Toast.makeText(MainActivity.this,"Obj : "+obj.getString("id"),Toast.LENGTH_LONG).show();
////                        Model model=new Model(array[i]);
////                        list.add(array);
//
//                    }
//                    recyclerView.setAdapter(adapter);
//                    recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        queue.add(request);
//
//    }

}
