package com.example.moviewdbtutorial.ui.fragment;

import android.app.MediaRouteButton;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviewdbtutorial.MovieDbApplication;
import com.example.moviewdbtutorial.R;
import com.example.moviewdbtutorial.di.component.AppComponent;
import com.example.moviewdbtutorial.model.Movie_detail;
import com.example.moviewdbtutorial.modules.top.MovieDataAdapter;
import com.example.moviewdbtutorial.mvp.presenter.MainPresenter;
import com.example.moviewdbtutorial.mvp.view.MainView;
import com.example.moviewdbtutorial.ui.activity.BaseActivity;
import com.example.moviewdbtutorial.ui.view.PaginationScrollListener;
import com.example.moviewdbtutorial.util.Lg;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ankit Gupta on 2/20/2018.
 */

public abstract class BaseFragment extends Fragment implements MainView {

    @BindView(R.id.movie_list)
    protected
    RecyclerView recyclerView;

    protected MovieDataAdapter mAdapter;

    @Inject
    protected MainPresenter mainPresenter;

    @Inject
    protected Context context;
    private Unbinder mUnbinder;
    @BindView(R.id.progress)
    FrameLayout progressBar;

    PaginationScrollListener paginationScrollListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
    }

    protected abstract int getLayoutById();

    protected abstract void initUI();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutById(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    protected void initList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MovieDataAdapter(getLayoutInflater());
        mAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie_detail movie_detail = (Movie_detail) view.getTag();
                if (movie_detail != null) {
                    ((BaseActivity) getActivity()).startDetailActivity(movie_detail.id, movie_detail.title);
                } else {
                    Lg.e("Error, ", " Cannot navigate to detail activity as movie detail model is null");
                }
            }
        });
        recyclerView.addOnScrollListener(getPageScrollListener());
        recyclerView.setAdapter(mAdapter);
    }


    protected void initActivityComponent() {
    }

    protected AppComponent getAppComponent() {
        return ((MovieDbApplication) getActivity().getApplication()).getApplicationComponent();
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

    @Override
    public void onShowToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    public RecyclerView.OnScrollListener getPageScrollListener() {
        return null;
    }
}
