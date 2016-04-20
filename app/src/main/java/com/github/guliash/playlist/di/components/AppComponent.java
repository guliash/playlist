package com.github.guliash.playlist.di.components;

import android.content.Context;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.di.modules.AppModule;
import com.github.guliash.playlist.executors.JobExecutor;
import com.github.guliash.playlist.executors.UIExecutor;
import com.github.guliash.playlist.ui.views.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by gulash on 20.04.16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(BaseActivity activity);

    Context context();

    UIExecutor uiExecutor();

    JobExecutor jobExecutor();

    Storage storage();

}
