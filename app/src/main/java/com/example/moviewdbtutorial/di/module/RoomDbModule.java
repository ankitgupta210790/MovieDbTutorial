package com.example.moviewdbtutorial.di.module;

import android.arch.persistence.room.Room;

import com.example.moviewdbtutorial.MovieDbApplication;
import com.example.moviewdbtutorial.database.IRepo;
import com.example.moviewdbtutorial.database.dao.MovieDataDao;
import com.example.moviewdbtutorial.database.dao.MovieDataSource;
import com.example.moviewdbtutorial.database.db.AppDatabase;
import com.example.moviewdbtutorial.model.Movie_detail;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */
@Module
public class RoomDbModule {
    private AppDatabase appDatabase;

    public RoomDbModule(MovieDbApplication mApplication) {
        appDatabase = Room.databaseBuilder(mApplication, AppDatabase.class, "movie-db").build();
    }

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }

    @Singleton
    @Provides
    MovieDataDao providesMovieDataModel(AppDatabase macDatabase) {
        return macDatabase.getMovieDataDao();
    }

    @Singleton
    @Provides
    IRepo<Movie_detail> providesMovieRepo(MovieDataDao movieDataDao) {
        return new MovieDataSource(movieDataDao);
    }
}
