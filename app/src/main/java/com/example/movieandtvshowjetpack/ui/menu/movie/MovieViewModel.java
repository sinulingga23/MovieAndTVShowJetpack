package com.example.movieandtvshowjetpack.ui.menu.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.vo.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MovieAndTVShowRepository movieAndTVShowRepository;
    private MutableLiveData<String> mLogin = new MutableLiveData<>();

    public MovieViewModel(MovieAndTVShowRepository movieAndTVShowRepository) {
        this.movieAndTVShowRepository = movieAndTVShowRepository;
    }


    LiveData<Resource<List<MovieEntity>>> listMovies = Transformations.switchMap(mLogin, data -> movieAndTVShowRepository.getListMovies());


    void setUsername(String username) {
        mLogin.setValue(username);
    }
}
