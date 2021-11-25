package com.example.disney;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyDisneyListActivity extends AppCompatActivity implements MyOnCardViewClick {

    List<Disney> listDisneyFav = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_disney_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("My Disney Fav");
        actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences mySavedDisney = getSharedPreferences("list_disney", Context.MODE_PRIVATE);
        mySavedDisney.edit();


        //recuperar noticias del sharedpreference
        RecyclerView list = (RecyclerView)findViewById(R.id.rvMyDisneyList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);



        String listSavedString = mySavedDisney.getString("list_disney", "");
        try {

            JSONArray jsonArray = new JSONArray(listSavedString);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Disney disneySaved = new Disney();
                disneySaved.setName(jsonObject.getString("name"));
                disneySaved.setFilms(jsonObject.getString("films"));
                disneySaved.setImageUrl(jsonObject.getString("urlToImage"));
                //disneySaved.setSourceUrl(jsonObject.getString("sourceUrl"));
                disneySaved.setCreatedAt(jsonObject.getString("createdAt"));
                disneySaved.setUrl(jsonObject.getString("url"));
                disneySaved.setSaved(true);

                listDisneyFav.add(disneySaved);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        DisneyAdapter adapter = new DisneyAdapter(listDisneyFav , this , getApplicationContext() );
        list.setAdapter(adapter);

    }

    @Override
    protected void onRestart() {

        Log.d("onRestart()" , "actualizar lista de noticias");

        List<Disney> listDisneyUpdated = new ArrayList<>();

        SharedPreferences mySavedDisney = getSharedPreferences("list_disney", Context.MODE_PRIVATE);
        mySavedDisney.edit();

        //recuperar noticias del sharedpreference
        RecyclerView list = (RecyclerView)findViewById(R.id.rvMyDisneyList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);


        String listSavedString = mySavedDisney.getString("list_disney", "");
        try {

            JSONArray jsonArray = new JSONArray(listSavedString);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Disney disneySaved = new Disney();
                disneySaved.setName(jsonObject.getString("name"));
                disneySaved.setFilms(jsonObject.getString("films"));
                disneySaved.setImageUrl(jsonObject.getString("urlToImage"));
                disneySaved.setSourceUrl(jsonObject.getString("sourceUrl"));
                disneySaved.setCreatedAt(jsonObject.getString("createdAt"));
                disneySaved.setUrl(jsonObject.getString("url"));
                disneySaved.setSaved(true);

                listDisneyUpdated.add(disneySaved);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        DisneyAdapter adapter = new DisneyAdapter(listDisneyUpdated , this , getApplicationContext() );
        list.setAdapter(adapter);

        super.onRestart();
    }

    //menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            super.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public void onCardViewClick(int position) {

        Disney disney = listDisneyFav.get(position);

        Intent i = new Intent(this, DisneyFullActivity.class);

        i.putExtra("disney" , (Serializable) disney);

        startActivity(i);

    }

}
