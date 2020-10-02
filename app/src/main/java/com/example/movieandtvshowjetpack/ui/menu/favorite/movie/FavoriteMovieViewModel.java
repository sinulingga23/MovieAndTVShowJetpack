package com.example.movieandtvshowjetpack.ui.menu.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.vo.Resource;

public class FavoriteMovieViewModel extends ViewModel {
    private MovieAndTVShowRepository movieAndTVShowRepository;

    public FavoriteMovieViewModel(MovieAndTVShowRepository movieAndTVShowRepository) {
        this.movieAndTVShowRepository = movieAndTVShowRepository;
    }

    LiveData<Resource<PagedList<MovieEntity>>> getFavoriteListMovieAsPaged() {
        return movieAndTVShowRepository.getFavoriteListMoviesAsPaged();
    }

    void setFavorite(MovieEntity movieEntity) {
        final boolean newState = !movieEntity.isFavorite();
        movieAndTVShowRepository.setFavoriteMovie(movieEntity, newState);
    }

}
