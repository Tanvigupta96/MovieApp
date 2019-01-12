package com.example.tanvigupta.movieapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tanvigupta.movieapp.fragments.MovieFavourites;
import com.example.tanvigupta.movieapp.fragments.TvFavourites;

public class FavouritePagerAdapter extends FragmentPagerAdapter {


    public FavouritePagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        if(i==0)
        {
            return new MovieFavourites();

        }
        else if(i==1){
            return new TvFavourites();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
