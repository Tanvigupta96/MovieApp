package com.example.tanvigupta.movieapp.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import android.widget.Toast;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.adapter.SearchItemAdapter;
import com.example.tanvigupta.movieapp.model.Search.Result;
import com.example.tanvigupta.movieapp.model.Search.SearchResponse;
import com.example.tanvigupta.movieapp.network.ApiClientForSearch;
import com.example.tanvigupta.movieapp.network.Item;
import com.example.tanvigupta.movieapp.network.SearchedClickListener;
import com.example.tanvigupta.movieapp.others.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private SearchItemAdapter adapter;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;


    private ArrayList<Result> results=new ArrayList<>();
    private ProgressBar progressBar;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.searchItem);
        searchView=(SearchView) menuItem.getActionView();

        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        ImageView searchViewIcon = (ImageView)searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);

        ViewGroup linearLayoutSearchView =(ViewGroup) searchViewIcon.getParent();
        linearLayoutSearchView.removeView(searchViewIcon);



        searchView.setQueryHint("Search Movies,Tv Shows,People");



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.isEmpty()){
                    updateListOfResults(s);
                }
                return true;
            }
        });


        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //fetchpopularmovies


                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void updateListOfResults(String s){
        Call<SearchResponse> call= ApiClientForSearch.getSearchService().getsearchedmovie(Constants.API_KEY,s);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

            SearchResponse searchResponse = response.body();

            if(searchResponse!=null){

                List<Result> searchresult = searchResponse.getResults();

                results.clear();
                results.addAll(searchresult);
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                }


                else{
                Toast.makeText(SearchActivity.this,"No Result Found",Toast.LENGTH_LONG).show();
                results.clear();
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(SearchActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("try",t.getMessage());
                progressBar.setVisibility(View.GONE);

            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView=findViewById(R.id.recycleview);
        toolbar=findViewById(R.id.toolbar);
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });


        adapter = new SearchItemAdapter(this, results, new SearchedClickListener() {
            @Override
            public void onItemClick(Long id, String type) {
                Log.d("heyo",type);
                Log.d("yo",id +" ");

                if(type.equals("tv")){
                    Intent intent=new Intent(getApplicationContext(),TvShowDetailActivity.class);
                    intent.putExtra("ID",id);
                    startActivity(intent);

                }
                else{
                    if(type.equals("movie")){
                    Intent intent1=new Intent(getApplicationContext(),MovieDetailActivity.class);
                    intent1.putExtra("ID",id);
                    startActivity(intent1);
                        }

                        else{
                        Intent intent2=new Intent(getApplicationContext(),CastDetailActivity.class);
                        intent2.putExtra("CastId",id);
                        startActivity(intent2);

                    }

                }
            }




        });


        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);



        recyclerView.setVisibility(View.GONE);

        recyclerView.setAdapter(adapter);
        progressBar=findViewById(R.id.progressbar);

        }
}
