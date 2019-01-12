package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class AiringTodayTvViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    ImageView poster;
    TextView rating;
    ImageView star;
    CardView cardView;
    ImageButton fav;
    TextView type;

    public AiringTodayTvViewHolder(View itemView) {

        super(itemView);
        name=itemView.findViewById(R.id.movietitle);
        poster=itemView.findViewById(R.id.posterimage);
        rating=itemView.findViewById(R.id.ratings);
        star=itemView.findViewById(R.id.star);
        cardView=itemView.findViewById(R.id.cardview);
        fav=itemView.findViewById(R.id.fav);
        type=itemView.findViewById(R.id.type);


    }
}
