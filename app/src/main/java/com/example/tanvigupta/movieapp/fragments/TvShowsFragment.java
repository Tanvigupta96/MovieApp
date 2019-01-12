package com.example.tanvigupta.movieapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;

import com.example.tanvigupta.movieapp.adapter.AiringTodayTvAdapter;

import com.example.tanvigupta.movieapp.adapter.PopularTvAdapter;

import com.example.tanvigupta.movieapp.adapter.TopRatedTvAdapter;

import com.example.tanvigupta.movieapp.adapter.UpcomingTvAdapter;


import com.example.tanvigupta.movieapp.model.TvShows.Result;
import com.example.tanvigupta.movieapp.model.TvShows.TvShowResponse;

import com.example.tanvigupta.movieapp.network.ApiClientForTvShow;
import com.example.tanvigupta.movieapp.network.TvShowClickListener;
import com.example.tanvigupta.movieapp.others.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment  {
    private RecyclerView recyclerView;
    private RecyclerView topratedRv;
    private RecyclerView upcoming;
    private RecyclerView airingtoday;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    //private AppCompatButton retry1;
    private List<Result> popularTvResults = new ArrayList<>();
    private List<Result> topRatedTvShowResults = new ArrayList<>();
    private List<Result> upcomingTvShowResults = new ArrayList<>();
    private List<Result> airingTodayTvResults = new ArrayList<>();
    private PopularTvAdapter adapter;
    private TopRatedTvAdapter adapter1;
    private UpcomingTvAdapter adapter2;
    private AiringTodayTvAdapter adapter3;
    private ProgressBar progressBar;

    public static final int REQUEST_INITIAL = 1;
    public static final int REQUEST_SEQUENTIAL = 2;
    public int PAGE_NO1 = 1;
    public int PAGE_NO2 = 1;
    public int PAGE_NO3 = 1;
    public int PAGE_NO4 = 1;


    onDataPass listener;


    public TvShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof onDataPass){
            listener=(onDataPass)context;
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View output = inflater.inflate(R.layout.fragment_tv_shows, container, false);

        textView = output.findViewById(R.id.popular);
        textView1 = output.findViewById(R.id.toprated);
        textView2 = output.findViewById(R.id.upcoming);
        textView3 = output.findViewById(R.id.airingtoday);

        recyclerView = output.findViewById(R.id.recycleview);
        topratedRv = output.findViewById(R.id.recycleview1);
        upcoming = output.findViewById(R.id.recycleview2);
        airingtoday = output.findViewById(R.id.recycleview3);


        progressBar = output.findViewById(R.id.progressbar);
        //retry1 = output.findViewById(R.id.retry1);

        adapter = new PopularTvAdapter(getContext(), popularTvResults, new TvShowClickListener() {
            @Override
            public void onshowclicked(long id) {
                if(listener!=null){
                    listener.onDataPass(id);
                }
            }


        });
        adapter1 = new TopRatedTvAdapter(getContext(), topRatedTvShowResults, new TvShowClickListener() {
            @Override
            public void onshowclicked(long id) {
                if(listener!=null){
                    listener.onDataPass(id);
                }
            }


        });
        adapter2 = new UpcomingTvAdapter(getContext(), upcomingTvShowResults, new TvShowClickListener() {
            @Override
            public void onshowclicked(long id) {
                if(listener!=null){
                    listener.onDataPass(id);
                }
            }


        });
        adapter3 = new AiringTodayTvAdapter(getContext(), airingTodayTvResults, new TvShowClickListener() {
            @Override
            public void onshowclicked(long id) {
                if(listener!=null){
                    listener.onDataPass(id);
                }



            }


        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topratedRv.setLayoutManager(linearLayoutManager1);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        upcoming.setLayoutManager(linearLayoutManager2);

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        airingtoday.setLayoutManager(linearLayoutManager3);


        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        snapHelper.attachToRecyclerView(topratedRv);
        snapHelper.attachToRecyclerView(upcoming);
        snapHelper.attachToRecyclerView(airingtoday);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        topratedRv.setItemAnimator(new DefaultItemAnimator());
        upcoming.setItemAnimator(new DefaultItemAnimator());
        airingtoday.setItemAnimator(new DefaultItemAnimator());

        progressBar.setVisibility(View.VISIBLE);


        recyclerView.setVisibility(View.GONE);
        topratedRv.setVisibility(View.GONE);
        upcoming.setVisibility(View.GONE);
        airingtoday.setVisibility(View.GONE);


        textView.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);


        makeRequestforairingtoday(REQUEST_INITIAL);
        makeRequestforpopular(REQUEST_INITIAL);
        makeRequestfortoprated(REQUEST_INITIAL);
        makerequestforupcoming(REQUEST_INITIAL);


        airingtoday.setAdapter(adapter3);
        recyclerView.setAdapter(adapter);
        topratedRv.setAdapter(adapter1);
        upcoming.setAdapter(adapter2);



        return output;
    }


    public void makeRequestforairingtoday(final int requestType) {
        Call<TvShowResponse> call = ApiClientForTvShow.getTvService().getAiringtodaytvshows(Constants.API_KEY, PAGE_NO4);
        if (requestType == REQUEST_INITIAL) {
            //retry1.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null) {
                    TvShowResponse tvShowResponse = response.body();
                    List<Result> results = tvShowResponse.getResults();
                    if (requestType == REQUEST_INITIAL) {
                        airingTodayTvResults.clear();
                        progressBar.setVisibility(View.GONE);
                        airingtoday.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                    }
                    airingTodayTvResults.addAll(results);
                    PAGE_NO4++;


                    adapter3.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makeRequestforairingtoday(REQUEST_SEQUENTIAL);
                        }
                    });

                    if (tvShowResponse.getPage() == tvShowResponse.getTotalPages()) {
                        adapter3.removeOnLoadMoreListener();
                    }

                }
            }


            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    airingtoday.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }

            }
        });

    }


    public void makeRequestforpopular(final int requestType) {
        Call<TvShowResponse> call = ApiClientForTvShow.getTvService().getpopulartvshows(Constants.API_KEY, PAGE_NO1);
        if (requestType == REQUEST_INITIAL) {
            //retry1.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null) {
                    TvShowResponse tvShowResponse=response.body();
                    List<Result> results = tvShowResponse.getResults();
                    if (requestType == REQUEST_INITIAL) {
                        popularTvResults.clear();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                    }
                    popularTvResults.addAll(results);
                    PAGE_NO1++;

                    adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makeRequestforpopular(REQUEST_SEQUENTIAL);
                        }
                    });

                    if (tvShowResponse.getPage() == tvShowResponse.getTotalPages()) {
                        adapter.removeOnLoadMoreListener();
                    }
                }


            }


            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {

                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    public void makeRequestfortoprated(final int requestType) {
        Call<TvShowResponse> call = ApiClientForTvShow.getTvService().gettopratedtvshows(Constants.API_KEY, PAGE_NO2);
        if (requestType == REQUEST_INITIAL) {
            //retry1.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null) {
                    TvShowResponse tvShowResponse = response.body();
                    List<Result> results = tvShowResponse.getResults();
                    if (requestType == REQUEST_INITIAL) {
                        topRatedTvShowResults.clear();
                        progressBar.setVisibility(View.GONE);
                        topratedRv.setVisibility(View.VISIBLE);
                        textView1.setVisibility(View.VISIBLE);
                    }
                    topRatedTvShowResults.addAll(results);
                    PAGE_NO2++;

                    adapter1.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makeRequestfortoprated(REQUEST_SEQUENTIAL);
                        }
                    });
                    if (tvShowResponse.getPage() == tvShowResponse.getTotalPages()) {
                        adapter1.removeOnLoadMoreListener();
                    }
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    topratedRv.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void makerequestforupcoming(final int requestType) {

        Call<TvShowResponse> call = ApiClientForTvShow.getTvService().getontheairtvshows(Constants.API_KEY, PAGE_NO3);
        if (requestType == REQUEST_INITIAL) {
            //retry1.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null) {
                    TvShowResponse tvShowResponse = response.body();
                    List<Result> results = tvShowResponse.getResults();
                    if (requestType == REQUEST_INITIAL) {
                        upcomingTvShowResults.clear();
                        progressBar.setVisibility(View.GONE);
                        upcoming.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                    }
                    upcomingTvShowResults.addAll(results);
                    PAGE_NO3++;

                    adapter2.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makerequestforupcoming(REQUEST_SEQUENTIAL);
                        }
                    });
                    if (tvShowResponse.getPage() == tvShowResponse.getTotalPages()) {
                        adapter2.removeOnLoadMoreListener();
                    }

                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {

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
        void onDataPass(long data);
    }
}