package com.example.movieandtvshowjetpack.ui.menu.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class TVShowViewModel extends ViewModel {
    private MovieAndTVShowRepository movieAndTVShowRepository;
    private MutableLiveData<String> mLogin = new MutableLiveData<>();

    public TVShowViewModel(MovieAndTVShowRepository movieAndTVShowRepository) {
        this.movieAndTVShowRepository = movieAndTVShowRepository;
    }


    LiveData<Resource<List<TVShowEntity>>> listTVShow = Transformations.switchMap(mLogin, data -> movieAndTVShowRepository.getListTVShow());


    void setUsername(String username) {
        mLogin.setValue(username);
    }


}
