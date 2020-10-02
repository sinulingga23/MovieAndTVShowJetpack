package com.example.movieandtvshowjetpack.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;

import java.util.List;

@Dao
public interface MovieAndTVShowDao {

    @WorkerThread
    @Query("SELECT * FROM MovieEntity")
    LiveData<List<MovieEntity>> getListMovies();

    @WorkerThread
    @Query("SELECT * FROM MovieEntity WHERE favorite = 1 ORDER BY release_date DESC")
    DataSource.Factory<Integer, MovieEntity> getFavoriteListMoviesAsPaged();

    @WorkerThread
    @Query("SELECT * FROM TVShowEntity")
    LiveData<List<TVShowEntity>> getListTVSHow();

    @WorkerThread
    @Query("SELECT * FROM TVShowEntity WHERE favorite = 1 ORDER BY first_air_date DESC")
    DataSource.Factory<Integer, TVShowEntity> getFavoriteListTVShowAsPaged();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListMovies(List<MovieEntity> listMovies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListTVShow(List<TVShowEntity> listTVShow);

    @SuppressWarnings("deprecation")
    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateMovieEntity(MovieEntity movieEntity);


    @SuppressWarnings("deprecation")
    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateTVSHowEntity(TVShowEntity tvShowEntity);

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE ID_Movie = :ID_Movie")
    LiveData<MovieEntity> getMovieEntity(int ID_Movie);

    @Transaction
    @Query("SELECT * FROM TVShowEntity WHERE ID_TVShow = :ID_TVShow")
    LiveData<TVShowEntity> getTVShowEntity(int ID_TVShow);

}
