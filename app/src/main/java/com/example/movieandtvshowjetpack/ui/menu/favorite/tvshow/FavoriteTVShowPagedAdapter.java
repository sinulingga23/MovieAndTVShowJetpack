package com.example.movieandtvshowjetpack.ui.menu.favorite.tvshow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.movieandtvshowjetpack.BuildConfig;
import com.example.movieandtvshowjetpack.R;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.ui.detail.tvshow.DetailsTVShowActivity;
import com.example.movieandtvshowjetpack.utils.GlideApp;


public class FavoriteTVShowPagedAdapter extends PagedListAdapter<TVShowEntity, FavoriteTVShowPagedAdapter.FavoriteTVShowViewHolder> {

    public FavoriteTVShowPagedAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FavoriteTVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tv_show, parent, false);
        return new FavoriteTVShowViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FavoriteTVShowViewHolder holder, int position) {
        TVShowEntity tvShowEntity = getItem(position);
        if (tvShowEntity != null) {
            GlideApp.with(holder.itemView.getContext())
                    .load(BuildConfig.URL_POSTER_PATH + tvShowEntity.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)).error(R.drawable.ic_error)
                    .apply(new RequestOptions().override(150, 250))
                    .into(holder.poster);

            holder.title.setText(tvShowEntity.getName());
            holder.date.setText(tvShowEntity.getFirstAirDate());
            holder.voteAverage.setText(String.format("%S", (float)tvShowEntity.getVoteAverage()));

            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailsTVShowActivity.class);
                intent.putExtra(DetailsTVShowActivity.EXTRA_TV_SHOW, tvShowEntity.getId());
                context.startActivity(intent);
            });
        }
    }

    class FavoriteTVShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView title;
        private TextView date;
        private TextView voteAverage;

        FavoriteTVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            voteAverage = itemView.findViewById(R.id.vote_average_detail);
        }
    }

    private static DiffUtil.ItemCallback<TVShowEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<TVShowEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull TVShowEntity oldFavoriteTVShow, @NonNull TVShowEntity newFavoriteTVShow) {
            return oldFavoriteTVShow.getName().equals(newFavoriteTVShow.getName());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TVShowEntity oldFavoriteTVShow, @NonNull TVShowEntity newFavoriteTVShow) {
            return oldFavoriteTVShow.equals(newFavoriteTVShow);
        }
    };
}
