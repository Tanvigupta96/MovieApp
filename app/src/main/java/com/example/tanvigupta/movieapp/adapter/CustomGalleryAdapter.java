package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tanvigupta.movieapp.model.CastPhotos.Profile;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomGalleryAdapter extends BaseAdapter {
    private Context context;
    private List<Profile> profiles;

    public CustomGalleryAdapter(Context context,List<Profile> profiles){
        this.context=context;
        this.profiles=profiles;
    }

    @Override
    public int getCount() {
        return profiles.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Profile profile=profiles.get(position);
        ImageView imageView=new ImageView(context);
        Picasso.get().load(Constants.IMAGE_BASE_URL+profile.getFilePath()).resize(230,230).centerCrop().into(imageView);

        return imageView;
    }
}
