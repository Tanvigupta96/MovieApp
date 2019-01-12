package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.fragments.TvShowsFragment;
import com.example.tanvigupta.movieapp.model.TvShows.Result;
import com.example.tanvigupta.movieapp.network.TvShowClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AiringTodayTvAdapter extends RecyclerView.Adapter<AiringTodayTvViewHolder> {

    Context context;
    List<Result> airingTodayTvResults;
    private TvShowsFragment.OnLoadMoreListener listener5;
    private TvShowClickListener listener;

    public AiringTodayTvAdapter(Context context, List<Result> airingTodayTvResults,TvShowClickListener listener){
        this.context=context;
        this.airingTodayTvResults=airingTodayTvResults;
        this.listener=listener;

    }



    @NonNull
    @Override
    public AiringTodayTvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowloayout=inflater.inflate(R.layout.airing_today_row_layout,parent,false);
        return new AiringTodayTvViewHolder(rowloayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final AiringTodayTvViewHolder holder, int i) {
        if (holder.getAdapterPosition() == airingTodayTvResults.size() - 5 && listener5!= null) {
            listener5.loadMore();
        }
        final Result results=airingTodayTvResults.get(i);

        holder.name.setText(results.getName());
        holder.rating.setText(results.getVoteAverage()+"");
        Picasso.get().load(Constants.IMAGE_BASE_URL + results.getBackdropPath()).into(holder.poster);




        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onshowclicked(airingTodayTvResults.get(holder.getAdapterPosition()).getId());
            }
        });




        }





    public void setOnLoadMoreListener(TvShowsFragment.OnLoadMoreListener listener) {
        this.listener5= listener;
    }

    public void removeOnLoadMoreListener() {

        this.listener5 = null;
    }







    @Override
    public int getItemCount() {
        return airingTodayTvResults.size();

    }
}
