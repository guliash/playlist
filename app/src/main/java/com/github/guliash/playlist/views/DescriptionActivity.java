package com.github.guliash.playlist.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.guliash.playlist.presenters.DescriptionPresenter;
import com.github.guliash.playlist.presenters.DescriptionPresenterImpl;
import com.github.guliash.playlist.structures.Singer;

/**
 * Created by gulash on 08.04.16.
 */
public class DescriptionActivity extends AppCompatActivity implements DescriptionView {

    private DescriptionPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DescriptionPresenterImpl();
        mPresenter.onCreate(this, savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void setSinger(Singer singer) {

    }


}
