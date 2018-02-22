package com.example.moviewdbtutorial.modules.detail;

import android.os.Bundle;

import com.example.moviewdbtutorial.api.MovieApiService;
import com.example.moviewdbtutorial.model.Movie_detail;
import com.example.moviewdbtutorial.mvp.presenter.BasePresenter;
import com.example.moviewdbtutorial.mvp.view.MovieDetailView;
import com.example.moviewdbtutorial.util.Constants;
import com.example.moviewdbtutorial.util.Lg;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Ankit Gupta on 2/22/2018.
 */

public class MovieDetailPresenter extends BasePresenter<MovieDetailView> implements Consumer<Movie_detail> {

    @Inject
    MovieApiService apiService;

    @Inject
    public MovieDetailPresenter() {
    }

    public void loadDetail(Bundle bundle) {
        getView().onShowDialog(null);
        if (bundle != null && bundle.getInt("id") > 0) {
            Observable<Movie_detail> observable = apiService.getMovieDetail("" + bundle.getInt("id"), Constants.API_KEY);
            subscribe(observable, this);
        } else {
            getView().onShowToast("Movie id is not valid");
        }
    }

    @Override
    public void accept(@NonNull Movie_detail movie_detail) throws Exception {
        Lg.e("Ankit", movie_detail + "");
        getView().onHideDialog();
        if (movie_detail != null) {
            getView().onDataLoaded(movie_detail);
        }
    }
}
