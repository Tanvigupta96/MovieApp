package com.example.tanvigupta.movieapp.fragments;


import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.DataBase.MovieDatabase;
import com.example.tanvigupta.movieapp.DataBase.MovieTable;
import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.activity.MovieDetailActivity;
import com.example.tanvigupta.movieapp.activity.TvShowDetailActivity;
import com.example.tanvigupta.movieapp.adapter.FavMovieAdapter;
import com.example.tanvigupta.movieapp.network.MovieClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavourites extends Fragment {
    MovieDatabase movieDatabase;
   GridLayoutManager gridLayoutManager;

    RecyclerView recyclerView;
    FavMovieAdapter favMovieAdapter;
    RelativeLayout rv;


    List<MovieTable> movieTables= new ArrayList<>();


    public MovieFavourites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_favourites, container, false);
        movieDatabase= Room.databaseBuilder(view.getContext(),MovieDatabase.class,"movie_db").allowMainThreadQueries().build();





        final List<MovieTable> list= movieDatabase.getDao().getMovies();


        if(list.size()!=0) {

            Log.i("room",list.size()+"");
            recyclerView = view.findViewById(R.id.favmovies);


            favMovieAdapter = new FavMovieAdapter(view.getContext(), list, new MovieClickListener() {
                @Override
                public void onmovieclicked(long id) {
                    Intent intent=new Intent(getContext(), MovieDetailActivity.class);
                    intent.putExtra("ID",id);
                    startActivity(intent);

                }
            });


            recyclerView.setAdapter(favMovieAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));


            gridLayoutManager = new GridLayoutManager(getContext(),3, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);


        }


        return view;
    }

    }

