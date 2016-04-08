package com.github.guliash.playlist.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mPresenter;
    private RecyclerView mSingersList;
    private SingersAdapter mSingersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSingersList = (RecyclerView)findViewById(R.id.singers);
        mSingersList.setLayoutManager(new LinearLayoutManager(this));
        mSingersAdapter = new SingersAdapter(new ArrayList<Singer>(0));
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
    }
}
