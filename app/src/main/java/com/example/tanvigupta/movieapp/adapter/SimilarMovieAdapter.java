package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.activity.MovieDetailActivity;
import com.example.tanvigupta.movieapp.model.SimilarMovie.Result;
import com.example.tanvigupta.movieapp.network.MovieClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarMovieAdapter extends RecyclerView.Adapter<SimilarMovieViewHolder> {
   List<com.example.tanvigupta.movieapp.model.SimilarMovie.Result> results;
   Context context;
    private MovieDetailActivity.OnLoadMoreListener listener7;
    MovieClickListener listener;

   public SimilarMovieAdapter(Context context, List<com.example.tanvigupta.movieapp.model.SimilarMovie.Result> results, MovieClickListener listener){
       this.context=context;
       this.results=results;
       this.listener=listener;
   }

   @NonNull
    @Override
    public SimilarMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View rowlayout=inflater.inflate(R.layout.simiar_row_layout,parent,false);
       return new SimilarMovieViewHolder(rowlayout);

    }

    @Override
    public void onBindViewHolder(@NonNull final SimilarMovieViewHolder holder, int i) {
        if (holder.getAdapterPosition() == results.size() - 5 && listener7 != null) {
            listener7.loadMore();
        }

        Result result=results.get(i);
        holder.title.setText(result.getTitle());

        Picasso.get().load(Constants.IMAGE_BASE_URL + result.getPosterPath()).into(holder.poster);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onmovieclicked(results.get(holder.getAdapterPosition()).getId());
            }
        });



    }








    @Override
    public int getItemCount() {
        return results.size();
    }



    public void setOnLoadMoreListener(MovieDetailActivity.OnLoadMoreListener listener) {
        this.listener7= listener;
    }

    public void removeOnLoadMoreListener() {

        this.listener7 = null;
    }
}
