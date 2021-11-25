package com.example.disney;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

public class DisneyAdapter extends Adapter<DisneyViewHolder> implements Handler.Callback {
    Disney disney;
    List<Disney> disneyList;
    MyOnCardViewClick myOnCardViewClick;

    Context context;
    Handler handler;
    private DisneyViewHolder holder;

    public DisneyAdapter(List<Disney> disneyList, MyOnCardViewClick myOnCardViewClick, Context applicationContext) {

        this.disneyList = disneyList;
        this.myOnCardViewClick = myOnCardViewClick;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public DisneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.disney_item , parent ,false);

        DisneyViewHolder disneyViewHolder = new DisneyViewHolder(view , myOnCardViewClick);

        return disneyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisneyViewHolder holder, int position) {

        this.holder = holder;

        disney = disneyList.get(position);
        holder.txtName.setText(disney.getName());
        //holder.txtDescription.setText(news.getDescription());


        handler = new Handler(this);
        HiloConexion hiloImages = new HiloConexion(handler , context , disney.getImageUrl() , position , true);
        hiloImages.start();


        holder.setPosition(position);

    }

    @Override
    public int getItemCount() {
        return disneyList.size();
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {

        if (message.obj != null){
            byte[] img = (byte[]) message.obj;

            if (message.arg1 == holder.getHolderPosition()){
                holder.imageDisney.setImageBitmap(BitmapFactory.decodeByteArray(img , 0 , img.length));
                disney.setDisneyImage(img);
            }

        }

        return false;
    }

}
