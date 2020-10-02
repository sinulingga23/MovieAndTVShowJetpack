package com.example.movieandtvshowjetpack.ui.menu.tvshow;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.movieandtvshowjetpack.BuildConfig;
import com.example.movieandtvshowjetpack.R;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.ui.detail.tvshow.DetailsTVShowActivity;
import com.example.movieandtvshowjetpack.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {
    private Activity activity;
    private ArrayList<TVShowEntity> tvShowList = new ArrayList<>();

    public TVShowAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<TVShowEntity> getTvShowList() {
        return tvShowList;
    }

    public void setTvShowList(List<TVShowEntity> tvShowList) {
        if (tvShowList != null) {
            this.tvShowList.clear();;
            this.tvShowList.addAll(tvShowList);
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tv_show, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        TVShowEntity tvShowEntity = tvShowList.get(position);
        GlideApp.with(holder.itemView.getContext())
                .load(BuildConfig.URL_POSTER_PATH + tvShowEntity.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)).error(R.drawable.ic_error)
                .apply(new RequestOptions().override(150, 250))
                .into(holder.poster);

        holder.title.setText(tvShowEntity.getName());
        holder.date.setText(tvShowEntity.getFirstAirDate());
        holder.voteAverage.setText(String.format("%s", (float)tvShowEntity.getVoteAverage()));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailsTVShowActivity.class);
            intent.putExtra(DetailsTVShowActivity.EXTRA_TV_SHOW, tvShowEntity.getId());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView title;
        private TextView date;
        private TextView voteAverage;

        TVShowViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            voteAverage = itemView.findViewById(R.id.vote_average_detail);
        }
    }
}
