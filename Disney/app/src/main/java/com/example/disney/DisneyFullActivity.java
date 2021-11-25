package com.example.disney;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisneyFullActivity extends AppCompatActivity {

    Disney disney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disney_full);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Personajes de Disney");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();


        disney= (Disney) intent.getSerializableExtra("disney");

        WebView webview = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(disney.getSourceUrl());


        FloatingActionButton sharedButton = findViewById(R.id.floating_action_button);
        sharedButton.setOnClickListener(view -> {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareUrl = disney.getUrl();
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Url");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareUrl);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.finish();
        }

        if (item.getItemId() == R.id.save_disney) {
            Log.d("Save", "guardar en shared preference");

            saveDisey(disney);

        }

        if (item.getItemId() == R.id.delete_disney) {
            Log.d("Delete", "eliminar del shared preference");

            deleteDisney(disney);

        }

        return super.onOptionsItemSelected(item);
    }


    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (disney.isSaved()) {
            getMenuInflater().inflate(R.menu.manu_my_disney, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_disney, menu);
        }

        return true;
    }

    private void saveDisey(Disney disney) {

        SharedPreferences MySavedDisney = getSharedPreferences("list_disney", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = MySavedDisney.edit();

        List<Disney> listUpdated = new ArrayList<>();
        String listDisneyString = MySavedDisney.getString("list_disney", "");

        if (listDisneyString.equals("")) {
            disney.setSaved(true);
            listUpdated.add(disney);
        } else {
            try {
                JSONArray jsonArray = new JSONArray(listDisneyString);
                boolean disneyAlreadySaved = false;
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (jsonObject.getString("url").equals(disney.getUrl())) {
                        disneyAlreadySaved = true;
                    }

                    Disney disneyToLoad = new Disney();
                    disneyToLoad.setName(jsonObject.getString("name"));
                    disneyToLoad.setFilms(jsonObject.getString("films"));
                    disneyToLoad.setImageUrl(jsonObject.getString("urlToImage"));
                    disneyToLoad.setSourceUrl(jsonObject.getString("sourceUrl"));
                    disneyToLoad.setCreatedAt(jsonObject.getString("createdAt"));
                    disneyToLoad.setUrl(jsonObject.getString("url"));
                    disneyToLoad.setSaved(true);

                    listUpdated.add(disneyToLoad);

                }

                if (!disneyAlreadySaved) {
                    listUpdated.add(disney);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        editor.putString("list_disney", listUpdated.toString());

        editor.commit();

    }

    private void deleteDisney(Disney disney) {

        SharedPreferences MySavedDisney = getSharedPreferences("list_disney", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = MySavedDisney.edit();

        List<Disney> listUpdated = new ArrayList<>();
        String listDisneyString = MySavedDisney.getString("list_disney", "");

        if (listDisneyString.equals("")) {
            disney.setSaved(true);
            listUpdated.add(disney);
        } else {
            try {
                JSONArray jsonArray = new JSONArray(listDisneyString);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (!jsonObject.getString("url").equals(disney.getUrl())) {
                        //Disney DisneyToLoad = new Disney();
                        Disney disneyToLoad = new Disney();
                        disneyToLoad.setName(jsonObject.getString("name"));
                        disneyToLoad.setFilms(jsonObject.getString("films"));
                        disneyToLoad.setImageUrl(jsonObject.getString("urlToImage"));
                        disneyToLoad.setSourceUrl(jsonObject.getString("sourceUrl"));
                        disneyToLoad.setCreatedAt(jsonObject.getString("createdAt"));
                        disneyToLoad.setUrl(jsonObject.getString("url"));
                        disneyToLoad.setSaved(true);

                        listUpdated.add(disneyToLoad);
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        editor.putString("list_disney", listUpdated.toString());

        editor.commit();
        editor.apply();

    }



}
