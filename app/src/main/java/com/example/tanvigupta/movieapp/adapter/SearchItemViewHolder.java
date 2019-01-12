package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class SearchItemViewHolder extends RecyclerView.ViewHolder {
    ImageView poster;
    TextView title;
    CardView cardView;
    TextView type;
    TextView content;




    public SearchItemViewHolder(View itemView) {
        super(itemView);
       poster=itemView.findViewById(R.id.posterimage);
       title=itemView.findViewById(R.id.movietitle);
       cardView=itemView.findViewById(R.id.cardview);
       type=itemView.findViewById(R.id.type);
       content=itemView.findViewById(R.id.content);
    }
}
