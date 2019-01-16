package com.example.tanvigupta.movieapp.fragments;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.DataBase.MovieDatabase;
import com.example.tanvigupta.movieapp.DataBase.TvTable;
import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.activity.TvShowDetailActivity;
import com.example.tanvigupta.movieapp.adapter.FavTvAdapter;
import com.example.tanvigupta.movieapp.network.TvShowClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFavourites extends Fragment {
    MovieDatabase movieDatabase;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    FavTvAdapter adapter;
    List<TvTable> list=new ArrayList<>();


    public TvFavourites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tv_favourites, container, false);
        movieDatabase= Room.databaseBuilder(view.getContext(),MovieDatabase.class,"movie_db").allowMainThreadQueries().build();

        list=movieDatabase.getDao().getTv();
        if(list.size()!=0){
            Log.i("room",list.size()+"");
            recyclerView=view.findViewById(R.id.favtv);
            adapter=new FavTvAdapter(view.getContext(), list, new TvShowClickListener() {
                @Override
                public void onshowclicked(long id) {
                    Intent intent=new Intent(getContext(), TvShowDetailActivity.class);
                    intent.putExtra("ID",id);
                    startActivity(intent);
                }
            });

            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));


            gridLayoutManager = new GridLayoutManager(getContext(),3, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);


        }



        return view;
    }


}
