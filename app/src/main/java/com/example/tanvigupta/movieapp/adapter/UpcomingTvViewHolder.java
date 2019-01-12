package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class UpcomingTvViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView date;
    ImageView poster;
    CardView cardView;
    ImageButton fav;


    public UpcomingTvViewHolder(View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.movietitle);
        date=itemView.findViewById(R.id.releasedate);
        poster=itemView.findViewById(R.id.posterimage);
        cardView=itemView.findViewById(R.id.cardview);
        fav=itemView.findViewById(R.id.fav);


        }
}
