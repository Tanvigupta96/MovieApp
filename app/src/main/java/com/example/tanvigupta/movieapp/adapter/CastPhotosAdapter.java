package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.model.CastPhotos.Profile;
import com.example.tanvigupta.movieapp.network.PhotoClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastPhotosAdapter extends RecyclerView.Adapter<CastPhotosViewHolder> {
    List<Profile> profiles;
    Context context;
    PhotoClickListener listener;

    public CastPhotosAdapter(Context context,List<Profile> profiles,PhotoClickListener listener){
        this.context=context;
        this.profiles=profiles;
        this.listener=listener;
    }





    @NonNull
    @Override
    public CastPhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.photos_row_layout,parent,false);
        return  new CastPhotosViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CastPhotosViewHolder holder, int i) {
        final Profile profile=profiles.get(i);
        Picasso.get().load(Constants.IMAGE_BASE_URL+profile.getFilePath()).into(holder.photo);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onphotoclick(Constants.IMAGE_BASE_URL+profile.getFilePath());
            }
        });


    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
}
