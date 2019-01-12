package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class UpcomingMovieViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView poster;
    TextView releasedate;
    CardView cardView;
    ImageButton fav;


    public UpcomingMovieViewHolder(View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.movietitle);
        poster=itemView.findViewById(R.id.posterimage);
        releasedate=itemView.findViewById(R.id.releasedate);
        cardView=itemView.findViewById(R.id.cardview);
        fav=itemView.findViewById(R.id.fav);

        }
}
