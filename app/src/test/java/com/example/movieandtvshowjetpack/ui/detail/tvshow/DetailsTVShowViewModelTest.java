package com.example.movieandtvshowjetpack.ui.detail.tvshow;

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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailsTVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailsTVShowViewModel detailsTVShowViewModel;
    private MovieAndTVShowRepository movieAndTVShowRepository = mock(MovieAndTVShowRepository.class);
    public TVShowEntity dummyTVShow = FakeDataDummyTVShow.generateDummyTVShow().get(0);
    public int tvShowId = dummyTVShow.getId();

    @Before
    public void setUp() {
        detailsTVShowViewModel = new DetailsTVShowViewModel(movieAndTVShowRepository);
        detailsTVShowViewModel.setTvShowId(tvShowId);
    }

    @Test
    public void getTVShow() {
        Resource<TVShowEntity> resource = Resource.success(FakeDataDummyTVShow.generateDummyTVShow(dummyTVShow.getId()));
        MutableLiveData<Resource<TVShowEntity>> tvShowEntity = new MutableLiveData<>();
        tvShowEntity.setValue(resource);

        when(movieAndTVShowRepository.getTVShow(tvShowId)).thenReturn(tvShowEntity);

        Observer<Resource<TVShowEntity>> observer = mock(Observer.class);
        detailsTVShowViewModel.tvShowEntity.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}