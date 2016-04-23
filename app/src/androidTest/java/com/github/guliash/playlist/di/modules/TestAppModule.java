package com.github.guliash.playlist.di.modules;

import android.app.Application;
import android.content.Context;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.executors.JobExecutor;
import com.github.guliash.playlist.executors.UIExecutor;
import com.github.guliash.playlist.storage.FakeStorage;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    JobExecutor provideJobExecutor() {
        return new JobExecutor(3, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    @Provides
    @Singleton
    UIExecutor provideUIExecutor() {
        return new UIExecutor();
    }
}
