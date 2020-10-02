package com.example.movieandtvshowjetpack.ui.menu.favorite.movie;

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
import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.ui.detail.movie.DetailsMovieActivity;
import com.example.movieandtvshowjetpack.utils.GlideApp;


public class FavoriteMoviePagedAdapter extends PagedListAdapter<MovieEntity, FavoriteMoviePagedAdapter.FavoriteMovieViewHolder> {

    FavoriteMoviePagedAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new FavoriteMovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieViewHolder holder, int position) {
        MovieEntity movieEntity = getItem(position);
        if (movieEntity != null) {
            GlideApp.with(holder.itemView.getContext())
                    .load(BuildConfig.URL_POSTER_PATH + movieEntity.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .apply(new RequestOptions().override(150, 250))
                    .into(holder.poster);

            holder.title.setText(movieEntity.getTitle());
            holder.date.setText(movieEntity.getReleaseDate());
            holder.voteAverage.setText(String.valueOf(movieEntity.getVoteAverage()));

            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailsMovieActivity.class);
                intent.putExtra(DetailsMovieActivity.EXTRA_MOVIE, movieEntity.getId());
                context.startActivity(intent);
            });
        }
    }

    class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {
        final ImageView poster;
        final TextView title;
        final TextView date;
        final TextView voteAverage;
        FavoriteMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            voteAverage = itemView.findViewById(R.id.vote_average_detail);
        }
    }

    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieEntity oldFavoriteMovie, @NonNull MovieEntity newFavoriteMovie) {
            return oldFavoriteMovie.getTitle().equals(newFavoriteMovie.getTitle());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MovieEntity oldFavoriteMovie, @NonNull MovieEntity newFavoriteMovie) {
            return oldFavoriteMovie.equals(newFavoriteMovie);
        }
    };
}
