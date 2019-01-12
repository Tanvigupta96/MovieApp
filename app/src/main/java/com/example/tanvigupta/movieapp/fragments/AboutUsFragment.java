package com.example.tanvigupta.movieapp.fragments;


import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tanvigupta.movieapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment implements View.OnClickListener {
    private ImageButton share;
    private ImageButton rate;
    private ImageButton feedback;
    private CardView card1;
    private String title;
    private String link;
    private ImageView imdb;
    private CardView card3;
    private CardView card2;


    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output = inflater.inflate(R.layout.fragment_about_us, container, false);
        share = output.findViewById(R.id.share);
        rate = output.findViewById(R.id.rate);
        feedback = output.findViewById(R.id.feedback);
        card1 = output.findViewById(R.id.cardview1);
        imdb=output.findViewById(R.id.imdb);
        card3=output.findViewById(R.id.cardview3);
        card2=output.findViewById(R.id.cardview2);
        feedback.setOnClickListener(this);
        share.setOnClickListener(this);
        rate.setOnClickListener(this);
        card1.setOnClickListener(this);
        imdb.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        title="hey checkout this amazing app!";
        link="https://www.google.com/";

        return output;


    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id) {
            case R.id.feedback:
                makerequestforfeedback(v);
                break;

            case R.id.share:
                makerequestforshare(v);
                break;

            case R.id.rate:
                makerequestforrating(v);
                break;

            case R.id.cardview1:
                makerequestforgitcode(v);
                break;

            case R.id.imdb:
                makerequesforimdb(v);
                break;

            case R.id.cardview3:
                makerequestforprivacypolicy(v);
                break;
            case R.id.cardview2:
                makerequestforopensourcelicenses(v);
                break;



        } }


    public void makerequestforfeedback(View view) {

        Intent intent = new Intent();
        intent.setAction(intent.ACTION_SENDTO);
        Uri uri = Uri.parse("mailto:guptatannu.34@gmail.com");
        intent.setData(uri);
        startActivity(intent);
    }

    public void makerequestforshare(View view){
        ArrayList<String> data = new ArrayList<>();
        data.add(title);
        data.add(link);
        String data1= TextUtils.join("\n",data);
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,data1);
        startActivity(intent);
        }

    public void makerequestforrating(View view){

    }

    public void makerequestforgitcode(View view){
        Intent intent=new Intent();
        intent.setAction(intent.ACTION_VIEW);
        Uri uri=Uri.parse("https://github.com/Tanvigupta96/MovieApp");
        intent.setData(uri);
        startActivity(intent);

    }

    public void makerequesforimdb(View view){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri=Uri.parse("https://developers.themoviedb.org/3/getting-started");
        intent.setData(uri);
        startActivity(intent);

        }
        private void makerequestforprivacypolicy(View view){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri=Uri.parse("https://github.com/github/site-policy/blob/master/Policies/github-privacy-statement.md");
            intent.setData(uri);
            startActivity(intent);
        }

        private void makerequestforopensourcelicenses(View view){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri=Uri.parse("https://opensource.org/licenses");
            intent.setData(uri);
            startActivity(intent);

        }





}

