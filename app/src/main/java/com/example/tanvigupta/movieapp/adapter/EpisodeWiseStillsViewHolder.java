package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.tanvigupta.movieapp.R;

public class EpisodeWiseStillsViewHolder extends RecyclerView.ViewHolder {
    ImageView imgstill;


    public EpisodeWiseStillsViewHolder(View itemView) {
        super(itemView);
        imgstill=itemView.findViewById(R.id.imgstill);
    }
}
