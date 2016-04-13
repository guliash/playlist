package com.github.guliash.playlist.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.github.guliash.playlist.R;
import com.github.guliash.playlist.adapters.SingersAdapter;
import com.github.guliash.playlist.presenters.DescriptionPresenter;
import com.github.guliash.playlist.presenters.MainPresenter;
import com.github.guliash.playlist.presenters.MainPresenterImpl;
import com.github.guliash.playlist.structures.Singer;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, SingersAdapter.Callbacks {

    private MainPresenter mPresenter;
    private RecyclerView mSingersList;
    private SingersAdapter mSingersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSingersList = (RecyclerView)findViewById(R.id.singers);
        mSingersList.setLayoutManager(new LinearLayoutManager(this));
        mSingersList.setHasFixedSize(true);
        mSingersAdapter = new SingersAdapter(new ArrayList<Singer>(0), this, this);
        mSingersList.setAdapter(mSingersAdapter);

        mPresenter = new MainPresenterImpl();
        mPresenter.onCreate(this, savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.saveState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
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

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setSingers(List<Singer> singers) {
        mSingersAdapter.setSingers(singers);
    }

    @Override
    public void navigateToDescription(Singer singer) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra(DescriptionPresenter.SINGER_EXTRA, Parcels.wrap(singer));
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void onSingerClicked(Singer singer) {
        mPresenter.onSingerClicked(singer);
    }
}
