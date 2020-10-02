package com.example.movieandtvshowjetpack.ui.menu.favorite.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.vo.Resource;

public class FavoriteTVShowViewModel extends ViewModel {
    private MovieAndTVShowRepository movieAndTVShowRepository;

    public FavoriteTVShowViewModel(MovieAndTVShowRepository movieAndTVShowRepository) {
        this.movieAndTVShowRepository = movieAndTVShowRepository;
    }

    LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteListTVShowAsPaged() {
        return movieAndTVShowRepository.getFavoriteListTVShowAsPaged();
    }

    void setFavorite(TVShowEntity tvShowEntity) {
        final boolean newState = !tvShowEntity.isFavorite();
        movieAndTVShowRepository.setFavoriteTVShow(tvShowEntity, newState);
    }
}
