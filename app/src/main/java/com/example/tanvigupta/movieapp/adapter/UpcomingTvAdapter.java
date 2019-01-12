package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class UpcomingTvAdapter extends RecyclerView.Adapter<UpcomingTvViewHolder>{
    Context context;
    List<Result> upcomingTvShowResults;
    private TvShowClickListener listener;

    private TvShowsFragment.OnLoadMoreListener listener4;

    public UpcomingTvAdapter(Context context, List<Result> upcomingTvShowResults,TvShowClickListener listener){
        this.context=context;
        this.upcomingTvShowResults=upcomingTvShowResults;
        this.listener=listener;
    }


    @NonNull
    @Override
    public UpcomingTvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.row_layout,parent,false);
        return new UpcomingTvViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final UpcomingTvViewHolder holder, int i) {
        if (holder.getAdapterPosition() == upcomingTvShowResults.size() - 5 && listener4 != null) {
            listener4.loadMore();
        }

        Result results = upcomingTvShowResults.get(i);
        holder.name.setText(results.getName());
        Log.d("" + i, "onBindViewHolder: " + results.getFirstAirDate());
        if (results.getFirstAirDate() == null || results.getFirstAirDate().isEmpty()) {
            holder.date.setText("No release date");
        } else {
            holder.date.setText(results.getFirstAirDate());
        }
        Picasso.get().load(Constants.IMAGE_BASE_URL + results.getPosterPath()).into(holder.poster);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onshowclicked(upcomingTvShowResults.get(holder.getAdapterPosition()).getId());
            }
        });




    }


    public void setOnLoadMoreListener(TvShowsFragment.OnLoadMoreListener listener) {
        this.listener4 = listener;
    }

    public void removeOnLoadMoreListener() {
        this.listener4 = null;
    }

    @Override
    public int getItemCount() {
        return upcomingTvShowResults.size();
    }
}
