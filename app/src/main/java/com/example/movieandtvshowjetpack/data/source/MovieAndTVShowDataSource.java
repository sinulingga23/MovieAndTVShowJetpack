package com.example.movieandtvshowjetpack.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.vo.Resource;

import java.util.List;

/*
    interface yang berfungsi untuk menggabungkan
    remote repository dan local repository
 */
public interface MovieAndTVShowDataSource {
    LiveData<Resource<List<MovieEntity>>> getListMovies();
    LiveData<Resource<List<TVShowEntity>>> getListTVShow();
    LiveData<Resource<PagedList<MovieEntity>>> getFavoriteListMoviesAsPaged();
    LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteListTVShowAsPaged();
    LiveData<Resource<MovieEntity>> getMovie(int id);
    LiveData<Resource<TVShowEntity>> getTVShow(int id);
    void setFavoriteMovie(MovieEntity movieEntity, boolean state);
    void setFavoriteTVShow(TVShowEntity tvShowEntity, boolean state);
}
