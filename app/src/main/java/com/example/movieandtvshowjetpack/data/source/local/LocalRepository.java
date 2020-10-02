package com.example.movieandtvshowjetpack.data.source.local;


import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.data.source.local.room.MovieAndTVShowDao;

import java.util.List;

public class LocalRepository {
    private final MovieAndTVShowDao mMovieAndTVShowDao;
    private static LocalRepository INSTANCE;

    private LocalRepository(MovieAndTVShowDao movieAndTVShowDao) {
        this.mMovieAndTVShowDao = movieAndTVShowDao;
    }

    public static LocalRepository getInstance(MovieAndTVShowDao movieAndTVShowDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(movieAndTVShowDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getListMovies() {
        return mMovieAndTVShowDao.getListMovies();
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoriteListMoviesAsPaged() {
        return mMovieAndTVShowDao.getFavoriteListMoviesAsPaged();
    }


    public LiveData<List<TVShowEntity>> getListTVShow() {
        return mMovieAndTVShowDao.getListTVSHow();
    }

    public DataSource.Factory<Integer, TVShowEntity> getFavoriteListTVShowAsPaged() {
        return mMovieAndTVShowDao.getFavoriteListTVShowAsPaged();
    }

    public void insertListMovies(List<MovieEntity> listMovies) {
        mMovieAndTVShowDao.insertListMovies(listMovies);
    }

    public void insertListTVShow(List<TVShowEntity> listTVShow) {
        mMovieAndTVShowDao.insertListTVShow(listTVShow);
    }

    public int updateMovieEntity(MovieEntity movieEntity) {
        return mMovieAndTVShowDao.updateMovieEntity(movieEntity);
    }

    public int updateTVShowEntity(TVShowEntity tvShowEntity) {
        return mMovieAndTVShowDao.updateTVSHowEntity(tvShowEntity);
    }

    public LiveData<MovieEntity> getMovieEntity(int ID_Movie) {
        return mMovieAndTVShowDao.getMovieEntity(ID_Movie);
    }

    public LiveData<TVShowEntity> getTVShowEntity(int ID_TVShow) {
        return mMovieAndTVShowDao.getTVShowEntity(ID_TVShow);
    }

    public void setFavoriteMovie(MovieEntity movieEntity, boolean state) {
        movieEntity.setFavorite(state);
        mMovieAndTVShowDao.updateMovieEntity(movieEntity);
    }

    public void setFavoriteTVShow(TVShowEntity tvShowEntity, boolean state) {
        tvShowEntity.setFavorite(state);
        mMovieAndTVShowDao.updateTVSHowEntity(tvShowEntity);
    }
}
