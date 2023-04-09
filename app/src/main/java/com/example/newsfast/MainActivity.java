package com.example.newsfast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
public RecyclerView recyclerView;
public  RequestQueue requestQueue;
public String url="https://saurav.tech/NewsAPI/everything/cnn.json";
    public  ArrayList<news> newslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue= Volleysingleton.getmInstance(this).getRequestQueue();
        newslist=new ArrayList<>();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray=response.getJSONArray("articles");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        news ns=new news(
                                jsonObject.getString("title"),
                                jsonObject.getString("author"),
                                jsonObject.getString("url"),
                                jsonObject.getString("urlToImage")
                        );

                        newslist.add(ns);
                    }
                    recyclerviewadapter rva=new recyclerviewadapter(MainActivity.this,newslist);

                    recyclerView.setAdapter(rva);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Network problem",Toast.LENGTH_LONG).show();
                Log.d("Main", error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);



    }

}