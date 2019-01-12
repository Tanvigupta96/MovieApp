package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class ReviewsViewHolder extends RecyclerView.ViewHolder {
    ImageView person;
    TextView name;
    TextView content;
    LinearLayout linearLayout;



    public ReviewsViewHolder(View itemView) {
        super(itemView);
        person=itemView.findViewById(R.id.person);
        name=itemView.findViewById(R.id.name);
        content=itemView.findViewById(R.id.content);
        linearLayout=itemView.findViewById(R.id.linearlayout);
    }
}
