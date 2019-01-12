package com.example.tanvigupta.movieapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.tanvigupta.movieapp.R;
import com.squareup.picasso.Picasso;


public class FullPhotoActivity extends AppCompatActivity {

    ImageView imageView;
    Intent intent;
    String filepath;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        name=intent.getStringExtra("Name");

        Log.d("FullPhotoActivity",name);

        getSupportActionBar().setTitle(name);


       getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });





        imageView=findViewById(R.id.image);
        intent=getIntent();
        filepath=intent.getStringExtra("FilePath");
        name=intent.getStringExtra("Name");

        Log.d("FullPhotoActivity",filepath);

        Picasso.get().load(filepath).into(imageView);
        }
}
