package com.example.tanvigupta.movieapp.adapter;

import android.content.ReceiverCallNotAllowedException;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

import org.w3c.dom.Text;

public class SimilarMovieViewHolder extends RecyclerView.ViewHolder {

    ImageView poster;
    TextView title;
    LinearLayout linearLayout;
    ImageButton fav;


    public SimilarMovieViewHolder(View itemView) {
        super(itemView);
        poster=itemView.findViewById(R.id.posterimage);
        title=itemView.findViewById(R.id.movietitle);
        linearLayout=itemView.findViewById(R.id.linearlayout);
        fav=itemView.findViewById(R.id.fav);
    }
}
