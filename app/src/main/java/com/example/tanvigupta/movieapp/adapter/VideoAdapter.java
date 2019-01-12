package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.model.Videos.Result;
import com.example.tanvigupta.movieapp.network.VideoClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    List<Result> resultList;
    Context context;
    VideoClickListener listener;

    public VideoAdapter(Context context, List<Result> resultList, VideoClickListener listener){
        this.context=context;
        this.resultList=resultList;
        this.listener=listener;

    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.video_row_layout,parent,false);
        return new VideoViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, int i) {
       final Result result=resultList.get(i);
       holder.videotitle.setText(result.getName());
       String key=result.getKey();
       Log.d("TvShowVideoAdapter",key);
       Picasso.get().load(Constants.VIDEO_IMAGE_BASE_URL+result.getKey()+"/hqdefault.jpg").into(holder.videoposter);

       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.onvideoclicked(resultList.get(holder.getAdapterPosition()).getKey());

           }
       });


    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
