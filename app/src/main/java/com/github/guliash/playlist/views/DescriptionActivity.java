package com.github.guliash.playlist.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        mImage = (ImageView)findViewById(R.id.image);
        mPresenter = new DescriptionPresenterImpl();
        mPresenter.onCreate(this, (savedInstanceState != null ? savedInstanceState
                : getIntent().getExtras()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void setSinger(Singer singer) {
        Picasso.with(this).load(singer.cover.small).fit().into(mImage);
    }


}
