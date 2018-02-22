package com.example.moviewdbtutorial.ui.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.moviewdbtutorial.MovieDbApplication;
import com.example.moviewdbtutorial.di.component.AppComponent;
import com.example.moviewdbtutorial.modules.detail.MovieDetailActivity;
import com.example.moviewdbtutorial.mvp.view.SearchView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutById();

    protected abstract void initView();

    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initActivityComponent();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutById());
        initView();
    }

    protected void initActivityComponent() {
    }

    protected AppComponent getAppComponent() {
        return ((MovieDbApplication) getApplication()).getApplicationComponent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void startDetailActivity(int id, String name) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        startActivity(intent);
    }

}
