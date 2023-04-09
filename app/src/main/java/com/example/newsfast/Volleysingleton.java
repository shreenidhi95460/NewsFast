package com.example.newsfast;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Volleysingleton {
    private RequestQueue requestQueue;
    private static Volleysingleton mInstance;
    private Volleysingleton(Context context){
        requestQueue= Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized Volleysingleton getmInstance(Context context){
        if(mInstance==null)
            mInstance=new Volleysingleton(context);
        return  mInstance;
    }
    public RequestQueue getRequestQueue(){
        return  requestQueue;
    }
}
