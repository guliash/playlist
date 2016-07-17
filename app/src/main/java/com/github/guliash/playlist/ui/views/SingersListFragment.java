package com.github.guliash.playlist.ui.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

public class SingersListFragment extends BaseFragment implements MainView, SingersAdapter.Callbacks {

    @Inject
    MainPresenterImpl mPresenter;

    @Bind(R.id.singers)
    RecyclerView mSingersList;

    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    @Bind(R.id.progressBar)
    ProgressBar mProgress;

    private SingersAdapter mSingersAdapter;

    private String mQuery;

    private static final String QUERY_EXTRA = "query";

    private Callbacks mCallbacks;

    public interface Callbacks {
        void onSingerChosen(Singer singer);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if(activity instanceof Callbacks) {
            mCallbacks = (Callbacks)activity;
        } else {
            throw new RuntimeException("Activity must implement SingersListFragment#Callbacks");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        DaggerSingersComponent.builder().appComponent(getAppComponent()).build().inject(this);

        if (savedInstanceState != null) {
            mQuery = savedInstanceState.getString(QUERY_EXTRA);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, view);

        mSingersList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mSingersAdapter = new SingersAdapter(new ArrayList<Singer>(0), this.getContext(), this);
        mSingersList.setAdapter(mSingersAdapter);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onSingersRefresh();
            }
        });
        mSwipe.setColorSchemeColors(R.color.accent);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onViewAttach(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onViewDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(QUERY_EXTRA, mQuery);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_main, menu);

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
        if (!TextUtils.isEmpty(mQuery)) {
            search.setQuery(mQuery, true);
            search.setIconified(false);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onSingerClicked(Singer singer) {
        mPresenter.onSingerSelected(singer);
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
        mCallbacks.onSingerChosen(singer);
    }
}
