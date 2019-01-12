package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;


public class EpisodesViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView epinumber;
    TextView epiname;
    LinearLayout Parent;

    public EpisodesViewHolder(View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.image);
        epiname=itemView.findViewById(R.id.epiname);
        epinumber=itemView.findViewById(R.id.epinumber);
        Parent=itemView.findViewById(R.id.Parent);


    }



}
