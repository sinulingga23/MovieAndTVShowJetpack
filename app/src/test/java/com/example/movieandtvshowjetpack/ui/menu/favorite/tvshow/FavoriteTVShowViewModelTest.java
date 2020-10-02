package com.example.movieandtvshowjetpack.ui.menu.favorite.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteTVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavoriteTVShowViewModel favoriteTVShowViewModel;
    private MovieAndTVShowRepository movieAndTVShowRepository = mock(MovieAndTVShowRepository.class);

    @Before
    public void setUp() {
        favoriteTVShowViewModel = new FavoriteTVShowViewModel(movieAndTVShowRepository);
    }

    @Test
    public void getFavoriteListTVShowAsPaged() {
        MutableLiveData<Resource<PagedList<TVShowEntity>>> dummyTVShowAsPaged = new MutableLiveData<>();
        PagedList<TVShowEntity> pagedList = mock(PagedList.class);

        dummyTVShowAsPaged.setValue(Resource.success(pagedList));

        when(movieAndTVShowRepository.getFavoriteListTVShowAsPaged()).thenReturn(dummyTVShowAsPaged);

        Observer<Resource<PagedList<TVShowEntity>>> observer = mock(Observer.class);

        favoriteTVShowViewModel.getFavoriteListTVShowAsPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }
}