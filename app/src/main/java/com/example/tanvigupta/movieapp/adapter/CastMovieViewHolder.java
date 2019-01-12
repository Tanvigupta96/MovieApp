package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class CastMovieViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView title;
    LinearLayout linearLayout;
    ImageButton fav;


    public CastMovieViewHolder(View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.movietitle);
        image=itemView.findViewById(R.id.posterimage);
        linearLayout=itemView.findViewById(R.id.linearlayout);
        fav=itemView.findViewById(R.id.fav);
    }

}
