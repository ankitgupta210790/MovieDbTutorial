package com.example.moviewdbtutorial.di.component;

import com.example.moviewdbtutorial.di.module.MainActivityModule;
import com.example.moviewdbtutorial.di.module.MainMovieModule;
import com.example.moviewdbtutorial.di.scope.PerActivity;
import com.example.moviewdbtutorial.modules.search.SearchActivity;
import com.example.moviewdbtutorial.modules.top.MainActivity;
import com.example.moviewdbtutorial.ui.activity.BaseActivity;

import dagger.Component;

/**
 * Created by Ankit Gupta on 2/20/2018.
 */
@PerActivity
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {
    void inject(SearchActivity activity);
}
