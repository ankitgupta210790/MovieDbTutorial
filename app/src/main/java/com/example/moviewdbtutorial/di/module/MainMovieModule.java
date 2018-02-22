package com.example.moviewdbtutorial.di.module;

import com.example.moviewdbtutorial.api.MovieApiService;
import com.example.moviewdbtutorial.di.scope.PerActivity;
import com.example.moviewdbtutorial.di.scope.PerFragment;
import com.example.moviewdbtutorial.mvp.presenter.MainPresenter;
import com.example.moviewdbtutorial.mvp.view.MainView;
import com.example.moviewdbtutorial.mvp.view.SearchView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */
@Module
public class MainMovieModule {

    private MainView mainView;

    public MainMovieModule(MainView mainView) {
        this.mainView = mainView;
    }

    @PerFragment
    @Provides
    MovieApiService movieApiService(Retrofit retrofit) {
        return retrofit.create(MovieApiService.class);
    }


    @PerFragment
    @Provides
    MainView provideView() {
        return mainView;
    }

    @PerFragment
    @Provides
    CompositeDisposable provideComposite() {
        return new CompositeDisposable();
    }
}
