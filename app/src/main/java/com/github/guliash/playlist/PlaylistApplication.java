package com.github.guliash.playlist;

import android.app.Application;

import com.github.guliash.playlist.di.components.AppComponent;
import com.github.guliash.playlist.di.components.DaggerAppComponent;
import com.github.guliash.playlist.di.modules.AppModule;

/**
 * Created by gulash on 07.04.16.
 */
public class PlaylistApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
