package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;


import com.example.tanvigupta.movieapp.model.Search.Result;
import com.example.tanvigupta.movieapp.network.SearchedClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemViewHolder> {
    private ArrayList<Result> results;
    private Context context;
    private SearchedClickListener listener;
    String type;


    public SearchItemAdapter(Context context, ArrayList<com.example.tanvigupta.movieapp.model.Search.Result> results,SearchedClickListener listener){
        this.context=context;
        this.results=results;
        this.listener=listener;
    }



    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.search_item,parent,false);
        return new SearchItemViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchItemViewHolder holder, int position) {
        final Result result = results.get(position);
        Picasso.get().load(Constants.IMAGE_BASE_URL + result.getPosterPath()).into(holder.poster);
        type = result.getMediaType();
        holder.type.setText(type);
        if(type.equals("movie")) {
            holder.title.setText(result.getTitle());

        }
        else{
            holder.title.setText(result.getName());
        }


        if (result.getOverview() == null|| result.getOverview().length()== 0) {
            holder.content.setText("No content to show");

        } else {

            int length = result.getOverview().length();

            SpannableString text = new SpannableString("...Read More");
            if (length > 200 && (Math.abs(length - 200)) > 10) {

                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(result.getOverview(), 0, 45);
                spannableStringBuilder.append(text);

                holder.content.setText(spannableStringBuilder.toString());
            } else {

                holder.content.setText(result.getOverview());
            }

        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String type=result.getMediaType();
                        Log.d("hey",type);
                        listener.onItemClick(results.get(holder.getAdapterPosition()).getId(),type);
                    }
                });

            }





    @Override
    public int getItemCount() {
        return results.size();
    }
}
