package com.example.moviewdbtutorial.mvp.view;


import com.example.moviewdbtutorial.model.Movie_detail;

import java.util.List;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public interface MainView extends BaseView {
    void onDataLoaded(List<Movie_detail> movies);

    void onClearItems();
}
