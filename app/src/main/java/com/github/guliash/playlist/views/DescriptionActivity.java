package com.github.guliash.playlist.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.guliash.playlist.R;
import com.github.guliash.playlist.presenters.DescriptionPresenter;
import com.github.guliash.playlist.presenters.DescriptionPresenterImpl;
import com.github.guliash.playlist.structures.Singer;
import com.squareup.picasso.Picasso;

/**
 * Created by gulash on 08.04.16.
 */
public class DescriptionActivity extends AppCompatActivity implements DescriptionView {

    private DescriptionPresenter mPresenter;
    private ImageView mImage;
    private TextView mName, mGenres, mTracks, mAlbums, mLink, mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImage = (ImageView)findViewById(R.id.image);
        mName = (TextView)findViewById(R.id.name);
        mGenres = (TextView)findViewById(R.id.genres);
        mTracks = (TextView)findViewById(R.id.tracks);
        mAlbums = (TextView)findViewById(R.id.albums);
        mLink = (TextView)findViewById(R.id.link);
        mDesc = (TextView)findViewById(R.id.desc);

        mPresenter = new DescriptionPresenterImpl();
        mPresenter.onCreate(this, (savedInstanceState != null ? savedInstanceState
                : getIntent().getExtras()));
    }

    @Override
    protected void onStart() {
        Log.e("TAG", "On START");
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        Log.e("TAG", "On STOP");
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
        mName.setText(singer.name);
        mGenres.setText(TextUtils.join(", ", singer.genres));
        mTracks.setText(String.valueOf(singer.tracks));
        mAlbums.setText(String.valueOf(singer.albums));
        mLink.setText(singer.link);
        mDesc.setText(singer.description);

        Picasso.with(this).load(singer.cover.small).fit().centerCrop().into(mImage);
    }


}
