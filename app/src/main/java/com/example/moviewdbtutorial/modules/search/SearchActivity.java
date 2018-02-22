package com.example.moviewdbtutorial.modules.search;

import android.app.MediaRouteButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviewdbtutorial.R;
import com.example.moviewdbtutorial.di.component.DaggerMainActivityComponent;
import com.example.moviewdbtutorial.di.module.MainActivityModule;
import com.example.moviewdbtutorial.model.Movie_detail;
import com.example.moviewdbtutorial.modules.top.MovieDataAdapter;
import com.example.moviewdbtutorial.mvp.view.SearchView;
import com.example.moviewdbtutorial.ui.activity.BaseActivity;
import com.example.moviewdbtutorial.ui.view.PaginationScrollListener;
import com.example.moviewdbtutorial.util.Lg;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ankit Gupta on 2/20/2018.
 */

public class SearchActivity extends BaseActivity implements SearchView {

    @Inject
    SearchPresenter searchPresenter;

    @BindView(R.id.movie_list)
    RecyclerView recyclerView;

    protected MovieDataAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.progress)
    FrameLayout progressBar;

    @Override
    protected int getLayoutById() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        initActionBar();
        initList();
        String query = (String) getIntent().getExtras().get("search_query");
        searchPresenter.getSearchResult(query);
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Search Result");
    }

    @Override
    protected void initActivityComponent() {
        super.initActivityComponent();
        DaggerMainActivityComponent.builder().appComponent(getAppComponent()).mainActivityModule(new MainActivityModule(this)).build().inject(this);

    }

    @Override
    public void onSearchResult(List<Movie_detail> movieDetailList) {
        if (movieDetailList != null && !movieDetailList.isEmpty()) {
            mAdapter.addData(movieDetailList);
        }
    }

    @Override
    public void onSearchError() {
        Toast.makeText(this, "Error occured while searching try after sometime", Toast.LENGTH_SHORT).show();
    }

    protected void initList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MovieDataAdapter(getLayoutInflater());
        mAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie_detail movie_detail = (Movie_detail) view.getTag();
                if (movie_detail != null) {
                    startDetailActivity(movie_detail.id, movie_detail.title);
                } else {
                    Lg.e("Error, ", " Cannot navigate to detail activity as movie detail model is null");
                }
            }
        });
        recyclerView.addOnScrollListener(new PaginationScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            protected void loadMoreItems() {
                searchPresenter.loadMoreItems();
            }

            @Override
            public int getTotalPageCount() {
                return searchPresenter.getTotalPageCount();
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowDialog(String message) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHideDialog() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
