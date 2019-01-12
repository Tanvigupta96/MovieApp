package com.example.tanvigupta.movieapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.adapter.TopRatedMovieAdapter;
import com.example.tanvigupta.movieapp.adapter.UpcomingMovieAdapter;
import com.example.tanvigupta.movieapp.model.Movies.MovieResponse;
import com.example.tanvigupta.movieapp.model.Movies.Result;
import com.example.tanvigupta.movieapp.network.ApiClient;
import com.example.tanvigupta.movieapp.network.MovieClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.example.tanvigupta.movieapp.adapter.PopularMovieAdapter;
import com.example.tanvigupta.movieapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView topratedRv;
    private RecyclerView upcoming;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;

    //private AppCompatButton retry1;
    private List<Result> popularmovies = new ArrayList<>();
    private List<Result> topratedmovies=new ArrayList<>();
    private List<Result> upcomingResults=new ArrayList<>();

    private PopularMovieAdapter adapter;
    private TopRatedMovieAdapter adapter1;
    private UpcomingMovieAdapter adapter2;

    private ProgressBar progressBar;

    public static final int REQUEST_INITIAL = 1;
    public static final int REQUEST_SEQUENTIAL = 2;
    public int PAGE_NO1=1;
    public int PAGE_NO2=1;
    public int PAGE_NO3=1;

    onDataPass listener;


    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MovieFragment.onDataPass){
            listener=(MovieFragment.onDataPass)context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View output = inflater.inflate(R.layout.fragment_movie, container, false);


        textView=output.findViewById(R.id.popular);
        textView1=output.findViewById(R.id.toprated);
        textView2=output.findViewById(R.id.upcoming);


        recyclerView = output.findViewById(R.id.recycleview);
        topratedRv =output.findViewById(R.id.recycleview1);
        upcoming=output.findViewById(R.id.recycleview2);


        progressBar = output.findViewById(R.id.progressbar);
        //retry1 = output.findViewById(R.id.retry1);

        adapter = new PopularMovieAdapter(getContext(), popularmovies, new MovieClickListener() {
            @Override
            public void onmovieclicked(long id) {
                Log.d("MovieFragment",id+"");
                if(listener!=null){
                    listener.onMovieDataPass(id);
                }
            }


        });


        adapter1= new TopRatedMovieAdapter(getContext(), topratedmovies, new MovieClickListener() {
            @Override
            public void onmovieclicked(long id) {
                if(listener!=null){
                    listener.onMovieDataPass(id);
                }
            }




        });



        adapter2 = new UpcomingMovieAdapter(getContext(), upcomingResults, new MovieClickListener() {
            @Override
            public void onmovieclicked(long id) {
                if(listener!=null){
                    listener.onMovieDataPass(id);
                }

            }




        });



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topratedRv.setLayoutManager(linearLayoutManager1);

        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        upcoming.setLayoutManager(linearLayoutManager2);


        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        snapHelper.attachToRecyclerView(topratedRv);
        snapHelper.attachToRecyclerView(upcoming);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        topratedRv.setItemAnimator(new DefaultItemAnimator());
        upcoming.setItemAnimator(new DefaultItemAnimator());

        progressBar.setVisibility(View.VISIBLE);


        recyclerView.setVisibility(View.GONE);
        topratedRv.setVisibility(View.GONE);
        upcoming.setVisibility(View.GONE);


        textView.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);


        makeRequestforpopular(REQUEST_INITIAL);
        makeRequestfortoprated(REQUEST_INITIAL);
        makerequestforupcoming(REQUEST_INITIAL);


        recyclerView.setAdapter(adapter);
        topratedRv.setAdapter(adapter1);
        upcoming.setAdapter(adapter2);



        /*retry1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest(REQUEST_INITIAL);
            }
        });*/
        return output;
    }

    public void makeRequestforpopular(final int requestType) {

        Call<MovieResponse> call = ApiClient.getMovieService().getpopularmovies(Constants.API_KEY, PAGE_NO1);

        if (requestType == REQUEST_INITIAL) {
            //retry1.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                    MovieResponse movieResponse = response.body();
                    List<Result> results = movieResponse.getResults();
                    if (requestType == REQUEST_INITIAL) {
                        popularmovies.clear();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                    }
                    popularmovies.addAll(results);
                    PAGE_NO1++;
                    adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makeRequestforpopular(REQUEST_SEQUENTIAL);
                        }
                    });

                    if (movieResponse.getPage() == movieResponse.getTotalPages()) {
                        adapter.removeOnLoadMoreListener();
                    }
                    //adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("Respose", "onFailure: ", t);
                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }
            }
        });
    }



    public void makeRequestfortoprated(final int requestType) {

        Call<MovieResponse> call1 = ApiClient.getMovieService().gettopratedmovies(Constants.API_KEY, PAGE_NO2);

        call1.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                    MovieResponse movieResponse = response.body();
                    List<Result> topRatedResults = movieResponse.getResults();
                    if (requestType == REQUEST_INITIAL) {
                        topratedmovies.clear();
                        progressBar.setVisibility(View.GONE);
                        topratedRv.setVisibility(View.VISIBLE);
                        textView1.setVisibility(View.VISIBLE);
                    }
                    topratedmovies.addAll(topRatedResults);
                    PAGE_NO2++;
                    adapter1.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makeRequestfortoprated(REQUEST_SEQUENTIAL);
                        }
                    });

                    if (movieResponse.getPage() == movieResponse.getTotalPages()) {
                        adapter1.removeOnLoadMoreListener();
                    }
                    //adapter1.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    topratedRv.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    public void makerequestforupcoming(final int requestType){
        Call<MovieResponse> call2=ApiClient.getMovieService().getupcoming(Constants.API_KEY,PAGE_NO3);

        call2.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                    MovieResponse movieResponse = response.body();
                    List<Result> upcomingmovieResults = movieResponse.getResults();
                    if (requestType == REQUEST_INITIAL) {
                        upcomingResults.clear();
                        progressBar.setVisibility(View.GONE);
                        upcoming.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                    }
                    upcomingResults.addAll(upcomingmovieResults);
                    PAGE_NO3++;

                    adapter2.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makerequestforupcoming(REQUEST_SEQUENTIAL);
                        }
                    });


                    if (movieResponse.getPage() == movieResponse.getTotalPages()) {
                        adapter1.removeOnLoadMoreListener();
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    upcoming.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }
            }
        });
    }



    public interface OnLoadMoreListener {
        void loadMore();
    }

    public interface onDataPass{
        void onMovieDataPass(long data);
    }
}
