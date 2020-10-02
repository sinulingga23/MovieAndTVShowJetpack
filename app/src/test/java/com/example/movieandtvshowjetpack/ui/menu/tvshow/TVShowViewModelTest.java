package com.example.movieandtvshowjetpack.ui.menu.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.ui.utils.FakeDataDummyTVShow;
import com.example.movieandtvshowjetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private String USERNAME = "TVShow";
    private TVShowViewModel tvShowViewModel;
    private MovieAndTVShowRepository movieAndTVShowRepository = mock(MovieAndTVShowRepository.class);

    @Before
    public void setUp() {
        tvShowViewModel = new TVShowViewModel(movieAndTVShowRepository);
    }

    @Test
    public void getListTVShow() {
        Resource<List<TVShowEntity>> resourceTShow = Resource.success(FakeDataDummyTVShow.generateDummyTVShow());
        MutableLiveData<Resource<List<TVShowEntity>>> dummyTVShow = new MutableLiveData<>();
        dummyTVShow.setValue(resourceTShow);

        when(movieAndTVShowRepository.getListTVShow()).thenReturn(dummyTVShow);

        Observer<Resource<List<TVShowEntity>>> observer = mock(Observer.class);

        tvShowViewModel.setUsername(USERNAME);

        tvShowViewModel.listTVShow.observeForever(observer);

        verify(observer).onChanged(resourceTShow);

    }
}