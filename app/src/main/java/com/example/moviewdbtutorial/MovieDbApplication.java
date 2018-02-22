package com.example.moviewdbtutorial;

import android.app.Application;

import com.example.moviewdbtutorial.di.component.AppComponent;
import com.example.moviewdbtutorial.di.component.DaggerAppComponent;
import com.example.moviewdbtutorial.di.module.AppModule;
import com.example.moviewdbtutorial.di.module.RoomDbModule;
import com.example.moviewdbtutorial.util.Constants;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public class MovieDbApplication extends Application {
    private AppComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerAppComponent
                    .builder()
                    .appModule(new AppModule(this, Constants.BASE_URL))
                    .roomDbModule(new RoomDbModule(this))
                    .build();
        }
    }

    public AppComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
