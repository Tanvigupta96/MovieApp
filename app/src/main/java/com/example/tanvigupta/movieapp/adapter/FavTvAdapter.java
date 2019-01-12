package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.net.LinkAddress;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.DataBase.TvTable;
import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.network.TvShowClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavTvAdapter extends RecyclerView.Adapter<TvFavViewHolder> {
    Context context;
    List<TvTable> list;
    TvShowClickListener listener;

    public FavTvAdapter(Context context,List<TvTable> list,TvShowClickListener listener){
        this.context=context;
        this.list=list;
        this.listener=listener;
    }



    @NonNull
    @Override
    public TvFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.fav_row_layout,parent,false);
        return new TvFavViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvFavViewHolder holder, int position) {
        final TvTable result=list.get(position);
        Picasso.get().load(Constants.IMAGE_BASE_URL+result.getPosterPath()).into(holder.imageView);
        holder.textView.setText(result.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onshowclicked(list.get(holder.getAdapterPosition()).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
