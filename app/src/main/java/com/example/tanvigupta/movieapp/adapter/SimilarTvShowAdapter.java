package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.activity.TvShowDetailActivity;
import com.example.tanvigupta.movieapp.model.SimilarTvShows.Result;
import com.example.tanvigupta.movieapp.network.TvShowClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarTvShowAdapter extends RecyclerView.Adapter<SimilarrTvShowViewHolder> {
    List<Result> results;
    Context context;
    private TvShowDetailActivity.OnLoadMoreListener listener6;
    TvShowClickListener listener;


    public SimilarTvShowAdapter(Context context,List<Result> results,TvShowClickListener listener){
        this.context=context;
        this.results=results;
        this.listener=listener;
    }

    @NonNull
    @Override
    public SimilarrTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.simiar_row_layout,parent,false);
        return new SimilarrTvShowViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final SimilarrTvShowViewHolder holder, int i) {
        if (holder.getAdapterPosition() == results.size() - 5 && listener6 != null) {
            listener6.loadMore();
        }

        Result result=results.get(i);
        holder.name.setText(result.getName());

        Picasso.get().load(Constants.IMAGE_BASE_URL + result.getPosterPath()).into(holder.poster);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onshowclicked(results.get(holder.getAdapterPosition()).getId());
            }
        });



    }



    @Override
    public int getItemCount() {
        return results.size();
    }


    public void setOnLoadMoreListener(TvShowDetailActivity.OnLoadMoreListener listener) {
        this.listener6 = listener;
    }

    public void removeOnLoadMoreListener() {

        this.listener6 = null;
    }
}
