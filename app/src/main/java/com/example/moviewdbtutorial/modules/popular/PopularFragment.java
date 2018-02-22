package com.example.moviewdbtutorial.modules.popular;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.moviewdbtutorial.R;
import com.example.moviewdbtutorial.di.component.DaggerMainMovieComponent;
import com.example.moviewdbtutorial.di.module.MainMovieModule;
import com.example.moviewdbtutorial.model.Movie_detail;
import com.example.moviewdbtutorial.ui.fragment.BaseFragment;
import com.example.moviewdbtutorial.ui.view.PaginationScrollListener;

import java.util.List;

/**
 * Created by Ankit Gupta on 2/20/2018.
 */

public class PopularFragment extends BaseFragment {

    @Override
    public void onDataLoaded(List<Movie_detail> movies) {
        if (movies != null) {
            mAdapter.addData(movies);
        }
    }

    @Override
    protected int getLayoutById() {
        return R.layout.layout_movie_list;
    }

    @Override
    protected void initUI() {
        initList();
        mainPresenter.getPopularData();
    }

    @Override
    protected void initActivityComponent() {
        super.initActivityComponent();
        DaggerMainMovieComponent.builder()
                .appComponent(getAppComponent())
                .mainMovieModule(new MainMovieModule(this))
                .build()
                .inject(this);
    }

    @Override
    public RecyclerView.OnScrollListener getPageScrollListener() {
        return new PaginationScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            protected void loadMoreItems() {
                mainPresenter.getPopularData();
            }

            @Override
            public int getTotalPageCount() {
                return mainPresenter.getTotalPageCount();
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        };
    }
}
