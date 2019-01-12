package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class PopularPeopleViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    ImageView photo;
    LinearLayout linearLayout;
    TextView knownfor;
    TextView known;
    CardView cardView;

    public PopularPeopleViewHolder(View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        photo=itemView.findViewById(R.id.image);
        linearLayout=itemView.findViewById(R.id.linearlayout);
        knownfor=itemView.findViewById(R.id.knownfor);
        known=itemView.findViewById(R.id.known);
        cardView=itemView.findViewById(R.id.cardview);

    }
}
