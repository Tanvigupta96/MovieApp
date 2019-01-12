package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.fragments.MovieFragment;
import com.example.tanvigupta.movieapp.model.Movies.Result;
import com.example.tanvigupta.movieapp.network.MovieClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.example.tanvigupta.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.tanvigupta.movieapp.R.drawable.*;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieViewHolder> {
    private List<Result> popularmovies;
    private Context context;
    private MovieFragment.OnLoadMoreListener listener;
    private MovieClickListener Listener;


    public PopularMovieAdapter(Context context, List<Result> popularmovies, MovieClickListener Listener){
        this.context = context;
        this.popularmovies = popularmovies;
        this.Listener=Listener;

    }


    @NonNull
    @Override
    public PopularMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new PopularMovieViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final PopularMovieViewHolder holder, int i) {
        if (holder.getAdapterPosition() == popularmovies.size() - 5 && listener != null) {
            listener.loadMore();
        }

        Result results = popularmovies.get(i);
        holder.title.setText(results.getTitle());
        Log.d("" + i, "onBindViewHolder: " + results.getReleaseDate());
        if (results.getReleaseDate() == null || results.getReleaseDate().isEmpty()) {
            holder.releasedate.setText("No release date");
        } else {
            holder.releasedate.setText(results.getReleaseDate());
        }
        Picasso.get().load(Constants.IMAGE_BASE_URL + results.getPosterPath()).into(holder.posterimage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listener.onmovieclicked(popularmovies.get(holder.getAdapterPosition()).getId());
            }
        });



    }

    public void setOnLoadMoreListener(MovieFragment.OnLoadMoreListener listener) {
        this.listener = listener;
    }

    public void removeOnLoadMoreListener() {
        this.listener = null;
    }

    @Override
    public int getItemCount() {
        return popularmovies.size();
    }
}
