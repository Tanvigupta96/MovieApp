package com.example.tanvigupta.movieapp.activity;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.fragments.AboutUsFragment;
import com.example.tanvigupta.movieapp.fragments.FavouritesFragment;
import com.example.tanvigupta.movieapp.fragments.MovieFragment;
import com.example.tanvigupta.movieapp.fragments.PopularPeopleFragment;
import com.example.tanvigupta.movieapp.fragments.TvShowsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, TvShowsFragment.onDataPass, MovieFragment.onDataPass, PopularPeopleFragment.onDataPass {
    FrameLayout frameLayout;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    MovieFragment movieFragment=new MovieFragment();
    TvShowsFragment tvShowsFragment=new TvShowsFragment();
    FavouritesFragment favouritesFragment=new FavouritesFragment();
    AboutUsFragment aboutUsFragment=new AboutUsFragment();
    PopularPeopleFragment popularPeopleFragment=new PopularPeopleFragment();
    FragmentManager manager1 = getSupportFragmentManager();
    FragmentTransaction transaction1 = manager1.beginTransaction();
    //SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.container);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottomNav);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().
        setTitle("Movies");

        bottomNavigationView.setOnNavigationItemSelectedListener(this);


        //movieFragment = new MovieFragment();
        transaction1.replace(R.id.container, movieFragment);
        transaction1.commit();
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (item.getItemId()) {
            case R.id.movies:
                getSupportActionBar().setTitle("Movies");
                transaction.replace(R.id.container, movieFragment);


                break;

            case R.id.tvshows:
                getSupportActionBar().setTitle("Tv Shows");

                transaction.replace(R.id.container, tvShowsFragment);

                break;

            case R.id.favourites:
                getSupportActionBar().setTitle("Favourites");

                transaction.replace(R.id.container, favouritesFragment);
                break;

            case R.id.popularpeople:
                getSupportActionBar().setTitle("Popular People");

                transaction.replace(R.id.container,popularPeopleFragment);
                break;

            case R.id.aboutus:
                getSupportActionBar().setTitle("About");

                transaction.replace(R.id.container,aboutUsFragment);
                break;
        }
        transaction.commit();

        return true;
    }


    @Override
    public void onDataPass(long data) {
        Bundle bundle = new Bundle();
        bundle.putLong("ID", data);
        Log.d("MainActivity", data + "");
        Intent intent = new Intent(this, TvShowDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void onMovieDataPass(long data) {
        Bundle bundle = new Bundle();
        bundle.putLong("ID", data);
        Log.d("MainActivity", data + "");
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onPopularDataPass(long data) {
        Bundle bundle=new Bundle();
        bundle.putLong("CastId",data);
        Log.d("MainActivity",data+"");
        Intent intent=new Intent(this,CastDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_on_main,menu);
        return  true;

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.search){
            Intent intent=new Intent(this,SearchActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
