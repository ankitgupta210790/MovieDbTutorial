package com.example.moviewdbtutorial.modules.top;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.moviewdbtutorial.R;
import com.example.moviewdbtutorial.drawer.DrawerFragment;
import com.example.moviewdbtutorial.modules.popular.PopularFragment;
import com.example.moviewdbtutorial.modules.search.SearchActivity;
import com.example.moviewdbtutorial.ui.activity.BaseActivity;
import com.example.moviewdbtutorial.ui.fragment.BaseFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements DrawerFragment.FragmentDrawerListener {

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private DrawerFragment drawerFragment;

    @Override
    protected int getLayoutById() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        Fresco.initialize(this);
        initActionBar();
        drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, mDrawerLayout, mToolbar);
        drawerFragment.setDrawerListener(this);
        searchView.setOnQueryTextListener(mOnQueryTextListener);
        searchView.setOnSearchViewListener(mSearchViewListener);
        replaceFragment(0);
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        replaceFragment(position);
    }

    private void replaceFragment(int position) {
        BaseFragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new TopFragment();
                title = getString(R.string.title_top);
                break;
            case 1:
                fragment = new PopularFragment();
                title = getString(R.string.title_popular);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

    private MaterialSearchView.OnQueryTextListener mOnQueryTextListener = new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            navigateToSearch(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private MaterialSearchView.SearchViewListener mSearchViewListener = new MaterialSearchView.SearchViewListener() {
        @Override
        public void onSearchViewShown() {

        }

        @Override
        public void onSearchViewClosed() {
        }
    };


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        }
        super.onBackPressed();
    }


    private void navigateToSearch(String query) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("search_query", query);
        startActivity(intent);
    }
}
