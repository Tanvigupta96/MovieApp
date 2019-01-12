package com.example.tanvigupta.movieapp.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.adapter.EpisodeWiseCastAdapter;
import com.example.tanvigupta.movieapp.adapter.EpisodeWiseStillsAdapter;
import com.example.tanvigupta.movieapp.model.EpisodeStills.EpisodeStillsResponse;
import com.example.tanvigupta.movieapp.model.EpisodeStills.Still;
import com.example.tanvigupta.movieapp.model.EpisodeWiseCast.Cast;
import com.example.tanvigupta.movieapp.model.EpisodeWiseCast.CastResponse;
import com.example.tanvigupta.movieapp.network.ApiClientForTvShow;
import com.example.tanvigupta.movieapp.network.CastClickListener;
import com.example.tanvigupta.movieapp.others.Constants;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeDetailActivity extends AppCompatActivity {
    private RecyclerView episodestills;
    private List<Still> stills=new ArrayList<>();
    private EpisodeWiseStillsAdapter adapter1;
    private RecyclerView episodecast;
    private List<Cast> casts=new ArrayList<>();
    private EpisodeWiseCastAdapter adapter;
    private ProgressBar progressBar;
    private long id;
    private long seasonnumber;
    private long episodenumber;
    private String date;
    private  String overview;
    private String name;
    private Intent intent;
    private TextView cast;
    private TextView still;

    private double voteavg;


    ImageView img;


    private TextView fixedtextview;
    private TextView ratingtxt;
     TextView airdate;
     TextView air;
    TextView content;
    android.support.v7.widget.Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_detail);

        episodecast = findViewById(R.id.episodecast);
        episodestills=findViewById(R.id.episodestills);
        progressBar = findViewById(R.id.progressbar);
        cast=findViewById(R.id.cast);
        still=findViewById(R.id.stills);


        img=findViewById(R.id.img);

        fixedtextview=findViewById(R.id.fixedtextview);
        ratingtxt=findViewById(R.id.ratingtxt);

        toolbar=findViewById(R.id.toolbar);
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });


        content = findViewById(R.id.content);
        air=findViewById(R.id.air);
        airdate=findViewById(R.id.airdate);


        adapter = new EpisodeWiseCastAdapter(getApplicationContext(), casts, new CastClickListener() {
            @Override
            public void oncastclick(long id) {
                Intent intent=new Intent(getApplicationContext(), CastDetailActivity.class);
                intent.putExtra("CastId",id);
                startActivity(intent);

            }
        });


        adapter1=new EpisodeWiseStillsAdapter(getApplicationContext(),stills);


        intent=getIntent();
        id = intent.getLongExtra("id",0);
        seasonnumber = intent.getLongExtra("seasonnumber",0);
        episodenumber = intent.getLongExtra("episodenumber",0);
        name = intent.getStringExtra("title");
        overview=intent.getStringExtra("overview");
        date=intent.getStringExtra("date");
        voteavg=intent.getDoubleExtra("vote",0.0);
        Log.d("vote",voteavg+"");


        Log.d("abc", id + "");
        Log.d("abc", seasonnumber + "");
        Log.d("abc",episodenumber+"");


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        episodecast.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        episodestills.setLayoutManager(linearLayoutManager1);

        episodecast.setItemAnimator(new DefaultItemAnimator());

        episodestills.setItemAnimator(new DefaultItemAnimator());


        progressBar.setVisibility(View.VISIBLE);
        episodestills.setVisibility(View.GONE);
        episodecast.setVisibility(View.GONE);
        still.setVisibility(View.GONE);
        cast.setVisibility(View.GONE);

        air.setVisibility(View.GONE);

        episodecast.setAdapter(adapter);
        episodestills.setAdapter(adapter1);

        initialize();
        makerequestforcast();
        makerequestforstills();


        }


        public void initialize() {
            getSupportActionBar().setTitle(name);
            progressBar.setVisibility(View.GONE);


            if (overview != null) {
                content.setText(overview);
                }
            else{
                content.setText("- no content available-");


            }

            if(date!=null){
                airdate.setText(date);
                air.setVisibility(View.VISIBLE);
            }
            else{
                airdate.setVisibility(View.GONE);
                air.setVisibility(View.GONE);
            }

            ratingtxt.setText(voteavg+"");
            fixedtextview.setText("/10");


        }


    public void makerequestforcast() {
        Call<CastResponse> call = ApiClientForTvShow.getTvService().getseasoncast(id, seasonnumber, episodenumber, Constants.API_KEY);
        call.enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                if (response.body() != null) {
                    CastResponse castResponse = response.body();
                    if (castResponse.getCast().size() == 0) {
                        episodecast.setVisibility(View.GONE);
                        cast.setVisibility(View.GONE);




                    } else {
                        cast.setVisibility(View.VISIBLE);
                        List<Cast> casts1 = castResponse.getCast();
                        casts.clear();
                        casts.addAll(casts1);
                        episodecast.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();

                    }

                }


            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {

            }
        });
    }


    public void makerequestforstills() {
        Call<EpisodeStillsResponse> call = ApiClientForTvShow.getTvService().getstills(id, seasonnumber, episodenumber, Constants.API_KEY);
        call.enqueue(new Callback<EpisodeStillsResponse>() {
            @Override
            public void onResponse(Call<EpisodeStillsResponse> call, Response<EpisodeStillsResponse> response) {
                if (response.body() != null) {
                    EpisodeStillsResponse episodeStillsResponse = response.body();
                    if (episodeStillsResponse.getStills().size() == 0 || episodeStillsResponse.getStills()==null) {
                        still.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        still.setVisibility(View.VISIBLE);
                        List<Still> still = episodeStillsResponse.getStills();
                        stills.clear();
                        stills.addAll(still);
                        episodestills.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        adapter1.notifyDataSetChanged();

                    }
                }

                else{

                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<EpisodeStillsResponse> call, Throwable t) {

            }
        });
    }






}
