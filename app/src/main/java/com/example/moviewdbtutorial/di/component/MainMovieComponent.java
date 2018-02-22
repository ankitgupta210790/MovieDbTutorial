package com.example.moviewdbtutorial.di.component;

import com.example.moviewdbtutorial.di.module.MainMovieModule;
import com.example.moviewdbtutorial.di.scope.PerActivity;
import com.example.moviewdbtutorial.di.scope.PerFragment;
import com.example.moviewdbtutorial.modules.top.MainActivity;
import com.example.moviewdbtutorial.ui.fragment.BaseFragment;

import dagger.Component;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */
@PerFragment
@Component(modules = MainMovieModule.class, dependencies = AppComponent.class)
public interface MainMovieComponent {
    void inject(BaseFragment baseFragment);
}
