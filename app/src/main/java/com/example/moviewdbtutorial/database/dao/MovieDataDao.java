package com.example.moviewdbtutorial.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.moviewdbtutorial.model.Movie_detail;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */
@Dao
public interface MovieDataDao {

    @Query("SELECT * FROM movie_detail WHERE id=:id")
    Movie_detail findById(long id);

    @Query("SELECT * FROM " + "movie_detail")
    Flowable<List<Movie_detail>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Movie_detail movieDetail);

    @Delete
    int delete(Movie_detail movieDetail);

    @Query("SELECT * FROM movie_detail WHERE category=:cat")
    Flowable<List<Movie_detail>> findAllByCategory(long cat);
}
