package com.example.moviewdbtutorial.mvp.view;

import com.example.moviewdbtutorial.model.Movie_detail;

/**
 * Created by Ankit Gupta on 2/22/2018.
 */

public interface MovieDetailView extends BaseView {
    void onDataLoaded(Movie_detail movie_detail);
}
