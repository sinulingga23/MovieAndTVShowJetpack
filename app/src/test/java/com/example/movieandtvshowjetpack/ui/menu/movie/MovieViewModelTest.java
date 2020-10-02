package com.example.movieandtvshowjetpack.ui.menu.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.ui.utils.FakeDataDummyMovie;
import com.example.movieandtvshowjetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private String USERNAME = "Movie";
    private MovieViewModel movieViewModel;
    private MovieAndTVShowRepository movieAndTVShowRepository = mock(MovieAndTVShowRepository.class);

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel(movieAndTVShowRepository);
    }

    @Test
    public void getListMovie() {
        Resource<List<MovieEntity>> resourceMovies = Resource.success(FakeDataDummyMovie.generateDummyMovies());
        MutableLiveData<Resource<List<MovieEntity>>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(resourceMovies);

        when(movieAndTVShowRepository.getListMovies()).thenReturn(dummyMovies);

        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);

        movieViewModel.setUsername(USERNAME);

        movieViewModel.listMovies.observeForever(observer);

        verify(observer).onChanged(resourceMovies);
    }
}