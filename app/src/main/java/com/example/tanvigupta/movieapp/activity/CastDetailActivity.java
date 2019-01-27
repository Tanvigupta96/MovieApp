package com.example.tanvigupta.movieapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.adapter.CastMovieAdapter;
import com.example.tanvigupta.movieapp.adapter.CastPhotosAdapter;
import com.example.tanvigupta.movieapp.adapter.CastTvAdapter;
import com.example.tanvigupta.movieapp.fragments.PopularPeopleFragment;
import com.example.tanvigupta.movieapp.model.CastMovieDetail.CastMovieResponse;
import com.example.tanvigupta.movieapp.model.CastPhotos.CastPhotosResponse;
import com.example.tanvigupta.movieapp.model.CastPhotos.Profile;
import com.example.tanvigupta.movieapp.model.CastTvDetail.Cast;
import com.example.tanvigupta.movieapp.model.CastTvDetail.CastTvResonse;
import com.example.tanvigupta.movieapp.model.PersonDetail.PersonResponse;
import com.example.tanvigupta.movieapp.network.ApiClientForCredits;
import com.example.tanvigupta.movieapp.network.MovieClickListener;
import com.example.tanvigupta.movieapp.network.PhotoClickListener;
import com.example.tanvigupta.movieapp.network.TvShowClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastDetailActivity extends AppCompatActivity  {
    private TextView biography;
    private TextView birthday;
    private TextView placeofbirth;
    private Long castId;
    private List<Cast> casts = new ArrayList<>();
    private List<com.example.tanvigupta.movieapp.model.CastMovieDetail.Cast> cast = new ArrayList<>();
    private List<Profile> profiles=new ArrayList<>();
    private CastTvAdapter tvcast;
    private CastMovieAdapter moviecast;
    private CastPhotosAdapter castPhotos;
    private RecyclerView tvcastrecycleview;
    private RecyclerView moviecastrecycleview;
    private RecyclerView photosrecycleview;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private ImageView castimage;
    private TextView castname;
    private TextView castcategory;
    private TextView movie;
    private TextView tv;
    private TextView bp;
    private TextView dob;
    private TextView bio;
    private TextView photos;
    private  String name;
    private LinearLayout Parent;
    private SpannableString text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Parent=findViewById(R.id.Parent);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setTitle(" ");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });



        biography = findViewById(R.id.biography);
        birthday = findViewById(R.id.birthday);
        placeofbirth = findViewById(R.id.birthplace);

        appBarLayout = findViewById(R.id.appbar);
        castimage = findViewById(R.id.castimage);
        castname = findViewById(R.id.castname);
        castcategory = findViewById(R.id.castcategory);
        movie = findViewById(R.id.movie);
        tv = findViewById(R.id.tv);
        bp = findViewById(R.id.bp);
        bio = findViewById(R.id.bio);
        dob = findViewById(R.id.dob);
        photos=findViewById(R.id.photos);



        final Intent intent = getIntent();
        castId = intent.getLongExtra("CastId", 0);
        Log.d("CastDetailActivity", castId + "");

        tvcastrecycleview = findViewById(R.id.tvcastrecycleview);
        moviecastrecycleview = findViewById(R.id.moviecastrecycleview);
        photosrecycleview=findViewById(R.id.photosrecycleview);


        tvcast = new CastTvAdapter(this, casts, new TvShowClickListener() {
            @Override
            public void onshowclicked(long id) {
                Intent intent1 = new Intent(getApplicationContext(), TvShowDetailActivity.class);
                intent1.putExtra("ID", id);
                startActivity(intent1);
            }


        });

        moviecast = new CastMovieAdapter(this, cast, new MovieClickListener() {
            @Override
            public void onmovieclicked(long id) {
                Intent intent1 = new Intent(getApplicationContext(), MovieDetailActivity.class);
                intent1.putExtra("ID", id);
                startActivity(intent1);
            }




        });

        castPhotos=new CastPhotosAdapter(this, profiles, new PhotoClickListener() {
            @Override
            public void onphotoclick(String filepath) {
                Intent intent1=new Intent(getApplicationContext(),FullPhotoActivity.class);
                intent1.putExtra("FilePath",filepath);
                intent1.putExtra("Name",name);
                intent1.putExtra("castId",castId);
                Log.d("CastDetailActivity",filepath);
                startActivity(intent1);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tvcastrecycleview.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        moviecastrecycleview.setLayoutManager(linearLayoutManager1);

        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        photosrecycleview.setLayoutManager(linearLayoutManager2);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(tvcastrecycleview);
        snapHelper.attachToRecyclerView(moviecastrecycleview);
        snapHelper.attachToRecyclerView(photosrecycleview);

        tvcastrecycleview.setItemAnimator(new DefaultItemAnimator());
        moviecastrecycleview.setItemAnimator(new DefaultItemAnimator());
        photosrecycleview.setItemAnimator(new DefaultItemAnimator());

        Parent.setVisibility(View.GONE);


        Call<PersonResponse> call = ApiClientForCredits.getCreditService().getpersondetail(castId, Constants.API_KEY);
        call.enqueue(new Callback<PersonResponse>() {
            @Override
            public void onResponse(Call<PersonResponse> call, Response<PersonResponse> response) {
                if (response.body() != null) {
                    initialize(response.body());
                }
            }

            @Override
            public void onFailure(Call<PersonResponse> call, Throwable t) {

            }
        });


        tvcastrecycleview.setAdapter(tvcast);
        moviecastrecycleview.setAdapter(moviecast);
        photosrecycleview.setAdapter(castPhotos);

        ballspinfadeloader();
        ballPulseAnimLoader();

        }


    public void initialize(PersonResponse response) {
        Parent.setVisibility(View.VISIBLE);
        final PersonResponse personResponse = response;
        if (personResponse.getBiography().length()!=0) {

            //spannable wala code

            int length = personResponse.getBiography().length();

            text = new SpannableString("...Read More");
            if (length > 1000 && (Math.abs(length - 1000)) > 10) {

                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(personResponse.getBiography(), 0, 450);
                spannableStringBuilder.append(text);

                biography.setText(spannableStringBuilder.toString());

            } else {

                biography.setText(personResponse.getBiography());
            }

        }else {
            bio.setVisibility(View.GONE);
            biography.setVisibility(View.GONE);
        }

        if (personResponse.getBirthday() != null) {
            birthday.setText(personResponse.getBirthday());
        } else {
            dob.setVisibility(View.GONE);
            birthday.setVisibility(View.GONE);
        }
        if (personResponse.getPlaceOfBirth() != null) {
            placeofbirth.setText(personResponse.getPlaceOfBirth());
        } else {
            bp.setVisibility(View.GONE);
            placeofbirth.setVisibility(View.GONE);
        }
        name=personResponse.getName();
        ballPulseAnimLoadergone();

        castname.setText(personResponse.getName());
        castcategory.setText(personResponse.getKnownForDepartment());
        ballspinfadeloadergone();
        Picasso.get().load(Constants.IMAGE_BASE_URL + personResponse.getProfilePath()).into(castimage);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isShow = true;
            int scrollRange = -1;


            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(name);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }

            }
        });


        makerequestfortvcast();
        makerequestformoviecast();
        makerequestforcastphotos();


    }

    public void makerequestfortvcast() {
        Call<CastTvResonse> call = ApiClientForCredits.getCreditService().getcasttv(castId, Constants.API_KEY);
        call.enqueue(new Callback<CastTvResonse>() {
            @Override
            public void onResponse(Call<CastTvResonse> call, Response<CastTvResonse> response) {
                if (response.body() != null) {
                    CastTvResonse castTvResonse = response.body();
                    if (castTvResonse.getCast().size() == 0) {
                        tvcastrecycleview.setVisibility(View.GONE);
                        tv.setVisibility(View.GONE);
                    }
                    else {

                        List<Cast> casts1 = castTvResonse.getCast();
                        casts.clear();
                        casts.addAll(casts1);
                        tvcast.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<CastTvResonse> call, Throwable t) {

            }
        });

    }

    public void makerequestformoviecast() {
        Call<CastMovieResponse> call = ApiClientForCredits.getCreditService().getcastmovie(castId, Constants.API_KEY);
        call.enqueue(new Callback<CastMovieResponse>() {
            @Override
            public void onResponse(Call<CastMovieResponse> call, Response<CastMovieResponse> response) {
                if (response.body() != null) {
                    CastMovieResponse castMovieResponse = response.body();
                    if (castMovieResponse.getCast().size() == 0) {
                        movie.setVisibility(View.GONE);
                        moviecastrecycleview.setVisibility(View.GONE);
                    }
                    else {
                        List<com.example.tanvigupta.movieapp.model.CastMovieDetail.Cast> casts1 = castMovieResponse.getCast();
                        cast.clear();
                        cast.addAll(casts1);
                        moviecast.notifyDataSetChanged();

                    }

                }
            }

            @Override
            public void onFailure(Call<CastMovieResponse> call, Throwable t) {

            }
        });
    }


    public void makerequestforcastphotos(){
        Call<CastPhotosResponse> call=ApiClientForCredits.getCreditService().getcastphotos(castId,Constants.API_KEY);
        call.enqueue(new Callback<CastPhotosResponse>() {
            @Override
            public void onResponse(Call<CastPhotosResponse> call, Response<CastPhotosResponse> response) {
                if(response.body()!=null){
                    CastPhotosResponse castPhotosResponse=response.body();
                    if(castPhotosResponse.getProfiles().size()==0){
                        photosrecycleview.setVisibility(View.GONE);
                        photos.setVisibility(View.GONE);
                    }
                    else{
                        List<Profile> profiles1=castPhotosResponse.getProfiles();
                        profiles.clear();
                        profiles.addAll(profiles1);
                        castPhotos.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<CastPhotosResponse> call, Throwable t) {

            }
        });



    }

    public void ballspinfadeloader(){
        findViewById(R.id.ballspinfadeoader).setVisibility(View.VISIBLE);
    }


    public void ballspinfadeloadergone(){
        findViewById(R.id.ballspinfadeoader).setVisibility(View.GONE);
    }

    public void ballPulseAnimLoader(){
        findViewById(R.id.ballpulse).setVisibility(View.VISIBLE);
    }

    public void ballPulseAnimLoadergone(){
        findViewById(R.id.ballpulse).setVisibility(View.GONE);
    }


}
