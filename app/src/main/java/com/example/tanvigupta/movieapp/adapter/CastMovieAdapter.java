package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.model.CastMovieDetail.Cast;
import com.example.tanvigupta.movieapp.network.MovieClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastMovieAdapter extends RecyclerView.Adapter<CastMovieViewHolder> {
    List<Cast> casts;
    Context context;
    MovieClickListener listener;

    public CastMovieAdapter(Context context,List<Cast> casts,MovieClickListener listener){
        this.casts=casts;
        this.context=context;
        this.listener=listener;

    }


    @NonNull
    @Override
    public CastMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.simiar_row_layout,parent,false);
        return new CastMovieViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final CastMovieViewHolder holder, int i) {
        Cast cast=casts.get(i);
        holder.title.setText(cast.getTitle());
        Picasso.get().load(Constants.IMAGE_BASE_URL+cast.getPosterPath()).into(holder.image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onmovieclicked(casts.get(holder.getAdapterPosition()).getId());
            }
        });





    }

    @Override
    public int getItemCount() {
        return casts.size();
    }
}
