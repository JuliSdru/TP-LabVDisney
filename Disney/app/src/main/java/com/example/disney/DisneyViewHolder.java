package com.example.disney;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.disney.MyOnCardViewClick;
import com.example.disney.R;

public class DisneyViewHolder extends ViewHolder implements View.OnClickListener {

    private MyOnCardViewClick myOnCardViewClick;
    private int position;

    TextView txtName;
    //TextView txtDescription;
    ImageView imageDisney;

    public DisneyViewHolder(@NonNull View itemView, MyOnCardViewClick myOnCardViewClick) {
        super(itemView);
        txtName = itemView.findViewById(R.id.name);
        //txtDescription = itemView.findViewById(R.id.description);
        imageDisney = itemView.findViewById(R.id.imgDisney);

        this.myOnCardViewClick = myOnCardViewClick;
        itemView.setOnClickListener(this);
    }

    public void setPosition(int position){
        this.position = position;
    }

    public int getHolderPosition(){
        return this.position;
    }

    @Override
    public void onClick(View view) {
        myOnCardViewClick.onCardViewClick(position);
    }
}
