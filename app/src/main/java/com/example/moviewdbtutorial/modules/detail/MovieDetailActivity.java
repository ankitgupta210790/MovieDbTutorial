package com.example.moviewdbtutorial.modules.detail;

import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviewdbtutorial.R;
import com.example.moviewdbtutorial.di.component.DaggerMovieDetailComponent;
import com.example.moviewdbtutorial.di.module.MovieDetailModule;
import com.example.moviewdbtutorial.model.Movie_detail;
import com.example.moviewdbtutorial.mvp.view.MovieDetailView;
import com.example.moviewdbtutorial.ui.activity.BaseActivity;
import com.example.moviewdbtutorial.util.Constants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ankit Gupta on 2/22/2018.
 */

public class MovieDetailActivity extends BaseActivity implements MovieDetailView {

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    @BindView(R.id.movie_title)
    TextView title;
    @BindView(R.id.movie_description)
    TextView description;
    @BindView(R.id.movie_icon)
    SimpleDraweeView movieIcon;
    @BindView(R.id.movie_icon_backdrop)
    SimpleDraweeView movieIconBackdrop;
    @BindView(R.id.movie_release_date)
    TextView movieReleaseDate;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.progress)
    FrameLayout progressBar;

    @Override
    protected int getLayoutById() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initView() {
        Fresco.initialize(this);
        unbinder = ButterKnife.bind(this);
        initActionBar();
        DaggerMovieDetailComponent.builder().appComponent(getAppComponent()).movieDetailModule(new MovieDetailModule(this)).build().inject(this);
        movieDetailPresenter.loadDetail(getIntent().getExtras());
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowDialog(String message) {
        if (progressBar != null) {
            //progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHideDialog() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDataLoaded(Movie_detail movie_detail) {
        movieIcon.setImageURI(Constants.IMAGE_BASE_URL + movie_detail.posterPath);
        movieIconBackdrop.setImageURI(Constants.IMAGE_BASE_URL + movie_detail.backdropPath);
        description.setText(movie_detail.overview);
        movieReleaseDate.setText(movie_detail.releaseDate);
        title.setText(movie_detail.title);
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getIntent().getExtras().getString("name"));
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
