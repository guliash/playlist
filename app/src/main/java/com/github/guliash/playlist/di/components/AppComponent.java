package com.github.guliash.playlist.di.components;

import android.content.Context;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.di.modules.AppModule;
import com.github.guliash.playlist.executors.PostExecutor;
import com.github.guliash.playlist.executors.ThreadExecutor;
import com.github.guliash.playlist.utils.DeviceStateResolver;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context context();

    PostExecutor uiExecutor();

    ThreadExecutor jobExecutor();

    Storage storage();

    DeviceStateResolver deviceStateResolver();

}
