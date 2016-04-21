package com.github.guliash.playlist.di.components;

import android.content.Context;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.di.modules.AppModule;
import com.github.guliash.playlist.executors.JobExecutor;
import com.github.guliash.playlist.executors.UIExecutor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context context();

    UIExecutor uiExecutor();

    JobExecutor jobExecutor();

    Storage storage();

}
