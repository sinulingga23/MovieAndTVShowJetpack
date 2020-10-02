package com.example.movieandtvshowjetpack.ui.detail.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.ui.utils.FakeDataDummyMovie;
import com.example.movieandtvshowjetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailsMovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailsMovieViewModel detailsMovieViewModel;
    private MovieAndTVShowRepository movieAndTVShowRepository = mock(MovieAndTVShowRepository.class);
    private MovieEntity dummyMovie = FakeDataDummyMovie.generateDummyMovies().get(0);
    private int movieId = dummyMovie.getId();

    @Before
    public void setUp() {
        detailsMovieViewModel = new DetailsMovieViewModel(movieAndTVShowRepository);
        detailsMovieViewModel.setMovieId(movieId);
    }


    @Test
    public void getMovie() {
        Resource<MovieEntity> resource = Resource.success(FakeDataDummyMovie.generateDummyMovie(dummyMovie.getId()));
        MutableLiveData<Resource<MovieEntity>> movieEntity = new MutableLiveData<>();
        movieEntity.setValue(resource);

        when(movieAndTVShowRepository.getMovie(movieId)).thenReturn(movieEntity);

        Observer<Resource<MovieEntity>> observer = mock(Observer.class);
        detailsMovieViewModel.movieEntity.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}