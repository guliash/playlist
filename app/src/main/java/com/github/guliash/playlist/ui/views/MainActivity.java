package com.github.guliash.playlist.ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.github.guliash.playlist.ui.adapters.SingersAdapter;
import com.github.guliash.playlist.ui.presenters.MainPresenter;
import com.github.guliash.playlist.ui.presenters.MainPresenterImpl;
import com.github.guliash.playlist.structures.Singer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, SingersAdapter.Callbacks {

    private MainPresenter mPresenter;
    private RecyclerView mSingersList;
    private SingersAdapter mSingersAdapter;
    private SwipeRefreshLayout mSwipe;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSingersList = (RecyclerView)findViewById(R.id.singers);
        mSingersList.setLayoutManager(new LinearLayoutManager(this));
        mSingersAdapter = new SingersAdapter(new ArrayList<Singer>(0), this, this);
        mSingersList.setAdapter(mSingersAdapter);
        mSwipe = (SwipeRefreshLayout)findViewById(R.id.swipe);
        mProgress = (ProgressBar)findViewById(R.id.progressBar);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onSingersRefresh();
            }
        });
        mPresenter = new MainPresenterImpl();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.onSingersSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)) {
                    mPresenter.onSingersSearch(newText);
                    return true;
                }
                return false;
            }
        });

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
