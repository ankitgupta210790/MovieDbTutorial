package com.example.moviewdbtutorial.mvp.view;

import com.example.moviewdbtutorial.model.Movie_detail;

import java.util.List;

/**
 * Created by Ankit Gupta on 2/20/2018.
 */

public interface SearchView extends BaseView {
    void onSearchResult(List<Movie_detail> movieDetailList);

    void onSearchError();

}
