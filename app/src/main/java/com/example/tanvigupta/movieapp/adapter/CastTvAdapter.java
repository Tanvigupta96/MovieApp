package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.model.CastTvDetail.Cast;

import com.example.tanvigupta.movieapp.network.TvShowClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastTvAdapter extends RecyclerView.Adapter<CastTvViewHolder> {
    List<Cast> casts;
    Context context;
    TvShowClickListener listener;


    public CastTvAdapter(Context context, List<Cast> casts, TvShowClickListener listener) {
        this.casts = casts;
        this.context = context;
        this.listener = listener;


    }


    @NonNull
    @Override
    public CastTvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = inflater.inflate(R.layout.simiar_row_layout, parent, false);
        return new CastTvViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final CastTvViewHolder holder, int i) {
        final Cast cast = casts.get(i);

        holder.title.setText(cast.getTitle());
        Picasso.get().load(Constants.IMAGE_BASE_URL + cast.getPosterPath()).into(holder.image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onshowclicked(casts.get(holder.getAdapterPosition()).getId());
            }
        });




    }

    @Override
    public int getItemCount() {
        return casts.size();
    }
}
