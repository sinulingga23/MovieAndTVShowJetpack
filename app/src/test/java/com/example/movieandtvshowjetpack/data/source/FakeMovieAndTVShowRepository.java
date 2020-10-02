package com.example.movieandtvshowjetpack.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.movieandtvshowjetpack.data.source.local.LocalRepository;
import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.data.source.remote.ApiResponse;
import com.example.movieandtvshowjetpack.data.source.remote.RemoteRepository;
import com.example.movieandtvshowjetpack.data.source.remote.response.MovieResponse;
import com.example.movieandtvshowjetpack.data.source.remote.response.TVShowResponse;
import com.example.movieandtvshowjetpack.utils.AppExecutors;
import com.example.movieandtvshowjetpack.vo.Resource;

import net.bytebuddy.asm.Advice;

import java.util.ArrayList;
import java.util.List;

public class FakeMovieAndTVShowRepository implements MovieAndTVShowDataSource{
    private volatile static FakeMovieAndTVShowRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;
    private final LocalRepository localRepository;
    private final AppExecutors appExecutors;

    FakeMovieAndTVShowRepository(@NonNull RemoteRepository remoteRepository, @NonNull LocalRepository localRepository, AppExecutors appExecutors) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
        this.appExecutors = appExecutors;
    }

    public static FakeMovieAndTVShowRepository getInstance(RemoteRepository remoteRepository, LocalRepository localRepository, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (FakeMovieAndTVShowRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FakeMovieAndTVShowRepository(remoteRepository, localRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getListMovies() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {

            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getListMovies();
            }

            @Override
            protected Boolean shouldBeFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteRepository.getListMovies();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> movieResponses) {
                List<MovieEntity> movieEntities = new ArrayList<>();

                for (MovieResponse movieResponse : movieResponses) {
                    movieEntities.add(new MovieEntity(
                            movieResponse.getId(),
                            movieResponse.getTitle(),
                            movieResponse.getPosterPath(),
                            movieResponse.getOverview(),
                            movieResponse.getReleaseDate(),
                            movieResponse.getVoteAverage(),
                            null
                    ));
                }
                localRepository.insertListMovies(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TVShowEntity>>> getListTVShow() {
        return new NetworkBoundResource<List<TVShowEntity>, List<TVShowResponse>>(appExecutors) {
            @Override
            protected LiveData<List<TVShowEntity>> loadFromDB() {
                return localRepository.getListTVShow();
            }

            @Override
            protected Boolean shouldBeFetch(List<TVShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TVShowResponse>>> createCall() {
                return remoteRepository.getListTVShow();
            }

            @Override
            protected void saveCallResult(List<TVShowResponse> tvShowResponses) {
                List<TVShowEntity> tvShowEntities = new ArrayList<>();

                for (TVShowResponse tvShowResponse : tvShowResponses) {
                    tvShowEntities.add(new TVShowEntity(
                            tvShowResponse.getId(),
                            tvShowResponse.getName(),
                            tvShowResponse.getPosterPath(),
                            tvShowResponse.getOverview(),
                            tvShowResponse.getFirstAirDate(),
                            tvShowResponse.getVoteAverage(),
                            null
                    ));
                }
                localRepository.insertListTVShow(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavoriteListMoviesAsPaged() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavoriteListMoviesAsPaged(), 10).build();
            }

            @Override
            protected Boolean shouldBeFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteListTVShowAsPaged() {
        return new NetworkBoundResource<PagedList<TVShowEntity>, List<TVShowResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<TVShowEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavoriteListTVShowAsPaged(), 10).build();
            }

            @Override
            protected Boolean shouldBeFetch(PagedList<TVShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TVShowResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVShowResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovie(int id) {
        return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {

            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getMovieEntity(id);
            }

            @Override
            protected Boolean shouldBeFetch(MovieEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return remoteRepository.getMovie(id);
            }

            @Override
            protected void saveCallResult(MovieResponse movieResponse) {
                MovieEntity movieEntity = new MovieEntity();
                if (movieResponse != null) {
                    movieEntity.setId(movieResponse.getId());
                    movieEntity.setTitle(movieResponse.getTitle());
                    movieEntity.setPosterPath(movieResponse.getPosterPath());
                    movieEntity.setOverview(movieResponse.getOverview());
                    movieEntity.setReleaseDate(movieResponse.getReleaseDate());
                    movieEntity.setVoteAverage(movieResponse.getVoteAverage());
                }
                localRepository.updateMovieEntity(movieEntity);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TVShowEntity>> getTVShow(int id) {
        return new NetworkBoundResource<TVShowEntity, TVShowResponse>(appExecutors) {

            @Override
            protected LiveData<TVShowEntity> loadFromDB() {
                return localRepository.getTVShowEntity(id);
            }

            @Override
            protected Boolean shouldBeFetch(TVShowEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<TVShowResponse>> createCall() {
                return remoteRepository.getTVShow(id);
            }

            @Override
            protected void saveCallResult(TVShowResponse tvShowResponse) {
                TVShowEntity tvShowEntity = new TVShowEntity();
                if (tvShowResponse != null) {
                    tvShowEntity.setId(tvShowResponse.getId());
                    tvShowEntity.setName(tvShowResponse.getName());
                    tvShowEntity.setPosterPath(tvShowResponse.getPosterPath());
                    tvShowEntity.setOverview(tvShowResponse.getOverview());
                    tvShowEntity.setFirstAirDate(tvShowResponse.getFirstAirDate());
                    tvShowEntity.setVoteAverage(tvShowResponse.getVoteAverage());
                }
                localRepository.updateTVShowEntity(tvShowEntity);
            }
        }.asLiveData();
    }

    @Override
    public void setFavoriteMovie(MovieEntity movieEntity, boolean state) {

    }

    @Override
    public void setFavoriteTVShow(TVShowEntity tvShowEntity, boolean state) {

    }

}
