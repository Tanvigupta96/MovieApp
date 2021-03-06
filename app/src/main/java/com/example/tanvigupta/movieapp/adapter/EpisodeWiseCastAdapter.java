package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.model.EpisodeWiseCast.Cast;
import com.example.tanvigupta.movieapp.network.CastClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EpisodeWiseCastAdapter extends RecyclerView.Adapter<CastViewHolder> {
    List<Cast> casts;
    Context context;
    CastClickListener listener;

    public EpisodeWiseCastAdapter(Context context, List<Cast> casts,CastClickListener listener){
        this.casts=casts;
        this.context=context;
        this.listener=listener;
    }


    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.cast_row_layout,parent,false);
        return new CastViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final CastViewHolder holder, int position) {
        final Cast cast=casts.get(position);
        holder.character.setText(cast.getCharacter());
        holder.name.setText(cast.getName());
        Picasso.get().load(Constants.IMAGE_BASE_URL+cast.getProfilePath()).into(holder.castprofile);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.oncastclick(cast.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return casts.size();
    }
}
