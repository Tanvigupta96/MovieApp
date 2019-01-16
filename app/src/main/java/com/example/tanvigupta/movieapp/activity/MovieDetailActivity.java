package com.example.tanvigupta.movieapp.activity;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanvigupta.movieapp.DataBase.MovieDatabase;
import com.example.tanvigupta.movieapp.DataBase.MovieTable;
import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.adapter.SimilarMovieAdapter;
import com.example.tanvigupta.movieapp.adapter.CastAdapter;
import com.example.tanvigupta.movieapp.adapter.VideoAdapter;
import com.example.tanvigupta.movieapp.model.MovieDetail.Genre;
import com.example.tanvigupta.movieapp.model.MovieDetail.MovieDetailResponse;
import com.example.tanvigupta.movieapp.model.Reviews.ReviewResponse;


import com.example.tanvigupta.movieapp.model.BothCast.Cast;
import com.example.tanvigupta.movieapp.model.BothCast.CastResponse;
import com.example.tanvigupta.movieapp.model.SimilarMovie.SimilarMovieResponse;
import com.example.tanvigupta.movieapp.model.Videos.VideoResponse;
import com.example.tanvigupta.movieapp.network.ApiClient;
import com.example.tanvigupta.movieapp.network.MovieClickListener;
import com.example.tanvigupta.movieapp.network.CastClickListener;
import com.example.tanvigupta.movieapp.network.VideoClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {
    long id;
    private TextView ratingtxt;
    private TextView overviewtxt;
    private ImageView coverimage;
    private TextView runtimetxt;
    private TextView releasedatetxt;
    private TextView similarmovies;
    private LinearLayout Parent;

    LinearLayout linearLayout;
    private RecyclerView similar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    ProgressBar progressBar;
    private List<com.example.tanvigupta.movieapp.model.SimilarMovie.Result> results = new ArrayList<>();
    public static final int REQUEST_INITIAL = 1;
    public static final int REQUEST_SEQUENTIAL = 2;
    public int PAGE_NO1 = 1;
    private SimilarMovieAdapter adapter5;
    private TextView runtm;
    private TextView releasedt;
    private ImageView titleimage;
    private TextView postertitle;
    private TextView postergenere;
    private TextView fixedtextview;
    private AppBarLayout appBarLayout;
    private Button reviews;
    private String review;
    private TextView video;
    private TextView showcast;
    private RecyclerView castrecycleview;
    private RecyclerView videorecycleview;
    private List<Cast> casts = new ArrayList<>();
    private CastAdapter adapter;
    private VideoAdapter adaptervideo;
     String backdroppath;
    private List<com.example.tanvigupta.movieapp.model.Videos.Result> result1 = new ArrayList<>();
    private  MovieDatabase movieDatabase;

    private boolean checked;
    private  boolean liked;

    Intent intent1;
    String title;
    String genere;
    String homepage;
    SpannableString text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Parent=findViewById(R.id.Parent);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout =  findViewById(R.id.collapsing_toolbar);
        getSupportActionBar().setTitle(" ");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });



        movieDatabase= Room.databaseBuilder(this,MovieDatabase.class,"movie_db" ).allowMainThreadQueries().build();

        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);


        ratingtxt = findViewById(R.id.ratingtxt);
        runtimetxt = findViewById(R.id.runningtiime);
        overviewtxt = findViewById(R.id.overviewtxt);
        coverimage = findViewById(R.id.coverimage);
        releasedatetxt = findViewById(R.id.releaseDate);
        progressBar = findViewById(R.id.progressbar);
        similar = findViewById(R.id.recycleview6);
        linearLayout = findViewById(R.id.linearlayout);
        similarmovies = findViewById(R.id.similarmovies);
        runtm = findViewById(R.id.runtm);
        releasedt = findViewById(R.id.releasedt);
        titleimage = findViewById(R.id.titleimage);
        postergenere = findViewById(R.id.postergenere);
        postertitle = findViewById(R.id.postertitle);
        fixedtextview = findViewById(R.id.fixedtextview);
        reviews = findViewById(R.id.reviews);
        showcast = findViewById(R.id.showcast);
        castrecycleview = findViewById(R.id.recycleview7);
        videorecycleview = findViewById(R.id.videorecycleview);
        video = findViewById(R.id.video);
        liked=false;


        Intent intent = getIntent();
        id = intent.getLongExtra("ID", 0);
        Log.d("MovieDetailActivity", id + "");


        adapter5 = new SimilarMovieAdapter(this, results, new MovieClickListener() {
            @Override
            public void onmovieclicked(long id) {
                Intent intent2 = new Intent(getApplicationContext(), MovieDetailActivity.class);
                intent2.putExtra("ID", id);
                startActivity(intent2);
            }


        });

        adaptervideo = new VideoAdapter(this, result1, new VideoClickListener() {
            @Override
            public void onvideoclicked(String key) {

                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(Constants.VIDEO_BASE_URL + key);
                intent1.setData(uri);
                startActivity(intent1);
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        similar.setLayoutManager(linearLayoutManager);

        //adding divider
        similar.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        castrecycleview.setLayoutManager(linearLayoutManager1);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        videorecycleview.setLayoutManager(linearLayoutManager2);


        adapter = new CastAdapter(this, casts, new CastClickListener() {
            @Override
            public void oncastclick(long id) {
                intent1 = new Intent(getApplicationContext(), CastDetailActivity.class);
                intent1.putExtra("CastId", id);
                Log.d("TvShowDetailActivity", id + "");
                startActivity(intent1);
            }
        });


        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(similar);
        snapHelper.attachToRecyclerView(castrecycleview);
        snapHelper.attachToRecyclerView(videorecycleview);

        similar.setItemAnimator(new DefaultItemAnimator());
        castrecycleview.setItemAnimator(new DefaultItemAnimator());
        videorecycleview.setItemAnimator(new DefaultItemAnimator());

        progressBar.setVisibility(View.VISIBLE);
        similar.setVisibility(View.GONE);
        castrecycleview.setVisibility(View.GONE);
        videorecycleview.setVisibility(View.GONE);
        Parent.setVisibility(View.GONE);

        Call<MovieDetailResponse> call = ApiClient.getMovieService().getDetailofmovie(id, Constants.API_KEY);
        call.enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                if (response.body() != null) {
                    initialize(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {

            }
        });


        makeRequestforsimilarmovies(REQUEST_INITIAL);

        similar.setAdapter(adapter5);
        castrecycleview.setAdapter(adapter);
        videorecycleview.setAdapter(adaptervideo);
        reviews.setOnClickListener(this);


        ballPulseAnimLoader();
        LineScaleLoaderExample();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        MenuItem menuItem= menu.findItem(R.id.fav);
        checked = movieDatabase.getDao().getChecked(id);
        Log.d("this",checked+" ");
        Log.d("hey",id+"");
        if(checked){
            menuItem.setIcon(R.drawable.ic_favorite_red_500_18dp);
            liked=checked;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();
        if (itemid == R.id.share) {
            ArrayList<String> data = new ArrayList<>();
            data.add(title);
            data.add(genere);
            data.add(homepage);
            String data1 = TextUtils.join("\n", data);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(intent.EXTRA_TEXT, data1);
            startActivity(intent);
        } else if (itemid == R.id.fav) {
            //liked=false;
            /*checked = movieDatabase.getDao().getChecked(id);
            Log.d("this",checked+" ");
            Log.d("hey",id+"");
            if(checked){
                item.setIcon(R.drawable.ic_favorite_red_500_18dp);
                liked=checked;
                }*/
                if(liked==false) {
                liked=true;
                item.setIcon(R.drawable.ic_favorite_red_500_18dp);
                MovieTable movieTable = new MovieTable();
                movieTable.setId(id);
                movieTable.setPosterPath(backdroppath);
                movieTable.setTitle(title);
                movieTable.setChecked(true);
                movieDatabase.getDao().addMovie(movieTable);
                Toast.makeText(this, "Added to favourites", Toast.LENGTH_SHORT).show();
                }
                else{
                item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                liked=false;
                Toast.makeText(this,"Removed from favourites",Toast.LENGTH_SHORT).show();
                int Mid=movieDatabase.getDao().getMovieTableId(id);
                MovieTable movieTable=new MovieTable();
                movieTable.setTableId(Mid);
                movieDatabase.getDao().deleteMovie(movieTable);

                }

            }
        return super.onOptionsItemSelected(item);
    }


    public void CheckReviews(View v) {


        Call<ReviewResponse> reviewResponseCall = ApiClient.getMovieService().getreviews(id, Constants.API_KEY);
        reviewResponseCall.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {


                if (response.body() != null) {
                    ReviewResponse reviewResponse = response.body();
                    //reviewId = reviewResponse.getResults().get(0).getId();
                    if (reviewResponse.getResults().size() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MovieDetailActivity.this);
                        builder.setTitle("No reviews");
                        builder.setIcon(R.drawable.ic_thumb_down_black_18dp);
                        AlertDialog dialog = builder.create();
                        dialog.show();


                    } else {
                        review = reviewResponse.getResults().get(0).getContent();

                        //Log.d("TvShowDetailActivity", reviewId + "");
                        AlertDialog.Builder builder = new AlertDialog.Builder(MovieDetailActivity.this);
                        builder.setTitle(review);
                        builder.setIcon(R.drawable.ic_thumb_up_black_18dp);

                        builder.setPositiveButton("view", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MovieDetailActivity.this, ReviewsActivity.class);
                                intent.putExtra("ID", id);
                                Log.d("MovieDetailActivity", id + "");
                                intent.putExtra("TITLE", title);

                                startActivity(intent);


                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }


        });
    }


    public void initialize(MovieDetailResponse response) {
        Parent.setVisibility(View.VISIBLE);
        MovieDetailResponse movieDetailResponse = response;
        backdroppath = movieDetailResponse.getBackdropPath();
        String overview = movieDetailResponse.getOverview();

        if(overview.length()!=0) {
            int length = overview.length();
            text = new SpannableString("...Read More");
            if (length > 1000 && (Math.abs(length - 1000)) > 10) {

                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(movieDetailResponse.getOverview(), 0, 450);
                spannableStringBuilder.append(text);

                overviewtxt.setText(spannableStringBuilder.toString());

            } else {
                overviewtxt.setText(overview);

            }

        }else{
                overviewtxt.setVisibility(View.GONE);

            }

            double voteAvg = movieDetailResponse.getVoteAverage();
        Log.d("MovieDetailActivity", voteAvg + "");
        Long runtime = movieDetailResponse.getRuntime();
        String image = movieDetailResponse.getPosterPath();
        title = movieDetailResponse.getTitle();
        Log.d("MovieDetailActivity", title);
        postertitle.setText(title);
        homepage = movieDetailResponse.getHomepage();

        if (runtime != null && runtime != 0L) {
            runtimetxt.setText(runtime + "Mins");

        } else {
            runtimetxt.setVisibility(View.GONE);
            runtm.setVisibility(View.GONE);

        }
        String releasedate = movieDetailResponse.getReleaseDate();
        if (releasedate == null || releasedate.isEmpty()) {
            releasedatetxt.setVisibility(View.GONE);
            releasedt.setVisibility(View.GONE);
        } else {
            releasedatetxt.setText(releasedate);
        }


        ratingtxt.setText(voteAvg + "");
        fixedtextview.setText("/10");


        ballPulseAnimLoaderGone();
        LineScaleLoaderGone();

        Picasso.get().load(Constants.IMAGE_BASE_URL + backdroppath).into(coverimage);
        Picasso.get().load(Constants.IMAGE_BASE_URL + image).into(titleimage);

        genere = TextUtils.join(",", movieDetailResponse.getGenres());
        if (genere == null || genere.isEmpty()) {
            postergenere.setVisibility(View.GONE);

        } else {
            postergenere.setText(genere);
        }

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(title);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work

                    isShow = false;
                }

            }
        });


        makeRequestforcast();
        makerequestforvideos();


    }


    public void makeRequestforsimilarmovies(final int requestType) {
        Call<SimilarMovieResponse> call = ApiClient.getMovieService().getsimilarmovies(id, Constants.API_KEY, PAGE_NO1);
        if (requestType == REQUEST_INITIAL) {
            //retry1.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
        call.enqueue(new Callback<SimilarMovieResponse>() {
            @Override
            public void onResponse(Call<SimilarMovieResponse> call, Response<SimilarMovieResponse> response) {
                if (response.body() != null) {
                    SimilarMovieResponse similarmovieresponse = response.body();
                    if (similarmovieresponse.getResults().size() == 0) {
                        similar.setVisibility(View.GONE);
                        similarmovies.setVisibility(View.GONE);

                    } else {
                        List<com.example.tanvigupta.movieapp.model.SimilarMovie.Result> result = similarmovieresponse.getResults();
                        if (requestType == REQUEST_INITIAL) {
                            results.clear();
                            progressBar.setVisibility(View.GONE);
                            similar.setVisibility(View.VISIBLE);

                        }
                        results.addAll(result);
                        PAGE_NO1++;

                        adapter5.setOnLoadMoreListener(new MovieDetailActivity.OnLoadMoreListener() {
                            @Override
                            public void loadMore() {
                                makeRequestforsimilarmovies(REQUEST_SEQUENTIAL);
                            }
                        });
                        if (similarmovieresponse.getPage() == similarmovieresponse.getTotalPages()) {
                            adapter5.removeOnLoadMoreListener();
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<SimilarMovieResponse> call, Throwable t) {

                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    similar.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    public void makeRequestforcast() {
        Call<CastResponse> call1 = ApiClient.getMovieService().getcast(id, Constants.API_KEY);

        call1.enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                if (response.body() != null) {
                    CastResponse tvCastResponse = response.body();
                    if (tvCastResponse.getCast().size() == 0) {
                        castrecycleview.setVisibility(View.GONE);
                        showcast.setVisibility(View.GONE);

                    } else {
                        List<Cast> cast = tvCastResponse.getCast();
                        casts.clear();
                        casts.addAll(cast);
                        progressBar.setVisibility(View.GONE);
                        castrecycleview.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {

            }
        });


    }


    public void makerequestforvideos() {
        Call<VideoResponse> call = ApiClient.getMovieService().getvideos(id, Constants.API_KEY);
        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.body() != null) {
                    VideoResponse tvVideoResponse = response.body();
                    if (tvVideoResponse.getResults().size() == 0) {
                        video.setVisibility(View.GONE);
                        videorecycleview.setVisibility(View.GONE);

                    } else {
                        List<com.example.tanvigupta.movieapp.model.Videos.Result> results1 = tvVideoResponse.getResults();
                        result1.clear();
                        result1.addAll(results1);
                        progressBar.setVisibility(View.GONE);
                        videorecycleview.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

            }
        });


    }


    @Override
    public void onClick(View v) {
        CheckReviews(v);
    }

    public interface OnLoadMoreListener {
        void loadMore();
    }

    void ballPulseAnimLoader() {
        findViewById(R.id.ballpulse).setVisibility(View.VISIBLE);


    }

    void ballPulseAnimLoaderGone(){
        findViewById(R.id.ballpulse).setVisibility(View.GONE);
    }


    void LineScaleLoaderExample(){
        findViewById(R.id.linearscaleprogressloader).setVisibility(View.VISIBLE);
    }

    void LineScaleLoaderGone(){
        findViewById(R.id.linearscaleprogressloader).setVisibility(View.GONE);
    }
}
