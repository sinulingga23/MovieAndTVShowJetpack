package com.example.movieandtvshowjetpack.di;

import android.app.Application;

import com.example.movieandtvshowjetpack.data.source.MovieAndTVShowRepository;
import com.example.movieandtvshowjetpack.data.source.local.LocalRepository;
import com.example.movieandtvshowjetpack.data.source.local.room.MovieAndTVShowDatabase;
import com.example.movieandtvshowjetpack.data.source.remote.RemoteRepository;
import com.example.movieandtvshowjetpack.utils.AppExecutors;
import com.example.movieandtvshowjetpack.utils.JsonHelper;

public class Injection {
    public static MovieAndTVShowRepository provideRepository(Application application) {
        MovieAndTVShowDatabase movieAndTVShowDatabase = MovieAndTVShowDatabase.getInstance(application);
        LocalRepository localRepository = LocalRepository.getInstance(movieAndTVShowDatabase.movieAndTVShowDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        AppExecutors appExecutors = new AppExecutors();
        return MovieAndTVShowRepository.getInstance(remoteRepository,localRepository, appExecutors);
    }
}
