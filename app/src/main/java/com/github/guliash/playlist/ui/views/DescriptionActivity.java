package com.github.guliash.playlist.ui.views;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.R;
import com.github.guliash.playlist.interactors.GetSingerInteractorImpl;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.presenters.DescriptionPresenter;
import com.github.guliash.playlist.ui.presenters.DescriptionPresenterImpl;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gulash on 08.04.16.
 */
public class DescriptionActivity extends AppCompatActivity implements DescriptionView {

    private DescriptionPresenter mPresenter;
    private int mSingerId;

    @Bind(R.id.image) ImageView image;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.genres) TextView genres;
    @Bind(R.id.tracks) TextView tracks;
    @Bind(R.id.albums) TextView albums;
    @Bind(R.id.link) TextView link;
    @Bind(R.id.desc) TextView desc;
    @Bind(R.id.progressBar) ProgressBar progress;
    @Bind(R.id.info) LinearLayout info;

    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState != null) {
            mSingerId = savedInstanceState.getInt(SINGER_ID_EXTRA);
        } else {
            mSingerId = getIntent().getIntExtra(SINGER_ID_EXTRA, 0);
        }

        mPresenter = new DescriptionPresenterImpl(new GetSingerInteractorImpl(PlaylistApplication.getStorage(),
                PlaylistApplication.getJobExecutor(), PlaylistApplication.getUIExecutor()));
    }

    @Override
    protected void onStart() {
        Log.e("TAG", "On START");
        super.onStart();
        mPresenter.onViewAttach(this);
        mPresenter.getSinger(mSingerId);
    }

    @Override
    protected void onStop() {
        Log.e("TAG", "On STOP");
        super.onStop();
        mPresenter.onViewDetach();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SINGER_ID_EXTRA, mSingerId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void setSinger(Singer singer) {
        Log.e("TAG", "SET SINGER");

        collapsingToolbar.setTitle(singer.name);
        name.setText(singer.name);
        genres.setText(TextUtils.join(", ", singer.genres));
        tracks.setText(String.valueOf(singer.tracks));
        albums.setText(String.valueOf(singer.albums));
        link.setText(singer.link);
        desc.setText(singer.description);

        Picasso.with(this).load(singer.cover.small).fit().centerCrop().into(image);
    }

    @Override
    public void onError(Throwable e) {
        Snackbar snackbar = Snackbar.make(collapsingToolbar, e.getMessage(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
        info.setVisibility(View.INVISIBLE);

    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.INVISIBLE);
        info.setVisibility(View.VISIBLE);
    }


}
