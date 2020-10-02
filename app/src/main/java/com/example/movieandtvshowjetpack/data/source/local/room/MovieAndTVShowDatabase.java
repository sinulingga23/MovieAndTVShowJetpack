package com.example.movieandtvshowjetpack.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;

@Database(entities = {MovieEntity.class, TVShowEntity.class}, version = 1, exportSchema = false)
public abstract class MovieAndTVShowDatabase extends RoomDatabase {
    private static MovieAndTVShowDatabase INSTANCE;
    public abstract MovieAndTVShowDao movieAndTVShowDao();
    private static final Object sLock = new Object();

    public static MovieAndTVShowDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieAndTVShowDatabase.class, "MovieAndTVShow.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
