package com.example.moviewdbtutorial.database.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.moviewdbtutorial.database.dao.MovieDataDao;
import com.example.moviewdbtutorial.model.Movie_detail;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */
@Database(entities = {Movie_detail.class}, version = AppDatabase.VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    static final int VERSION = 1;

    public abstract MovieDataDao getMovieDataDao();

}
