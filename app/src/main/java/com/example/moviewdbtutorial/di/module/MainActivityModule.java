package com.example.moviewdbtutorial.di.module;

import com.example.moviewdbtutorial.api.MovieApiService;
import com.example.moviewdbtutorial.di.scope.PerActivity;
import com.example.moviewdbtutorial.mvp.view.MainView;
import com.example.moviewdbtutorial.mvp.view.SearchView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by Ankit Gupta on 2/20/2018.
 */
@Module
public class MainActivityModule {

    private SearchView searchView;

    public MainActivityModule(SearchView searchView) {
        this.searchView = searchView;
    }

    @PerActivity
    @Provides
    SearchView provideSearchView() {
        return searchView;
    }

    @PerActivity
    @Provides
    MovieApiService movieApiService(Retrofit retrofit) {
        return retrofit.create(MovieApiService.class);
    }

    @PerActivity
    @Provides
    CompositeDisposable provideComposite() {
        return new CompositeDisposable();
    }
}
