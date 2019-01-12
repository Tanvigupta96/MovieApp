package com.example.tanvigupta.movieapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

public class CastPhotosViewHolder extends RecyclerView.ViewHolder {
    ImageView photo;
    LinearLayout ll;




    public CastPhotosViewHolder(View itemView) {
        super(itemView);
        photo=itemView.findViewById(R.id.photo);
        ll=itemView.findViewById(R.id.ll);

    }
}
