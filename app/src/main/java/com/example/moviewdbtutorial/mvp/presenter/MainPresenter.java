package com.example.moviewdbtutorial.mvp.presenter;

import android.content.Context;

import com.example.moviewdbtutorial.api.MovieApiService;
import com.example.moviewdbtutorial.database.IRepo;
import com.example.moviewdbtutorial.model.Movie_detail;
import com.example.moviewdbtutorial.model.TopRatedMovieResponse;
import com.example.moviewdbtutorial.mvp.view.MainView;
import com.example.moviewdbtutorial.util.Constants;
import com.example.moviewdbtutorial.util.Lg;
import com.example.moviewdbtutorial.util.Utils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.moviewdbtutorial.mvp.presenter.MainPresenter.CATEGORY.POPULAR;
import static com.example.moviewdbtutorial.mvp.presenter.MainPresenter.CATEGORY.TOP;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public class MainPresenter extends BasePresenter<MainView> implements Consumer<TopRatedMovieResponse> {

    private int totalPageCount;

    @Override
    public void accept(@NonNull final TopRatedMovieResponse topRatedMovieResponse) throws Exception {
        getView().onHideDialog();
        if (topRatedMovieResponse != null && topRatedMovieResponse.results != null) {
            totalPageCount = topRatedMovieResponse.totalPages;
            getView().onDataLoaded(topRatedMovieResponse.results);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                movieDataModelIRepo.insertAllData(topRatedMovieResponse.results, mCurrentRequest.ordinal());
            }
        }).start();
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }



    public enum CATEGORY {
        TOP,
        POPULAR
    }

    @Inject
    MovieApiService apiService;

    @Inject
    IRepo<Movie_detail> movieDataModelIRepo;


    private int page;

    @Inject
    Context context;

    @Inject
    public MainPresenter() {
    }

    private CATEGORY mCurrentRequest;


    public void getTopData() {
        mCurrentRequest = TOP;
        getView().onShowDialog(null);
        if (Utils.isConnectionAvailable(context)) {
            Observable<TopRatedMovieResponse> movieResponseObservable = apiService.getTopRatedMovies(Constants.API_KEY, "en-US", ++page);
            subscribe(movieResponseObservable, this);
        } else {
            getDataFromDatabase(TOP);

        }
    }


    public void getPopularData() {
        mCurrentRequest = POPULAR;
        getView().onShowDialog("Loading Data....");
        if (Utils.isConnectionAvailable(context)) {
            Observable<TopRatedMovieResponse> movieResponseObservable = apiService.getPopularMovies(Constants.API_KEY, "en-US", ++page);
            subscribe(movieResponseObservable, this);
        } else {
            getDataFromDatabase(CATEGORY.POPULAR);
        }
    }


    public void getDataFromDatabase(CATEGORY category) {
        if (movieDataModelIRepo != null) {
            Flowable<List<Movie_detail>> movie_details = movieDataModelIRepo.findAllByCategory(category.ordinal());
            movie_details.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Movie_detail>>() {
                        @Override
                        public void accept(@NonNull List<Movie_detail> movie_details) throws Exception {
                            Lg.e("Ankit", Thread.currentThread().getName());
                            getView().onHideDialog();
                            if (movie_details != null) {
                                getView().onDataLoaded(movie_details);
                            }
                        }
                    });

        }
    }
}
