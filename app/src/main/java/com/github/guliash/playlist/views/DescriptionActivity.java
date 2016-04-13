package com.github.guliash.playlist.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.e("TAG", "ON CREATE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mImage = (ImageView)findViewById(R.id.image);
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
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public void setSinger(Singer singer) {

        Log.e("TAG", "SET SINGER");
        Picasso.with(this).load(singer.cover.small).fit().centerCrop().into(mImage);
    }


}
