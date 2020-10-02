package com.example.movieandtvshowjetpack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.di.Injection;
import com.example.movieandtvshowjetpack.ui.detail.movie.DetailsMovieViewModel;
import com.example.movieandtvshowjetpack.ui.detail.tvshow.DetailsTVShowViewModel;
import com.example.movieandtvshowjetpack.ui.menu.favorite.movie.FavoriteMovieViewModel;
import com.example.movieandtvshowjetpack.ui.menu.favorite.tvshow.FavoriteTVShowViewModel;
import com.example.movieandtvshowjetpack.ui.menu.movie.MovieViewModel;
import com.example.movieandtvshowjetpack.ui.menu.tvshow.TVShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private final MovieAndTVShowRepository mMovieAndTVShowRepository;

    private ViewModelFactory(MovieAndTVShowRepository movieAndTVShowRepository) {
        this.mMovieAndTVShowRepository = movieAndTVShowRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            // noinspection unchecked
            return (T) new MovieViewModel(mMovieAndTVShowRepository);
        } else if (modelClass.isAssignableFrom(TVShowViewModel.class)) {
            // noinspection unchecked
            return (T) new TVShowViewModel(mMovieAndTVShowRepository);
        } else if (modelClass.isAssignableFrom(DetailsMovieViewModel.class)) {
            // noinspection unchecked
            return (T) new DetailsMovieViewModel(mMovieAndTVShowRepository);
        } else if (modelClass.isAssignableFrom(DetailsTVShowViewModel.class)) {
            // noinspection unchecked
            return (T) new DetailsTVShowViewModel(mMovieAndTVShowRepository);
        } else if (modelClass.isAssignableFrom(FavoriteMovieViewModel.class)) {
            // noinspection unchecked
            return (T) new FavoriteMovieViewModel(mMovieAndTVShowRepository);
        } else if (modelClass.isAssignableFrom(FavoriteTVShowViewModel.class)) {
            // noinspection unchecked
            return (T) new FavoriteTVShowViewModel(mMovieAndTVShowRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
