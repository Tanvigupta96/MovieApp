package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class CastViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView character;
    ImageView castprofile;
    LinearLayout linearLayout;



    public CastViewHolder(View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.realname);
        character=itemView.findViewById(R.id.showname);
        castprofile=itemView.findViewById(R.id.posterimage);
        linearLayout=itemView.findViewById(R.id.linearlayout);




    }
}
