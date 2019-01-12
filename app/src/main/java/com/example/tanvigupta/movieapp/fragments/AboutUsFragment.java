package com.example.tanvigupta.movieapp.fragments;


import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.tanvigupta.movieapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment implements View.OnClickListener {
    ImageButton share;
    ImageButton rate;
    ImageButton feedback;


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
        feedback.setOnClickListener(this);
        share.setOnClickListener(this);
        rate.setOnClickListener(this);
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


        } }


    public void makerequestforfeedback(View view) {

        Intent intent = new Intent();
        intent.setAction(intent.ACTION_SENDTO);
        Uri uri = Uri.parse("mailto:guptatannu.34@gmail.com");
        intent.setData(uri);
        startActivity(intent);
    }

    public void makerequestforshare(View view){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"hey,Checkout this amazing app!");
        startActivity(intent);

    }

    public void makerequestforrating(View view){

    }








}

