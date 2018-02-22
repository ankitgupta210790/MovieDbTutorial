package com.example.moviewdbtutorial.database;

import android.arch.lifecycle.LiveData;

import com.example.moviewdbtutorial.model.Movie_detail;
import com.example.moviewdbtutorial.mvp.presenter.MainPresenter;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public interface IRepo<T> {

    long insertData(T t);

    Flowable<List<T>> getAll();

    T findById(int id);

    int delete(T t);

    void insertAllData(List<T> list, int currentRequest);

    Flowable<List<Movie_detail>> findAllByCategory(long cat);

}
