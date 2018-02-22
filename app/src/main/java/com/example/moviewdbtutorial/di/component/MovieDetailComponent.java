package com.example.moviewdbtutorial.di.component;

import com.example.moviewdbtutorial.di.module.MovieDetailModule;
import com.example.moviewdbtutorial.di.scope.PerActivity;
import com.example.moviewdbtutorial.modules.detail.MovieDetailActivity;

import dagger.Component;

/**
 * Created by Ankit Gupta on 2/22/2018.
 */
@PerActivity
@Component(modules = MovieDetailModule.class, dependencies = AppComponent.class)
public interface MovieDetailComponent {

    void inject(MovieDetailActivity activity);
}
