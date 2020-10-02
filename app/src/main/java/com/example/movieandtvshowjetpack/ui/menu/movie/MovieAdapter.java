package com.example.movieandtvshowjetpack.ui.menu.movie;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieandtvshowjetpack.BuildConfig;
import com.example.movieandtvshowjetpack.R;
import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.ui.detail.movie.DetailsMovieActivity;
import com.example.movieandtvshowjetpack.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieEntity> listMovies  = new ArrayList<>();
    private final Activity activity;

    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<MovieEntity> getListMovies() {
        return listMovies;
    }

    public void setListMovies(List<MovieEntity> list) {
        if (list == null) return;
            this.listMovies.clear();
            this.listMovies.addAll(list);
            notifyDataSetChanged();

    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieEntity movie = listMovies.get(position);

        GlideApp.with(holder.itemView.getContext())
                .load(BuildConfig.URL_POSTER_PATH + movie.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)).error(R.drawable.ic_error)
                .apply(new RequestOptions().override(150, 250))
                .into(holder.poster);

        holder.title.setText(movie.getTitle());
        holder.date.setText(movie.getReleaseDate());
        holder.voteAverage.setText(String.format("%s", (float)movie.getVoteAverage()));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailsMovieActivity.class);
            intent.putExtra(DetailsMovieActivity.EXTRA_MOVIE, movie.getId());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        final ImageView poster;
        final TextView title;
        final TextView date;
        final TextView voteAverage;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            voteAverage = itemView.findViewById(R.id.vote_average_detail);
        }
    }
}
