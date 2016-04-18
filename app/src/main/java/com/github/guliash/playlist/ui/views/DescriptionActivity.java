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
import com.github.guliash.playlist.ui.presenters.DescriptionPresenter;
import com.github.guliash.playlist.ui.presenters.DescriptionPresenterImpl;
import com.github.guliash.playlist.structures.Singer;
import com.squareup.picasso.Picasso;

/**
 * Created by gulash on 08.04.16.
 */
public class DescriptionActivity extends AppCompatActivity implements DescriptionView {

    private DescriptionPresenter mPresenter;
    private ImageView mImage;
    private TextView mName, mGenres, mTracks, mAlbums, mLink, mDesc;
    private ProgressBar mProgress;
    private LinearLayout mInfo;
    private int mSingerId;
    private CollapsingToolbarLayout mCollapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        mCollapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState != null) {
            mSingerId = savedInstanceState.getInt(SINGER_ID_EXTRA);
        } else {
            mSingerId = getIntent().getIntExtra(SINGER_ID_EXTRA, 0);
        }

        mImage = (ImageView)findViewById(R.id.image);
        mName = (TextView)findViewById(R.id.name);
        mGenres = (TextView)findViewById(R.id.genres);
        mTracks = (TextView)findViewById(R.id.tracks);
        mAlbums = (TextView)findViewById(R.id.albums);
        mLink = (TextView)findViewById(R.id.link);
        mDesc = (TextView)findViewById(R.id.desc);
        mProgress = (ProgressBar)findViewById(R.id.progressBar);
        mInfo = (LinearLayout)findViewById(R.id.info);

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

        mCollapsingToolbar.setTitle(singer.name);
        mName.setText(singer.name);
        mGenres.setText(TextUtils.join(", ", singer.genres));
        mTracks.setText(String.valueOf(singer.tracks));
        mAlbums.setText(String.valueOf(singer.albums));
        mLink.setText(singer.link);
        mDesc.setText(singer.description);

        Picasso.with(this).load(singer.cover.small).fit().centerCrop().into(mImage);
    }

    @Override
    public void onError(Throwable e) {
        Snackbar snackbar = Snackbar.make(mCollapsingToolbar, e.getMessage(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showLoading() {
        mProgress.setVisibility(View.VISIBLE);
        mInfo.setVisibility(View.INVISIBLE);

    }

    @Override
    public void hideLoading() {
        mProgress.setVisibility(View.INVISIBLE);
        mInfo.setVisibility(View.VISIBLE);
    }


}
