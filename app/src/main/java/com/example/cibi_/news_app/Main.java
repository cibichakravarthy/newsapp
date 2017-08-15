package com.example.cibi_.news_app;

import android.content.ClipData;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ImageView img = (ImageView)findViewById(R.id.images);
        //Picasso.with(this).load(Uri.parse("https://images.pexels.com/photos/534164/pexels-photo-534164.jpeg?w=940&h=650&auto=compress&cs=tinysrgb")).into(img);
        String ne = new String();
        try {
            ne = new RetrieveFeedTask().execute().get();
            if(ne == "ERROR") {
                Toast.makeText(this, (CharSequence) (ne), Toast.LENGTH_LONG).show();
                return;
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        } catch (ExecutionException e) {
           // e.printStackTrace();
        }
        JSONArray ji = new JSONArray();
        try {
            JSONObject js = new JSONObject(ne);
            ji = (js.getJSONArray("articles"));
            JSONObject p = ji.getJSONObject(0);
            //Toast.makeText(this,p.get("author").toString(),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
       RecyclerView yourListView = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager my = new LinearLayoutManager(this);
        yourListView.setLayoutManager(my);
        //String[] i ={"https://images.pexels.com/photos/534164/pexels-photo-534164.jpeg?w=940&h=650&auto=compress&cs=tinysrgb","https://static.pexels.com/photos/159056/dandelion-seeds-number-of-lion-screen-pilot-159056.jpeg"};

// get ata from the table by the ListAdapter
        ListAdapter customAdapter = new ListAdapter(ji);
        yourListView .setAdapter(customAdapter);

    }

        public void refresh(View view) {
            //Toast.makeText(this,"i am",Toast.LENGTH_SHORT).show();
        String ne = new String();
        try {
            ne = new RetrieveFeedTask().execute().get();
            if(ne == "ERROR") {
                Toast.makeText(this, (CharSequence) (ne), Toast.LENGTH_LONG).show();
                return;
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        } catch (ExecutionException e) {
            // e.printStackTrace();
        }
        JSONArray ji = new JSONArray();
        try {
            JSONObject js = new JSONObject(ne);
            ji = (js.getJSONArray("articles"));
            JSONObject p = ji.getJSONObject(0);
            //Toast.makeText(this,p.get("author").toString(),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecyclerView yourListView = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager my = new LinearLayoutManager(this);
        yourListView.setLayoutManager(my);
        //String[] i ={"https://images.pexels.com/photos/534164/pexels-photo-534164.jpeg?w=940&h=650&auto=compress&cs=tinysrgb","https://static.pexels.com/photos/159056/dandelion-seeds-number-of-lion-screen-pilot-159056.jpeg"};

// get ata from the table by the ListAdapter
        ListAdapter customAdapter = new ListAdapter(ji);
        yourListView .setAdapter(customAdapter);
        }
    }


