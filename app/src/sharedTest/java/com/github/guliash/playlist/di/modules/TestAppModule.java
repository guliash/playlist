package com.github.guliash.playlist.di.modules;

import android.app.Application;
import android.content.Context;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.executors.FakeThreadExecutor;
import com.github.guliash.playlist.executors.PostExecutor;
import com.github.guliash.playlist.executors.ThreadExecutor;
import com.github.guliash.playlist.executors.UIExecutor;
import com.github.guliash.playlist.storage.FakeStorage;
import com.github.guliash.playlist.utils.DeviceStateResolver;
import com.github.guliash.playlist.utils.FakeDeviceStateResolver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Fake AppModule
 */
@Module
public class TestAppModule {

    private Application mApplication;

    public TestAppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Storage provideStorage() {
        return new FakeStorage();
    }

    @Provides
    @Singleton
    ThreadExecutor provideJobExecutor() {
        return new FakeThreadExecutor();
    }

    @Provides
    @Singleton
    PostExecutor provideUIExecutor() {
        return new UIExecutor();
    }

    @Provides
    @Singleton
    DeviceStateResolver provideDeviceStateResolver() {
        return new FakeDeviceStateResolver();
    }
}
