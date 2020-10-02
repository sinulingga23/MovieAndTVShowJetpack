package com.example.movieandtvshowjetpack.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieandtvshowjetpack.data.source.remote.response.MovieResponse;
import com.example.movieandtvshowjetpack.data.source.remote.response.TVShowResponse;
import com.example.movieandtvshowjetpack.utils.EspressoIdlingResource;
import com.example.movieandtvshowjetpack.utils.JsonHelper;

import java.util.ArrayList;
import java.util.List;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper jsonHelper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(jsonHelper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieResponse>>> getListMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResponse>>> listMovies =  new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            listMovies.setValue(ApiResponse.success(jsonHelper.loadListMovies()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return listMovies;
    }

    public LiveData<ApiResponse<List<TVShowResponse>>> getListTVShow() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TVShowResponse>>> listTVShow = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            listTVShow.setValue(ApiResponse.success(jsonHelper.loadListTVShow()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);

        return listTVShow;
    }


    public LiveData<ApiResponse<MovieResponse>> getMovie(int id) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<MovieResponse>> movie = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            movie.setValue(ApiResponse.success(jsonHelper.getMovie(id)));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);

        return movie;
    }


    public LiveData<ApiResponse<TVShowResponse>> getTVShow(int id) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<TVShowResponse>> tvShow = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            tvShow.setValue(ApiResponse.success(jsonHelper.getTVShow(id)));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return tvShow;
    }

    public interface LoadListMoviesCallback {
        void onAllListMoviesReceived(ArrayList<MovieResponse> listMoviesResponse);
        void onDataNotAvailable();
    }

    public interface LoadListTVShowCallback {
        void onAllListTVSHowReceived(ArrayList<TVShowResponse> listTVShowResponse);
        void onDataNotAvailable();
    }
    public interface GetMovieCallback {
        void onMovieReceived(MovieResponse movieResponse);
        void onDataNotAvailable();
    }

    public interface GetTVShowCallback {
        void onTVShowReceived(TVShowResponse tvShowResponse);
        void onDataNotAvailable();
    }
}
