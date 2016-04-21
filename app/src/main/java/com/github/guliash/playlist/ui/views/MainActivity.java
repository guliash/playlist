package com.github.guliash.playlist.ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.github.guliash.playlist.R;
import com.github.guliash.playlist.di.components.DaggerSingersComponent;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.adapters.SingersAdapter;
import com.github.guliash.playlist.ui.presenters.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, SingersAdapter.Callbacks {

    @Inject
    MainPresenterImpl mPresenter;

    private SingersAdapter mSingersAdapter;

    @Bind(R.id.singers) RecyclerView mSingersList;

    @Bind(R.id.swipe) SwipeRefreshLayout mSwipe;

    @Bind(R.id.progressBar) ProgressBar mProgress;

    private static final String QUERY_EXTRA = "query";

    private String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerSingersComponent.builder().appComponent(getAppComponent()).build().inject(this);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        if(savedInstanceState != null) {
            mQuery = savedInstanceState.getString(QUERY_EXTRA);
        }

        mSingersList.setLayoutManager(new LinearLayoutManager(this));
        mSingersAdapter = new SingersAdapter(new ArrayList<Singer>(0), this, this);
        mSingersList.setAdapter(mSingersAdapter);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onSingersRefresh();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onViewAttach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onViewDetach();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(QUERY_EXTRA, mQuery);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView search = (SearchView) MenuItemCompat.getActionView(searchItem);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mQuery = newText;
                mPresenter.onSingersSearch(mQuery);
                return true;
            }
        });
        if(!TextUtils.isEmpty(mQuery)) {
            search.setQuery(mQuery, true);
            search.setIconified(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        mSingersList.setVisibility(View.INVISIBLE);
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mSingersList.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setSingers(List<Singer> singers) {
        mSwipe.setRefreshing(false);
        mSingersAdapter.setSingers(singers);
    }

    @Override
    public void onSingersError(Throwable e) {
        Snackbar snackbar = Snackbar.make(mProgress, e.getMessage(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void navigateToDescription(Singer singer) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra(DescriptionView.SINGER_ID_EXTRA, singer.id);
        startActivity(intent);
    }

    @Override
    public void onSingerClicked(Singer singer) {
        mPresenter.onSingerClicked(singer);
    }
}
