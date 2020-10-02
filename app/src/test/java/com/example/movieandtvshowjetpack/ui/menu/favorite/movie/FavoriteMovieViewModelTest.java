package com.example.movieandtvshowjetpack.ui.menu.favorite.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteMovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavoriteMovieViewModel favoriteMovieViewModel;
    private MovieAndTVShowRepository movieAndTVShowRepository = mock(MovieAndTVShowRepository.class);

    @Before
    public void setUp() {
        favoriteMovieViewModel = new FavoriteMovieViewModel(movieAndTVShowRepository);
    }

    @Test
    public void getFavoriteListMoviesAsPaged() {
        MutableLiveData<Resource<PagedList<MovieEntity>>> dummyMoviesAsPaged = new MutableLiveData<>();
        PagedList<MovieEntity> pagedList = mock(PagedList.class);

        dummyMoviesAsPaged.setValue(Resource.success(pagedList));

        when(movieAndTVShowRepository.getFavoriteListMoviesAsPaged()).thenReturn(dummyMoviesAsPaged);

        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);

        favoriteMovieViewModel.getFavoriteListMovieAsPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }
}