package com.example.moviewdbtutorial.modules.search;

import android.text.TextUtils;

import com.example.moviewdbtutorial.api.MovieApiService;
import com.example.moviewdbtutorial.model.TopRatedMovieResponse;
import com.example.moviewdbtutorial.mvp.presenter.BasePresenter;
import com.example.moviewdbtutorial.mvp.view.SearchView;
import com.example.moviewdbtutorial.util.Constants;

import java.io.Serializable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Ankit Gupta on 2/20/2018.
 */

public class SearchPresenter extends BasePresenter<SearchView> implements Consumer<TopRatedMovieResponse> {

    int page;

    String query;

    @Inject
    MovieApiService movieApiService;
    private int totalPageCount;

    @Inject
    public SearchPresenter() {
    }

    public void getSearchResult(String query) {
        this.query = query;
        if (!TextUtils.isEmpty(query) && query.length() > 2) {
            getView().onShowDialog(null);

            Observable<TopRatedMovieResponse> observable = movieApiService.getSearchResult(Constants.API_KEY, query, "en-US", ++page);
            subscribe(observable, this);
        } else {
            getView().onShowToast("Enter more than 2 character to search");
        }
    }

    @Override
    public void accept(@NonNull TopRatedMovieResponse topRatedMovieResponse) throws Exception {
        getView().onHideDialog();
        if (topRatedMovieResponse != null && topRatedMovieResponse.results != null) {
            totalPageCount = topRatedMovieResponse.totalPages;
            getView().onSearchResult(topRatedMovieResponse.results);
        }
        else
            getView().onSearchError();
    }

    public void loadMoreItems() {
        getSearchResult(query);
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }
}
