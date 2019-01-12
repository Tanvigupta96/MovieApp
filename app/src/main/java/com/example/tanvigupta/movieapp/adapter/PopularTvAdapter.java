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

public class PopularTvAdapter extends RecyclerView.Adapter<PopularTvViewHolder> {
    List<Result> popularTvResults;
    Context context;
    private TvShowsFragment.OnLoadMoreListener listener3;
    private TvShowClickListener listener;

    public PopularTvAdapter(Context context,List<Result> popularTvResults,TvShowClickListener listener){
        this.context=context;
        this.popularTvResults=popularTvResults;
        this.listener=listener;
    }


    @NonNull
    @Override
    public PopularTvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.row_layout,parent,false);
        return new PopularTvViewHolder(rowlayout);

    }

    @Override
    public void onBindViewHolder(@NonNull final PopularTvViewHolder holder, int i) {
        if (holder.getAdapterPosition() == popularTvResults.size() - 5 && listener3 != null) {
            listener3.loadMore();
        }
        Result results=popularTvResults.get(i);
        holder.name.setText(results.getName());
        if (results.getFirstAirDate() == null || results.getFirstAirDate().isEmpty()) {
            holder.date.setText("No release date");
        } else {
            holder.date.setText(results.getFirstAirDate());
        }
        Picasso.get().load(Constants.IMAGE_BASE_URL + results.getPosterPath()).into(holder.poster);




        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onshowclicked(popularTvResults.get(holder.getAdapterPosition()).getId());
            }
        });



    }


    public void setOnLoadMoreListener(TvShowsFragment.OnLoadMoreListener listener3) {
        this.listener3 = listener3;
    }

    public void removeOnLoadMoreListener() {

        this.listener3 = null;
    }


    @Override
    public int getItemCount() {
        return popularTvResults.size();
    }
}
