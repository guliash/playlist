package com.github.guliash.playlist.ui.views;

import android.support.v7.app.AppCompatActivity;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.di.components.AppComponent;

/**
 * Created by gulash on 20.04.16.
 */
public class BaseActivity extends AppCompatActivity {

    protected AppComponent getAppComponent() {
        return ((PlaylistApplication)getApplication()).getAppComponent();
    }

}
