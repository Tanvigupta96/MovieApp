package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.model.Tvdetail.Season;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter {
    List<Season> seasons;
    LayoutInflater inflater;


    public SpinnerAdapter(@NonNull Context context, List<Season> seasons, LayoutInflater inflater) {
        super(context, 0, seasons);
        this.inflater = inflater;
        this.seasons = seasons;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);


    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        View output = convertView;
        if (output == null) {
            output = inflater.inflate(R.layout.spinner_row_layout, parent, false);

        }

        TextView textView = output.findViewById(R.id.season_text);

        Season season = seasons.get(position);


        if (season != null) {


            textView.setText(season.getSeasonNumber() + "");
            textView.setTextColor(Color.parseColor("#000000"));


        }


        return output;
    }
}
