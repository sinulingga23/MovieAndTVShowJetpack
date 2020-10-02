package com.example.movieandtvshowjetpack.ui.detail.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.vo.Resource;

public class DetailsMovieViewModel extends ViewModel {
    private MovieEntity mMovie;
    private MovieAndTVShowRepository movieAndTVShowRepository;
    private MutableLiveData<Integer> movieId = new MutableLiveData<>();

    public DetailsMovieViewModel(MovieAndTVShowRepository mMovieAndTVShowRepository) {
        this.movieAndTVShowRepository = mMovieAndTVShowRepository;
    }

    public Integer getMovieId() {
        if (movieId.getValue() == null) return null;
        return movieId.getValue();
    }

    public void setMovieId(int movieId) {
        this.movieId.setValue(movieId);
    }


    public LiveData<Resource<MovieEntity>> movieEntity = Transformations.switchMap(movieId, mMovieId -> movieAndTVShowRepository.getMovie(mMovieId));

    void setFavorite() {
        if (movieEntity.getValue() != null) {
            MovieEntity movie = movieEntity.getValue().data;
            if (movie != null) {
                final boolean newState = !movie.isFavorite();
                movieAndTVShowRepository.setFavoriteMovie(movie, newState);
            }
        }
    }

}
