package com.example.moviewdbtutorial.di.module;

import com.example.moviewdbtutorial.api.MovieApiService;
import com.example.moviewdbtutorial.di.scope.PerActivity;
import com.example.moviewdbtutorial.mvp.view.MovieDetailView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by Ankit Gupta on 2/22/2018.
 */
@Module
public class MovieDetailModule {


    private MovieDetailView movieDetailView;

    public MovieDetailModule(MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
    }

    @PerActivity
    @Provides
    MovieDetailView provideMovieDetailView() {
        return movieDetailView;
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
