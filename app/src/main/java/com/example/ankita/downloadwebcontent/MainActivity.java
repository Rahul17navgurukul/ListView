package com.example.ankita.downloadwebcontent;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.*;

public class MainActivity extends AppCompatActivity {

    //URL of having json
  private static final String JSON_URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";


//listView object
    ListView listView;

    // hero list where we store all  hero object while parsing json
    List<Hero> heroList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //initialise listView and hero list
        listView = (ListView) findViewById(R.id.listView);
        heroList = new ArrayList<>();

        // this method fetch and pass the data
        loadHeroList();
    }

    private void loadHeroList() {
        //getting the progressBar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Making progressBar visible
        progressBar.setVisibility(View.VISIBLE);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//hidden the progressBar after completion
                progressBar.setVisibility(View.INVISIBLE);


                try {
// getting all the json object from the response
                    JSONObject obj = new JSONObject(response);

                    //We have a array named hero inside the object
                    // so here we getting that json array
                    JSONArray heroArray = obj.getJSONArray("heroes");

                    //now looping through all the elements of the json array
                    for (int i = 0; i < heroArray.length(); i++) {

                        //getting the json object of the particular index inside the array
                        JSONObject heroObject = heroArray.getJSONObject(i);

                        //creating a hero object and giving them the values from json object
                        Hero hero = new Hero(heroObject.getString("name"), heroObject.getString("imageurl"));

                        //adding the hero to heroList
                        heroList.add(hero);

                    }
                    //creating custom adapter object
                    ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());

                    //adding the adapter to listview
                    listView.setAdapter((ListAdapter) adapter);

                } catch (JSONException e) {
//                    e.printStackTrace();
//                    catch (Exception e) {
                    Log.e("MYAPP", "exception", e);

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurs
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //creating a request queue
//RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
//requestQueue.add(stringRequest);
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.add(stringRequest);

    }
}