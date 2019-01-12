package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.model.EpisodeStills.Still;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EpisodeWiseStillsAdapter extends RecyclerView.Adapter<EpisodeWiseStillsViewHolder> {
    List<Still> stills;
    Context context;


    public EpisodeWiseStillsAdapter(Context context, List<Still> stills){
        this.context=context;
        this.stills=stills;
    }


    @NonNull
    @Override
    public EpisodeWiseStillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.episodestills_row_layout,parent,false);
        return new EpisodeWiseStillsViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeWiseStillsViewHolder holder, int position) {
        Still still=stills.get(position);
        Picasso.get().load(Constants.IMAGE_BASE_URL+still.getFilePath()).into(holder.imgstill);


    }

    @Override
    public int getItemCount() {
        return stills.size();
    }
}
