package com.example.tanvigupta.movieapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.adapter.CustomGalleryAdapter;
import com.example.tanvigupta.movieapp.model.CastPhotos.CastPhotosResponse;
import com.example.tanvigupta.movieapp.model.CastPhotos.Profile;
import com.example.tanvigupta.movieapp.network.ApiClientForCredits;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FullPhotoActivity extends AppCompatActivity {

    private Gallery simplegallery;
    private Long castId;
    private ImageView imageView;
    private String filepath;
    private String name;
    private List<Profile> profiles=new ArrayList<>();
    private CustomGalleryAdapter adapter;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_photo);
        simplegallery=findViewById(R.id.simplegallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        intent=getIntent();
        name=intent.getStringExtra("Name");
        filepath=intent.getStringExtra("FilePath");
        castId=intent.getLongExtra("castId",0);

        Log.d("FullPhotoActivity",name);
        Log.d("FullPhotoActivity",filepath);
        Log.d("FullPhotoActivity",castId+"");

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        imageView=findViewById(R.id.image);
        Picasso.get().load(filepath).into(imageView);
        makerequestforcastphotos();


        //gallery wala code
        adapter=new CustomGalleryAdapter(getApplicationContext(),profiles);



        simplegallery.setAdapter(adapter);
        simplegallery.setSpacing(10);

        simplegallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Profile profile=profiles.get(position);
                ballPulseAnimLoaderGone();
                Picasso.get().load(Constants.IMAGE_BASE_URL+profile.getFilePath()).into(imageView);

            }
        });


        ballPulseAnimLoader();


    }



    public void makerequestforcastphotos(){
        Call<CastPhotosResponse> call= ApiClientForCredits.getCreditService().getcastphotos(castId, Constants.API_KEY);
        call.enqueue(new Callback<CastPhotosResponse>() {
            @Override
            public void onResponse(Call<CastPhotosResponse> call, Response<CastPhotosResponse> response) {
                if(response.body()!=null){
                    CastPhotosResponse castPhotosResponse=response.body();
                    Log.d("FullPhotoActivity",response+"");
                    List<Profile> profiles1=castPhotosResponse.getProfiles();
                    profiles.clear();
                    profiles.addAll(profiles1);
                    Log.d("FullPhotoActivity",profiles+"");
                    adapter.notifyDataSetChanged();

                    }
            }
            @Override
            public void onFailure(Call<CastPhotosResponse> call, Throwable t) {

            }
        });



    }


    void ballPulseAnimLoader() {
        findViewById(R.id.ballpulse).setVisibility(View.VISIBLE);


    }

    void ballPulseAnimLoaderGone(){
        findViewById(R.id.ballpulse).setVisibility(View.GONE);
    }


}
