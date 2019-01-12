package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.model.TvEpisodesDetail.Episode;
import com.example.tanvigupta.movieapp.network.OnEpisodeClickListener;

import java.util.List;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesViewHolder> {

    List<Episode> episodes;
    Context context;
    OnEpisodeClickListener listener;


    public EpisodesAdapter(Context context,List<Episode> episodes,OnEpisodeClickListener listener){
        this.context=context;
        this.episodes=episodes;
        this.listener=listener;
    }

    @NonNull
    @Override
    public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.episodes_row_layout,parent,false);
        return new EpisodesViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final EpisodesViewHolder holder, int position) {
        final Episode episode=episodes.get(position);
        holder.epinumber.setText(episode.getEpisodeNumber()+".");
        holder.epiname.setText(episode.getName());
        final String overview=episode.getOverview();
        final String airdate=episode.getAirDate();
        final String title=episode.getName();
        final Double voteavg=episode.getVoteAverage();

        holder.Parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onepisodeclick(episode.getEpisodeNumber(),airdate,overview,title,voteavg);
            }
        });

    }



    @Override
    public int getItemCount() {
        return episodes.size();
    }
}
