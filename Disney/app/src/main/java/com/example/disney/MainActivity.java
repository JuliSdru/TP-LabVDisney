package com.example.disney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.os.Handler;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.io.Serializable;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MyOnCardViewClick, Handler.Callback  {

    List<Disney> disneyList;

    DisneyAdapter adapter;

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Disney");

        handler = new Handler(this);

        HiloConexion hiloDisney = new HiloConexion(handler, getApplicationContext(), "https://api.disneyapi.dev/characters", 0, false);
        hiloDisney.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {

        disneyList = (List<Disney>) message.obj;

        RecyclerView list = (RecyclerView) findViewById(R.id.rvDisney);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new DisneyAdapter(disneyList, this, getApplicationContext());
        list.setAdapter(adapter);


        return false;
    }

    @Override
    public void onCardViewClick(int position) {

        Disney disney = disneyList.get(position);

        Intent i = new Intent(this, DisneyFullActivity.class);

        i.putExtra("disney", (Serializable) disney);

        startActivity(i);

    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal, menu);

        //MenuItem menuItem = menu.findItem(R.id.buscar);

        //androidx.appcompat.widget.SearchView searchView = (SearchView) menuItem.getActionView();
        //searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

     /*   if (item.getItemId() == R.id.discover) {

            Log.d("Se hizo click", "Desplegar Dialog");

            MiDialog miDialog = new MiDialog( handler, getApplicationContext());

            miDialog.show(getSupportFragmentManager(), "dialogo");


            return true;
        }
*/
        if (item.getItemId() == R.id.my_disney) {

            Log.d("Favoritos", "mostrar mis favoritos");

            Intent i = new Intent(this, MyDisneyListActivity.class);
            startActivity(i);


        }

        return super.onOptionsItemSelected(item);
    }

    // buscador
    @Override
    public boolean onQueryTextSubmit(String query) {

        Log.d("onQueryTextSubmit()", "Buscar por palabra completa");

        HiloConexion hiloDisney = new HiloConexion(handler, getApplicationContext(), "https://api.disneyapi.dev/characters" + query, 0, false);
        hiloDisney.start();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        Log.d("onQueryTextChange()", "Buscar por coincidencia de caracteres");

        HiloConexion hiloDisney = new HiloConexion(handler, getApplicationContext(), "https://api.disneyapi.dev/characters" + newText, 0, false);
        hiloDisney.start();

        return true;
    }



}