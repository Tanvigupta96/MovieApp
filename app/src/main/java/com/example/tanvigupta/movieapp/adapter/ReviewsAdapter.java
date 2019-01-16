package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.activity.ReviewsActivity;
import com.example.tanvigupta.movieapp.fragments.MovieFragment;
import com.example.tanvigupta.movieapp.model.Reviews.Result;
import com.example.tanvigupta.movieapp.network.ReviewClickListener;
import com.example.tanvigupta.movieapp.others.Constants;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsViewHolder> {
    List<Result> results;
    Context context;
    private ReviewsActivity.OnLoadMoreListener listener1;
    private ReviewClickListener listener;

    public ReviewsAdapter(Context context,List<Result> results,ReviewClickListener listener){
        this.context=context;
        this.results=results;
        this.listener=listener;
    }



    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout=inflater.inflate(R.layout.reviews_row_layout,parent,false);
        return new ReviewsViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int i) {

        if (holder.getAdapterPosition() == results.size() - 5 && listener1 != null) {
            listener1.loadMore();
        }
        Result result=results.get(i);
        holder.name.setText(result.getAuthor());
       int length=result.getContent().length();

        SpannableString text=new SpannableString("...Read More");
        if(length>300 &&(Math.abs(length-300))>10){

            SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder(result.getContent(),0,100);
            spannableStringBuilder.append(text);

            holder.content.setText(spannableStringBuilder.toString());
        }
        else{

            holder.content.setText(result.getContent());
        }

        final String name=result.getAuthor();
        final String content=result.getContent();

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onreviewclick(name,content);
            }
        });



    }


    public void setOnLoadMoreListener(ReviewsActivity.OnLoadMoreListener listener1) {
        this.listener1 = listener1;
    }

    public void removeOnLoadMoreListener() {
        this.listener1= null;
    }


    @Override
    public int getItemCount() {
        return results.size();
    }
}
