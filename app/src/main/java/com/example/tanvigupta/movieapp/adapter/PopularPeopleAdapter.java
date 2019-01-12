package com.example.tanvigupta.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanvigupta.movieapp.R;
import com.example.tanvigupta.movieapp.activity.ReviewsActivity;
import com.example.tanvigupta.movieapp.fragments.PopularPeopleFragment;
import com.example.tanvigupta.movieapp.fragments.TvShowsFragment;
import com.example.tanvigupta.movieapp.model.PopularPeople.KnownFor;
import com.example.tanvigupta.movieapp.model.PopularPeople.Result;
import com.example.tanvigupta.movieapp.network.CastClickListener;
import com.example.tanvigupta.movieapp.others.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularPeopleAdapter extends RecyclerView.Adapter<PopularPeopleViewHolder> {
    List<Result> results;
    Context context;
    String Knownfors;
    CastClickListener listener;

    private PopularPeopleFragment.OnLoadMoreListener listener1;


    public PopularPeopleAdapter(Context context, List<Result> results,CastClickListener listener) {
        this.context = context;
        this.results = results;
        this.listener=listener;
    }

    @NonNull
    @Override
    public PopularPeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = layoutInflater.inflate(R.layout.popular_ppl_row_layout, parent, false);
        return new PopularPeopleViewHolder(rowlayout);

    }

    @Override
    public void onBindViewHolder(@NonNull final PopularPeopleViewHolder holder, int i) {

        if (holder.getAdapterPosition() == results.size() - 5 && listener1!= null) {
            listener1.loadMore();
        }

        final Result result = results.get(i);
        holder.name.setText(result.getName());
        final String name=result.getName();
        //Log.d("PopularPeopleAdapter", name);




            Knownfors = TextUtils.join(",", result.getKnownFor());
            Log.d("PopularPeopleAdapter", Knownfors);
            holder.knownfor.setText(Knownfors);

            holder.knownfor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                   // builder.setView(R.layout.reviews_row_layout);
                    if(result.getKnownFor().contains(null)){
                        builder.setMessage(null);
                    }
                    else {
                        for(int i=0;i<result.getKnownFor().size();i++) {
                            builder.setMessage(result.getKnownFor().get(i)+"");
                        }
                    }
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });






             Picasso.get().load(Constants.IMAGE_BASE_URL + result.getProfilePath()).into(holder.photo);
             holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.oncastclick(results.get(holder.getAdapterPosition()).getId());
                 }
             });




    }

    public void setOnLoadMoreListener(PopularPeopleFragment.OnLoadMoreListener listener) {
        this.listener1 = listener;
    }

    public void removeOnLoadMoreListener() {
        this.listener1 = null;
    }


    @Override
    public int getItemCount() {
        return results.size();
    }
}
