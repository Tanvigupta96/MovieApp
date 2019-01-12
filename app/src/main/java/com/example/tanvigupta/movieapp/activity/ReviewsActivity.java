package com.example.tanvigupta.movieapp.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.adapter.ReviewsAdapter;
import com.example.tanvigupta.movieapp.model.Reviews.Result;
import com.example.tanvigupta.movieapp.model.Reviews.ReviewResponse;
import com.example.tanvigupta.movieapp.network.ApiClient;
import com.example.tanvigupta.movieapp.network.ApiClientForTvShow;
import com.example.tanvigupta.movieapp.network.ReviewClickListener;
import com.example.tanvigupta.movieapp.others.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ReviewsAdapter adapter;
    public static final int REQUEST_INITIAL = 1;
    public static final int REQUEST_SEQUENTIAL = 2;
    public int PAGE_NO1 = 1;
    List<Result> results = new ArrayList<>();
    ProgressBar progressBar;
    Long id;
    String title;
    int i;
    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        recyclerView = findViewById(R.id.reviewsrecycleview);
        progressBar = findViewById(R.id.progressbar);


        adapter = new ReviewsAdapter(this, results, new ReviewClickListener() {
            @Override
            public void onreviewclick(String name, String content) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewsActivity.this);
                builder.setView(R.layout.reviews_row_layout);
                builder.setTitle(name);
                builder.setMessage(content);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        id = intent.getLongExtra("ID", 0);
        Log.d("ReviewsActivity", id + "");

        title = intent.getStringExtra("TITLE");
        if(title!=null){
            toolbar.setTitle(title);
        }
        else {
            toolbar.setTitle("Reviews!!");
        }


        i = intent.getIntExtra("I", 0);
        Log.d("ReviewsActivity",i+"");


        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        if (i == 1){

            makerequestforreviewsfortv(REQUEST_INITIAL);
            }

            else{
            makerequestforreviewsformovie(REQUEST_INITIAL);
        }
}


    public void  makerequestforreviewsfortv(final int requestType) {
        Call<ReviewResponse> call = ApiClientForTvShow.getTvService().getreviews(id, Constants.API_KEY);
        if (requestType == REQUEST_INITIAL) {
            //retry1.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if(response.body()!=null) {
                    ReviewResponse reviewResponse = response.body();
                    List<Result> results1 = reviewResponse.getResults();
                    if (requestType == REQUEST_INITIAL) {
                        results.clear();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                    results.addAll(results1);
                    PAGE_NO1++;

                    adapter.setOnLoadMoreListener(new ReviewsActivity.OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makerequestforreviewsfortv(REQUEST_SEQUENTIAL);
                        }
                    });
                    if (reviewResponse.getPage() == reviewResponse.getTotalPages()) {
                        adapter.removeOnLoadMoreListener();
                    }

                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }



            }
        });
    }

    public void  makerequestforreviewsformovie(final int requestType) {
        Call<ReviewResponse> call = ApiClient.getMovieService().getreviews(id, Constants.API_KEY);
        if (requestType == REQUEST_INITIAL) {
            //retry1.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if(response.body()!=null) {
                    ReviewResponse reviewResponse = response.body();
                    List<Result> results1 = reviewResponse.getResults();
                    if (requestType == REQUEST_INITIAL) {
                        results.clear();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                    results.addAll(results1);
                    PAGE_NO1++;

                    adapter.setOnLoadMoreListener(new ReviewsActivity.OnLoadMoreListener() {
                        @Override
                        public void loadMore() {
                            makerequestforreviewsformovie(REQUEST_SEQUENTIAL);
                        }
                    });
                    if (reviewResponse.getPage() == reviewResponse.getTotalPages()) {
                        adapter.removeOnLoadMoreListener();
                    }

                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }



            }
        });
    }







    public interface OnLoadMoreListener {
        void loadMore();
    }
}
