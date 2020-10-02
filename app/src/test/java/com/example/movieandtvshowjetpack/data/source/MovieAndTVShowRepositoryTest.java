package com.example.movieandtvshowjetpack.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.example.movieandtvshowjetpack.data.source.local.LocalRepository;
import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.data.source.remote.RemoteRepository;
import com.example.movieandtvshowjetpack.data.source.remote.response.MovieResponse;
import com.example.movieandtvshowjetpack.data.source.remote.response.TVShowResponse;
import com.example.movieandtvshowjetpack.ui.utils.FakeDataDummyMovie;
import com.example.movieandtvshowjetpack.ui.utils.FakeDataDummyTVShow;
import com.example.movieandtvshowjetpack.ui.utils.InstantAppExecutors;
import com.example.movieandtvshowjetpack.ui.utils.LiveDataTestUtil;
import com.example.movieandtvshowjetpack.ui.utils.PagedListUtil;
import com.example.movieandtvshowjetpack.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieAndTVShowRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private LocalRepository localRepository = mock(LocalRepository.class);
    private InstantAppExecutors instantAppExecutors = mock(InstantAppExecutors.class);
    private FakeMovieAndTVShowRepository fakeMovieAndTVShowRepository = new FakeMovieAndTVShowRepository(remoteRepository, localRepository, instantAppExecutors);

    private ArrayList<MovieResponse> movieResponses = FakeDataDummyMovie.generateRemoteDummyMovies();
    private int movieId = movieResponses.get(0).getId();
    private MovieResponse movie = FakeDataDummyMovie.getRemoteMovie(movieId);
    private List<MovieEntity> movieEntities = FakeDataDummyMovie.generateDummyMovies();


    private ArrayList<TVShowResponse> tvShowResponses = FakeDataDummyTVShow.generateRemoteDummyTVShow();
    private int tvShowId = tvShowResponses.get(0).getId();
    private TVShowResponse tvShow = FakeDataDummyTVShow.getRemoteTVShow(tvShowId);
    private List<TVShowEntity> tvShowEntities = FakeDataDummyTVShow.generateDummyTVShow();

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getListMovies() {
        MutableLiveData<List<MovieEntity>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(FakeDataDummyMovie.generateDummyMovies());

        when(localRepository.getListMovies()).thenReturn(dummyMovies);

        Resource<List<MovieEntity>> result = LiveDataTestUtil.getvalue(fakeMovieAndTVShowRepository.getListMovies());

        verify(localRepository).getListMovies();
        assertNotNull(result.data);
        assertEquals(movieResponses.size(), result.data.size());
    }

    @Test
    public void getListTVShow() {
       MutableLiveData<List<TVShowEntity>> dummyTVShow = new MutableLiveData<>();
       dummyTVShow.setValue(FakeDataDummyTVShow.generateDummyTVShow());

       when(localRepository.getListTVShow()).thenReturn(dummyTVShow);

       Resource<List<TVShowEntity>> result = LiveDataTestUtil.getvalue(fakeMovieAndTVShowRepository.getListTVShow());

       verify(localRepository).getListTVShow();
       assertNotNull(result.data);
       assertEquals(tvShowResponses.size(), result.data.size());
    }

    @Test
    public void getMovie() {
        MutableLiveData<MovieEntity> dummyMovieEntity = new MutableLiveData<>();
        dummyMovieEntity.setValue(FakeDataDummyMovie.generateDummyMovie(movieId));

        when(localRepository.getMovieEntity(movieId)).thenReturn(dummyMovieEntity);

        Resource<MovieEntity> result = LiveDataTestUtil.getvalue(fakeMovieAndTVShowRepository.getMovie(movieId));

        verify(localRepository).getMovieEntity(movieId);
        assertNotNull(result);
        assertNotNull(result.data);
        assertEquals(movie.getTitle(), result.data.getTitle());
    }

    @Test
    public void getTVShow() {
        MutableLiveData<TVShowEntity> dummyTVShowEntity = new MutableLiveData<>();
        dummyTVShowEntity.setValue(FakeDataDummyTVShow.generateDummyTVShow(tvShowId));

        when(localRepository.getTVShowEntity(tvShowId)).thenReturn(dummyTVShowEntity);

        Resource<TVShowEntity> result = LiveDataTestUtil.getvalue(fakeMovieAndTVShowRepository.getTVShow(tvShowId));

        verify(localRepository).getTVShowEntity(tvShowId);
        assertNotNull(result);
        assertNotNull(result.data);
        assertEquals(tvShow.getName(), result.data.getName());
    }

    @Test
    public void getFavoriteListMoviesAsPaged() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.getFavoriteListMoviesAsPaged()).thenReturn(dataSourceFactory);
        fakeMovieAndTVShowRepository.getFavoriteListMoviesAsPaged();
        Resource<PagedList<MovieEntity>> result = Resource.success(PagedListUtil.mockPagedList(movieEntities));

        verify(localRepository).getFavoriteListMoviesAsPaged();
        assertNotNull(result.data);
        assertEquals(movieEntities.size(), result.data.size());
    }

    @Test
    public void getFavoriteListTVShowAsPaged() {
        DataSource.Factory<Integer, TVShowEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.getFavoriteListTVShowAsPaged()).thenReturn(dataSourceFactory);
        fakeMovieAndTVShowRepository.getFavoriteListTVShowAsPaged();
        Resource<PagedList<TVShowEntity>> result = Resource.success(PagedListUtil.mockPagedList(tvShowEntities));

        verify(localRepository).getFavoriteListTVShowAsPaged();
        assertNotNull(result.data);
        assertEquals(tvShowEntities.size(), result.data.size());
    }
}