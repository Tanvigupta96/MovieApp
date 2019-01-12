package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.fragments.MovieFragment;
import com.example.tanvigupta.movieapp.network.MovieClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopRatedMovieAdapter extends RecyclerView.Adapter<TopRatedMovieViewHolder>{
    List<com.example.tanvigupta.movieapp.model.Movies.Result> topresults;
    Context context;
    private MovieFragment.OnLoadMoreListener listener1;
    private MovieClickListener Listener;

   public TopRatedMovieAdapter(Context context, List<com.example.tanvigupta.movieapp.model.Movies.Result> results, MovieClickListener Lisener){
        this.context=context;
        this.topresults=results;
        this.Listener=Lisener;

    }

    @NonNull
    @Override
    public TopRatedMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.row_layout,parent,false);
        return new TopRatedMovieViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopRatedMovieViewHolder holder, int i) {
        if (holder.getAdapterPosition() == topresults.size() - 5 && listener1!= null) {
            listener1.loadMore();
        }


        com.example.tanvigupta.movieapp.model.Movies.Result ratedResults=topresults.get(i);
        holder.title.setText(ratedResults.getTitle());
        if (ratedResults.getReleaseDate()== null || ratedResults.getReleaseDate().isEmpty()) {
            holder.releasedate.setText("No release date");
        } else {
            holder.releasedate.setText(ratedResults.getReleaseDate());
        }
        Picasso.get().load(Constants.IMAGE_BASE_URL + ratedResults.getPosterPath()).into(holder.posterimage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Listener.onmovieclicked(topresults.get(holder.getAdapterPosition()).getId());
            }
        });



    }

    public void setOnLoadMoreListener(MovieFragment.OnLoadMoreListener listener1) {
        this.listener1 = listener1;
    }

    public void removeOnLoadMoreListener() {
        this.listener1= null;
    }


    @Override
    public int getItemCount() {
        return topresults.size();
    }
}
