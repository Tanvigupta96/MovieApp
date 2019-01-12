package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class SimilarrTvShowViewHolder extends RecyclerView.ViewHolder {
    ImageView poster;
    TextView name;
    LinearLayout linearLayout;
    ImageButton fav;



    public SimilarrTvShowViewHolder(View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.movietitle);
        poster=itemView.findViewById(R.id.posterimage);
        linearLayout=itemView.findViewById(R.id.linearlayout);
        fav=itemView.findViewById(R.id.fav);
    }
}
