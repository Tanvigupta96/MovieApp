package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class TopRatedMovieViewHolder extends RecyclerView.ViewHolder {

    ImageView posterimage;
    TextView title;
    TextView releasedate;
    CardView cardView;
    ImageButton fav;

    public TopRatedMovieViewHolder(View itemView) {
        super(itemView);
        posterimage=itemView.findViewById(R.id.posterimage);
        title=itemView.findViewById(R.id.movietitle);
        releasedate=itemView.findViewById(R.id.releasedate);
        cardView=itemView.findViewById(R.id.cardview);
        fav=itemView.findViewById(R.id.fav);
    }
}
