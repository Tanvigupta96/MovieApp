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
import com.example.tanvigupta.movieapp.fragments.MovieFragment;
import com.example.tanvigupta.movieapp.model.Movies.Result;
import com.example.tanvigupta.movieapp.network.MovieClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieViewHolder>{
    List<Result> upcomingResults;
    Context context;
    MovieFragment.OnLoadMoreListener listener2;
    MovieClickListener Listener;

    public  UpcomingMovieAdapter(Context context,List<Result> upcomingResults,MovieClickListener Listener){
        this.context=context;
        this.upcomingResults=upcomingResults;
        this.Listener=Listener;
    }


    @NonNull
    @Override
    public UpcomingMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.row_layout,parent,false);
        return new UpcomingMovieViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final UpcomingMovieViewHolder holder, int i) {
        if (holder.getAdapterPosition() == upcomingResults.size() - 5 && listener2 != null) {
            listener2.loadMore();
        }

        Result results = upcomingResults.get(i);
        holder.title.setText(results.getTitle());
        Log.d("" + i, "onBindViewHolder: " + results.getReleaseDate());
        if (results.getReleaseDate() == null || results.getReleaseDate().isEmpty()) {
            holder.releasedate.setText("No release date");
        } else {
            holder.releasedate.setText(results.getReleaseDate());
        }
        Picasso.get().load(Constants.IMAGE_BASE_URL + results.getPosterPath()).into(holder.poster);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listener.onmovieclicked(upcomingResults.get(holder.getAdapterPosition()).getId());
            }
        });


    }

    public void setOnLoadMoreListener(MovieFragment.OnLoadMoreListener listener) {
        this.listener2 = listener;
    }

    public void removeOnLoadMoreListener() {
        this.listener2 = null;
    }



    @Override
    public int getItemCount() {
        return upcomingResults.size() ;
    }
}
