package com.example.movieandtvshowjetpack.ui.detail.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.vo.Resource;

public class DetailsTVShowViewModel extends ViewModel {
    private TVShowEntity mTVShow;
    private MovieAndTVShowRepository movieAndTVShowRepository;
    private MutableLiveData<Integer> tvShowId = new MutableLiveData<>();

    public DetailsTVShowViewModel(MovieAndTVShowRepository mMovieAndTVShowRepository) {
        this.movieAndTVShowRepository = mMovieAndTVShowRepository;
    }

    public Integer getTvShowId() {
        if (tvShowId.getValue() == null) return null;
        return tvShowId.getValue();
    }

    public void setTvShowId(int tvShowId) {
        this.tvShowId.setValue(tvShowId);
    }


    public LiveData<Resource<TVShowEntity>> tvShowEntity = Transformations.switchMap(tvShowId, mTVShowId -> movieAndTVShowRepository.getTVShow(mTVShowId));


    void setFavorite() {
        if (tvShowEntity.getValue() != null) {
            TVShowEntity tv = tvShowEntity.getValue().data;
            if (tv != null) {
                final boolean newState = !tv.isFavorite();
                movieAndTVShowRepository.setFavoriteTVShow(tv, newState);
            }
        }
    }
}
