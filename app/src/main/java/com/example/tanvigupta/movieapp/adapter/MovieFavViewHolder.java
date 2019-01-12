package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class MovieFavViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    View itemView;
    ImageButton button;



    public MovieFavViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        imageView=itemView.findViewById(R.id.image1);
        textView=itemView.findViewById(R.id.text);
        button=itemView.findViewById(R.id.filled);
        }
}
