package com.example.moviewdbtutorial.database.dao;

import com.example.moviewdbtutorial.database.IRepo;
import com.example.moviewdbtutorial.model.Movie_detail;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public class MovieDataSource implements IRepo<Movie_detail> {

    @Inject
    MovieDataDao movieDataDao;

    public MovieDataSource(MovieDataDao movieDataDao) {
        this.movieDataDao = movieDataDao;
    }

    @Override
    public long insertData(Movie_detail movieDetail) {
        return movieDataDao.insert(movieDetail);
    }

    @Override
    public Flowable<List<Movie_detail>> getAll() {
        return movieDataDao.findAll();
    }

    @Override
    public Movie_detail findById(int id) {
        return movieDataDao.findById(id);
    }

    @Override
    public int delete(Movie_detail movieDetail) {
        return movieDataDao.delete(movieDetail);
    }

    @Override
    public void insertAllData(List<Movie_detail> list, int category) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Movie_detail movieDetail : list) {
            movieDetail.category = category;
            insertData(movieDetail);
        }

    }

    @Override
    public Flowable<List<Movie_detail>> findAllByCategory(long cat) {
        return movieDataDao.findAllByCategory(cat);
    }
}
