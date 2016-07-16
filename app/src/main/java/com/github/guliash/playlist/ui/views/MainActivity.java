package com.github.guliash.playlist.ui.views;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.github.guliash.playlist.R;
import com.github.guliash.playlist.structures.Singer;

public class MainActivity extends BaseActivity implements SingersListFragment.Callbacks,
        SingerDescFragment.Callbacks {

    static final String SINGERS_FRAGMENT_TAG = "singers_fragment";
    static final String SINGERS_DESC_TAG = "singers_desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            showListFragment();
        }
    }

    @Override
    public void onSingerChosen(Singer singer) {
        showDescFragment(singer.id);
    }

    @Override
    public void onBackClicked() {
        getSupportFragmentManager().popBackStack();
        setStatusBarTransparent(false);
    }

    public void showListFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_fragment, new SingersListFragment(), SINGERS_FRAGMENT_TAG);
        ft.commit();
        setStatusBarTransparent(false);
    }

    public void showDescFragment(int singerId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, SingerDescFragment.newInstance(singerId), SINGERS_DESC_TAG);
        ft.addToBackStack(null);
        ft.commit();
        setStatusBarTransparent(true);
    }

    private void setStatusBarTransparent(boolean transparent) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        if(transparent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
