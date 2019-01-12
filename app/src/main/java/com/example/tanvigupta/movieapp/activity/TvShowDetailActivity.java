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
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanvigupta.movieapp.DataBase.MovieDatabase;
import com.example.tanvigupta.movieapp.DataBase.TvTable;
import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.adapter.EpisodesAdapter;
import com.example.tanvigupta.movieapp.adapter.SimilarTvShowAdapter;
import com.example.tanvigupta.movieapp.adapter.CastAdapter;
import com.example.tanvigupta.movieapp.adapter.SpinnerAdapter;
import com.example.tanvigupta.movieapp.adapter.VideoAdapter;
import com.example.tanvigupta.movieapp.model.Reviews.ReviewResponse;
import com.example.tanvigupta.movieapp.model.SimilarTvShows.Result;
import com.example.tanvigupta.movieapp.model.SimilarTvShows.SimilarTvResponse;
import com.example.tanvigupta.movieapp.model.BothCast.Cast;
import com.example.tanvigupta.movieapp.model.BothCast.CastResponse;
import com.example.tanvigupta.movieapp.model.TvEpisodesDetail.Episode;
import com.example.tanvigupta.movieapp.model.TvEpisodesDetail.TvEpisodesResponse;
import com.example.tanvigupta.movieapp.model.Tvdetail.Season;
import com.example.tanvigupta.movieapp.model.Videos.VideoResponse;
import com.example.tanvigupta.movieapp.model.Tvdetail.TvDetailResponse;
import com.example.tanvigupta.movieapp.network.ApiClientForTvShow;
import com.example.tanvigupta.movieapp.network.CastClickListener;
import com.example.tanvigupta.movieapp.network.OnEpisodeClickListener;
import com.example.tanvigupta.movieapp.network.TvShowClickListener;
import com.example.tanvigupta.movieapp.network.VideoClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private long id;

    private String review;
    private TextView ratingtxt;
    private TextView overviewtxt;
    private TextView firstairdatetxt;
    private TextView runningtimetxt;
    private TextView origincountrytxt;
    private TextView statustxt;
    private TextView networktxt;
    private ImageView coverimage;
    private TextView reviews;
    private LinearLayout Parent;
    private TextView origin;

    private TextView similartextview;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private AppBarLayout appBarLayout;
    private RecyclerView similar;
    private RecyclerView videorecycleview;
    private List<Result> results = new ArrayList<>();
    private List<Cast> casts = new ArrayList<>();
    private List<com.example.tanvigupta.movieapp.model.Videos.Result> result1 = new ArrayList<>();
    private FrameLayout frameLayout;

    private RecyclerView castrecycleview;
    private ImageView titleimage;
    private TextView postertitle;
    private TextView postergenere;
    private TextView fixedtextview;

    private TextView video;
    private TextView showcast;
    private TextView similarcasttv;
    private TextView runtime;
    private TextView editText;
    private RecyclerView episodesrv;
    private MovieDatabase movieDatabase;


    ProgressBar progressBar;
    public static final int REQUEST_INITIAL = 1;
    public static final int REQUEST_SEQUENTIAL = 2;
    public int PAGE_NO1 = 1;

    private SimilarTvShowAdapter adapter4;
    private CastAdapter adapter;
    private VideoAdapter adaptervideo;
    String title;
    String genere;
    String homepage;
    Intent intent1;
    private Long selectedspinneritem;
    private int i = 1;

    Spinner spinner;


    private List<Season> seasonsnumber = new ArrayList<>();
    private List<Episode> episodes = new ArrayList<>();


    private SpinnerAdapter spinnerAdapter;
    private EpisodesAdapter episodesAdapter;
    private boolean checked;
    private  boolean liked;
    String image;
    SpannableString text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        appBarLayout = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        Parent=findViewById(R.id.Parent);
        origin=findViewById(R.id.origin);
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

        similar = findViewById(R.id.recycleview4);
        videorecycleview = findViewById(R.id.videorecycleview);
        castrecycleview = findViewById(R.id.recycleview5);
        liked=false;




        ratingtxt = findViewById(R.id.ratingtxt);
        overviewtxt = findViewById(R.id.overviewtxt);
        firstairdatetxt = findViewById(R.id.firstairdate);
        runningtimetxt = findViewById(R.id.runningtiime);
        origincountrytxt = findViewById(R.id.origincountry);
        statustxt = findViewById(R.id.status);
        networktxt = findViewById(R.id.networks);
        coverimage = findViewById(R.id.coverimage);
        progressBar = findViewById(R.id.progressbar);
        similartextview = findViewById(R.id.similarmovies);
        fixedtextview = findViewById(R.id.fixedtextview);
       episodesrv=findViewById(R.id.episodesrv);

        reviews = findViewById(R.id.reviews);
        video = findViewById(R.id.video);
        showcast = findViewById(R.id.showcast);
        similarcasttv = findViewById(R.id.similartv);
        runtime = findViewById(R.id.runtime);
       spinner = findViewById(R.id.spinner);
       editText=findViewById(R.id.edittext);


        titleimage = findViewById(R.id.titleimage);
        postertitle = findViewById(R.id.postertitle);
        postergenere = findViewById(R.id.postergenere);

        final Intent intent = getIntent();
        id = intent.getLongExtra("ID", 0);
        Log.d("TvShowDetailActivity", id + "");


        adapter4 = new SimilarTvShowAdapter(this, results, new TvShowClickListener() {
            @Override
            public void onshowclicked(long id) {
                Intent intent2 = new Intent(getApplicationContext(), TvShowDetailActivity.class);
                intent2.putExtra("ID", id);
                startActivity(intent2);
            }


        });
        adapter = new CastAdapter(this, casts, new CastClickListener() {
            @Override
            public void oncastclick(long id) {

                intent1 = new Intent(getApplicationContext(), CastDetailActivity.class);
                intent1.putExtra("CastId", id);
                Log.d("TvShowDetailActivity", id + "");
                startActivity(intent1);
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

        spinnerAdapter = new SpinnerAdapter(this, seasonsnumber, getLayoutInflater());

        episodesAdapter= new EpisodesAdapter(this, episodes, new OnEpisodeClickListener() {
            @Override
            public void onepisodeclick(long episodenumber, String date, String overview,String title,double voteavg) {
                Intent intent2=new Intent(getApplicationContext(),EpisodeDetailActivity.class);
                    intent2.putExtra("id",id);
                    intent2.putExtra("seasonnumber",selectedspinneritem);
                    intent2.putExtra("episodenumber",episodenumber);
                    intent2.putExtra("date",date);
                    intent2.putExtra("overview",overview);
                    intent2.putExtra("title",title);
                    intent2.putExtra("vote",voteavg);
                    startActivity(intent2);
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

       LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        episodesrv.setLayoutManager(linearLayoutManager3);


        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(similar);
        snapHelper.attachToRecyclerView(castrecycleview);
        snapHelper.attachToRecyclerView(videorecycleview);


        similar.setItemAnimator(new DefaultItemAnimator());
        castrecycleview.setItemAnimator(new DefaultItemAnimator());
        videorecycleview.setItemAnimator(new DefaultItemAnimator());

        episodesrv.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));

        episodesrv.setItemAnimator(new DefaultItemAnimator());


        progressBar.setVisibility(View.VISIBLE);
        similar.setVisibility(View.GONE);
        castrecycleview.setVisibility(View.GONE);
        videorecycleview.setVisibility(View.GONE);
        episodesrv.setVisibility(View.GONE);
        Parent.setVisibility(View.GONE);


        Call<TvDetailResponse> call = ApiClientForTvShow.getTvService().getDetailoftvshow(id, Constants.API_KEY);
        call.enqueue(new Callback<TvDetailResponse>() {
            @Override
            public void onResponse(Call<TvDetailResponse> call, Response<TvDetailResponse> response) {
                if (response.body() != null) {
                    initialize(response.body());
                }
            }

            @Override
            public void onFailure(Call<TvDetailResponse> call, Throwable t) {

            }
        });


        makeRequestforsimilarshows(REQUEST_INITIAL);


        similar.setAdapter(adapter4);
        castrecycleview.setAdapter(adapter);
        videorecycleview.setAdapter(adaptervideo);
        spinner.setAdapter(spinnerAdapter);
        episodesrv.setAdapter(episodesAdapter);


        spinner.setOnItemSelectedListener(this);
        reviews.setOnClickListener(this);

        ballPulseAnimLoader();
        LineScaleLoaderExample();




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.fav);
        checked = movieDatabase.getDao().getTvChecked(id);
        Log.d("checked",checked+" ");
        if(checked) {
            menuItem.setIcon(R.drawable.ic_favorite_red_500_18dp);
            liked=checked;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int Itemid = item.getItemId();
        if (Itemid == R.id.share) {
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
        } else if (Itemid == R.id.fav) {
           // liked = false;


            if(liked == false){
                liked=true;
                item.setIcon(R.drawable.ic_favorite_red_500_18dp);
                TvTable tvTable=new TvTable();
                tvTable.setId(id);
                tvTable.setPosterPath(image);
                tvTable.setTitle(title);
                tvTable.setChecked(true);
                movieDatabase.getDao().addTv(tvTable);
                Toast.makeText(this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                }
                else{
                item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                liked=false;
                Toast.makeText(this,"Removed from favourites",Toast.LENGTH_SHORT).show();
                int Tid=movieDatabase.getDao().getTvTableId(id);
                TvTable tvTable=new TvTable();
                tvTable.setTableId(Tid);
                movieDatabase.getDao().deleteTv(tvTable);
                }

            }
        return super.onOptionsItemSelected(item);

    }


    public void CheckReviews(View v) {


        Call<ReviewResponse> reviewResponseCall = ApiClientForTvShow.getTvService().getreviews(id, Constants.API_KEY);
        reviewResponseCall.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {


                if (response.body() != null) {
                    ReviewResponse reviewResponse = response.body();
                    //reviewId = reviewResponse.getResults().get(0).getId();
                    if (reviewResponse.getResults().size() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TvShowDetailActivity.this);
                        builder.setTitle("No reviews");
                        builder.setIcon(R.drawable.ic_thumb_down_black_18dp);
                        AlertDialog dialog = builder.create();
                        dialog.show();


                    } else {
                        review = reviewResponse.getResults().get(0).getContent();

                        //Log.d("TvShowDetailActivity", reviewId + "");
                        AlertDialog.Builder builder = new AlertDialog.Builder(TvShowDetailActivity.this);
                        builder.setTitle(review);
                        builder.setIcon(R.drawable.ic_thumb_up_black_18dp);

                        builder.setPositiveButton("view", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(TvShowDetailActivity.this, ReviewsActivity.class);
                                intent.putExtra("ID", id);
                                intent.putExtra("TITLE", title);
                                intent.putExtra("I", i);

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


    public void initialize(TvDetailResponse response) {
        Parent.setVisibility(View.VISIBLE);

        TvDetailResponse tvDetailResponse = response;
        String backdroppath = tvDetailResponse.getBackdropPath();

        double voteavg = tvDetailResponse.getVoteAverage();
        Log.d("hi",tvDetailResponse.getVoteAverage().toString());
        String overview = tvDetailResponse.getOverview();
        if(overview.length()!=0) {
            int length = overview.length();
            text = new SpannableString("...Read More");
            if (length > 1000 && (Math.abs(length - 1000)) > 10) {

                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(tvDetailResponse.getOverview(), 0, 450);
                spannableStringBuilder.append(text);

                overviewtxt.setText(spannableStringBuilder.toString());

            } else {
                overviewtxt.setText(overview);

            }

        }else{
            overviewtxt.setVisibility(View.GONE);

        }





        String firstairdate = tvDetailResponse.getFirstAirDate();
        String status = tvDetailResponse.getStatus();
        List<Long> runningtime = tvDetailResponse.getEpisodeRunTime();
        List<String> origincountry = tvDetailResponse.getOriginCountry();
        // String network = tvDetailResponse.getNetworks().get(0).getName();

        image = tvDetailResponse.getPosterPath();
        title = tvDetailResponse.getName();
        //String genere = tvDetailResponse.getGenres().get(0).getName();



        ballPulseAnimLoaderGone();
        LineScaleLoaderGone();
        Picasso.get().load(Constants.IMAGE_BASE_URL + backdroppath).into(coverimage);
        Picasso.get().load(Constants.IMAGE_BASE_URL + image).into(titleimage);
        postertitle.setText(title);


        String network = TextUtils.join(",", response.getNetworks());
        genere = TextUtils.join(",", tvDetailResponse.getGenres());

        if (tvDetailResponse.getHomepage() != null) {
            homepage = tvDetailResponse.getHomepage();

        } else {

            homepage = "null";

        }

        Log.d("TvShowDetailActivity", homepage);


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


        ratingtxt.setText(voteavg + "");
        fixedtextview.setText("/10");


        overviewtxt.setMovementMethod(new ScrollingMovementMethod());


        firstairdatetxt.setText(firstairdate);
        statustxt.setText(status);
        if (runningtime.size() == 0) {
            runningtimetxt.setVisibility(View.GONE);
            runtime.setVisibility(View.GONE);
        } else {
            runningtimetxt.setText(runningtime.get(0) + "" + "Mins");
        }

        if(origincountry.size() == 0){
            origincountrytxt.setVisibility(View.GONE);
            origin.setVisibility(View.GONE);

        }
        else{
            origincountrytxt.setText(origincountry.get(0));
        }

        networktxt.setText(network);
        postergenere.setText(genere);

        if (tvDetailResponse.getSeasons().size() == 0) {
            spinner.setVisibility(View.GONE);
        } else {
            List<Season> seasonList = tvDetailResponse.getSeasons();
            seasonsnumber.clear();
            seasonsnumber.addAll(seasonList);
            spinnerAdapter.notifyDataSetChanged();

        }


        makeRequestforcast();
        makerequestforvideos();


    }


    public void makeRequestforsimilarshows(final int requestType) {
        Call<SimilarTvResponse> call = ApiClientForTvShow.getTvService().getsimilartvshows(id, Constants.API_KEY, PAGE_NO1);
        if (requestType == REQUEST_INITIAL) {
            //retry1.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
        call.enqueue(new Callback<SimilarTvResponse>() {
            @Override
            public void onResponse(Call<SimilarTvResponse> call, Response<SimilarTvResponse> response) {
                if (response.body() != null) {
                    SimilarTvResponse similarShowResponse = response.body();
                    if (similarShowResponse.getResults().size() == 0) {
                        similar.setVisibility(View.GONE);
                        similarcasttv.setVisibility(View.GONE);

                    } else {
                        List<Result> result = similarShowResponse.getResults();
                        if (requestType == REQUEST_INITIAL) {
                            results.clear();
                            progressBar.setVisibility(View.GONE);
                            similar.setVisibility(View.VISIBLE);

                        }
                        results.addAll(result);
                        PAGE_NO1++;

                        adapter4.setOnLoadMoreListener(new TvShowDetailActivity.OnLoadMoreListener() {
                            @Override
                            public void loadMore() {
                                makeRequestforsimilarshows(REQUEST_SEQUENTIAL);
                            }
                        });
                        if (similarShowResponse.getPage() == similarShowResponse.getTotalPages()) {
                            adapter4.removeOnLoadMoreListener();
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<SimilarTvResponse> call, Throwable t) {
                if (requestType == REQUEST_INITIAL) {
                    progressBar.setVisibility(View.GONE);
                    similar.setVisibility(View.GONE);
                    // retry1.setVisibility(View.VISIBLE);
                }

            }
        });


    }


    public void makeRequestforcast() {

        Call<CastResponse> call1 = ApiClientForTvShow.getTvService().getcast(id, Constants.API_KEY);

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

    @Override
    public void onClick(View v) {


        CheckReviews(v);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long Id) {
        Season season = (Season) adapterView.getItemAtPosition(position);
        selectedspinneritem=season.getSeasonNumber();
        editText.setText("   Season "+selectedspinneritem);
        Log.d("TvShowDetailActivity", selectedspinneritem+"");


        Call<TvEpisodesResponse> call=ApiClientForTvShow.getTvService().getEpisodes(id,selectedspinneritem,Constants.API_KEY);
        call.enqueue(new Callback<TvEpisodesResponse>() {
            @Override
            public void onResponse(Call<TvEpisodesResponse> call, Response<TvEpisodesResponse> response) {
                if(response.body()!=null){
                    TvEpisodesResponse tvEpisodesResponse=response.body();
                    if(tvEpisodesResponse.getEpisodes().size()==0){
                      episodesrv.setVisibility(View.GONE);
                    }
                    else{
                        List<Episode> episodes1 = tvEpisodesResponse.getEpisodes();
                        episodes.clear();
                        episodes.addAll(episodes1);
                        episodesrv.setVisibility(View.VISIBLE);
                        episodesAdapter.notifyDataSetChanged();
                    }
                }

            }


            @Override
            public void onFailure(Call<TvEpisodesResponse> call, Throwable t) {

            }
        });
       Log.d("TvShowDetailActivity", selectedspinneritem+"");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }






    public interface OnLoadMoreListener {
        void loadMore();
    }

    public void makerequestforvideos() {
        Call<VideoResponse> call = ApiClientForTvShow.getTvService().getvideos(id, Constants.API_KEY);
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
