package com.example.tanvigupta.movieapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.adapter.PopularPeopleAdapter;
import com.example.tanvigupta.movieapp.model.PopularPeople.PopularPeopleResponse;
import com.example.tanvigupta.movieapp.model.PopularPeople.Result;
import com.example.tanvigupta.movieapp.network.ApiClientForCredits;
import com.example.tanvigupta.movieapp.network.CastClickListener;
import com.example.tanvigupta.movieapp.others.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularPeopleFragment extends Fragment {
    private List<Result> results = new ArrayList<>();
    private PopularPeopleAdapter adapter;
    private TextView popular;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    public static final int REQUEST_INITIAL = 1;
    public static final int REQUEST_SEQUENTIAL = 2;
    public int PAGE_NO1 = 1;

    onDataPass listener;


    public PopularPeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onDataPass) {
            listener = (onDataPass) context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View output = inflater.inflate(R.layout.fragment_popular_people, container, false);
        recyclerView = output.findViewById(R.id.popularpeoplerv);
        progressBar = output.findViewById(R.id.progressbar1);
        popular = output.findViewById(R.id.popular);
        adapter = new PopularPeopleAdapter(getContext(), results, new CastClickListener() {
            @Override
            public void oncastclick(long id) {

                if(listener!=null){
                    listener.onPopularDataPass(id);
                }

            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);



        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setVisibility(View.GONE);
        popular.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        makerequestforpopularpeople(REQUEST_INITIAL);

        recyclerView.setAdapter(adapter);


        return output;
    }

    public void makerequestforpopularpeople(final int requestType) {

        Call<PopularPeopleResponse> call = ApiClientForCredits.getCreditService().getpopularppl(Constants.API_KEY, PAGE_NO1);
        if (requestType == REQUEST_INITIAL) {
            progressBar.setVisibility(View.VISIBLE);

        }

        call.enqueue(new Callback<PopularPeopleResponse>() {
            @Override
            public void onResponse(Call<PopularPeopleResponse> call, Response<PopularPeopleResponse> response) {
                if (response.body() != null) {
                    PopularPeopleResponse popularPeopleResponse = response.body();
                    List<Result> result1 = popularPeopleResponse.getResults();

                    if (requestType == REQUEST_INITIAL) {
                        results.clear();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        popular.setVisibility(View.VISIBLE);
                    }
                    results.addAll(result1);
                    PAGE_NO1++;
                    adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makerequestforpopularpeople(REQUEST_SEQUENTIAL);
                        }
                    });

                    if (popularPeopleResponse.getPage() == popularPeopleResponse.getTotalPages()) {
                        adapter.removeOnLoadMoreListener();
                    }


                }
            }

            @Override
            public void onFailure(Call<PopularPeopleResponse> call, Throwable t) {
                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }

    public interface OnLoadMoreListener {
        void loadMore();
    }


    public interface onDataPass {
        void onPopularDataPass(long data);

    }
}
