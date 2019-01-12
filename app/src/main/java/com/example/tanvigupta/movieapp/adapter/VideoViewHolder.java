package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    ImageView videoposter;
    TextView videotitle;
    CardView cardView;


    public VideoViewHolder(View itemView) {
        super(itemView);
        videoposter=itemView.findViewById(R.id.videoimage);
        videotitle=itemView.findViewById(R.id.videotitle);
        cardView=itemView.findViewById(R.id.cardview);


    }
}
