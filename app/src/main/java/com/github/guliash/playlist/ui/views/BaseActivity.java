package com.github.guliash.playlist.ui.views;

import android.support.v7.app.AppCompatActivity;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.di.components.AppComponent;

public class BaseActivity extends AppCompatActivity {

    protected static final String LOG_TAG = "playlist";

    protected AppComponent getAppComponent() {
        return ((PlaylistApplication)getApplication()).getAppComponent();
    }

}
