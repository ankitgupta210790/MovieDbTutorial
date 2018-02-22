package com.example.moviewdbtutorial.di.component;

import android.content.Context;

import com.example.moviewdbtutorial.MovieDbApplication;
import com.example.moviewdbtutorial.api.MovieApiService;
import com.example.moviewdbtutorial.database.IRepo;
import com.example.moviewdbtutorial.di.module.AppModule;
import com.example.moviewdbtutorial.di.module.RoomDbModule;
import com.example.moviewdbtutorial.model.Movie_detail;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */
@Singleton
@Component(modules = {AppModule.class, RoomDbModule.class})
public interface AppComponent {
    Retrofit getRetrofit();

    Context getContext();

    IRepo<Movie_detail> getRepo();
}
