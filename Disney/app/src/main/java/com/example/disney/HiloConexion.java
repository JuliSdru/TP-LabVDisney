package com.example.disney;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class HiloConexion extends Thread{
    public Handler handler;
    private int position;
    private String urlString;
    private boolean isImage;
    private Context applicationContext;

    public HiloConexion(Handler handler, Context applicationContext, String url, int position, boolean isImage) {
        this.handler = handler;
        this.position = position;
        this.applicationContext = applicationContext;
        this.urlString = url;
        this.isImage = isImage;
    }

    @Override
    public void run(){

        ConexionHTTP con = new ConexionHTTP();
        byte[] imagesJson;
        //String json = con.obtenerRespuesta("https://api.disneyapi.dev/characters");
        //Message mensaje = new Message();
        if(isImage){
            try{
                imagesJson = con.obtenerRespuesta(urlString);
                Message msg = new Message();
                msg.obj = imagesJson;
                msg.arg1 = position;
                handler.sendMessage(msg);
            }catch (RuntimeException e){
                Message msg = new Message();
                msg.obj = null;
                msg.arg1 = position;
                handler.sendMessage(msg);
            }
        }else {

            byte[] disneyJson = con.obtenerRespuesta(urlString);

            String jsonString = new String(disneyJson);

            Message msg = new Message();
            msg.obj = this.parserJson(jsonString);

            handler.sendMessage(msg);

        }

    }

    public List<Disney> parserJson(String s) {

        List<Disney> disneyList = new ArrayList<>();

        try {

            JSONObject disneyListJson = new JSONObject(s);
            JSONArray articles = disneyListJson.getJSONArray("data");
            for (int i = 0; i < articles.length(); i++) {

                JSONObject jsonObject = articles.getJSONObject(i);

                if(!jsonObject.getString("imageUrl").equals("null")){

                    Disney disney = new Disney();

                    disney.setName(jsonObject.getString("name"));
                    disney.setFilms(jsonObject.getString("films"));
                    disney.setUrl(jsonObject.getString("url"));
                    disney.setSourceUrl(jsonObject.getString("sourceUrl"));
                    disney.setImageUrl(jsonObject.getString("imageUrl"));
                    disney.setCreatedAt(jsonObject.getString("createdAt"));
                    disney.setSaved(false);

                    disneyList.add(disney);
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return disneyList;

    }
}
