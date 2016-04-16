package com.github.guliash.playlist;

import android.app.Application;

import com.github.guliash.playlist.api.PlaylistApi;
import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.api.StorageImpl;
import com.github.guliash.playlist.cache.FileCache;
import com.github.guliash.playlist.cache.JsonDeserializer;
import com.github.guliash.playlist.cache.JsonSerializer;
import com.github.guliash.playlist.cache.RAMCache;
import com.github.guliash.playlist.cache.TwoLevelCache;
import com.github.guliash.playlist.executors.JobExecutor;
import com.github.guliash.playlist.executors.UIExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gulash on 07.04.16.
 */
public class PlaylistApplication extends Application {

    private static Storage storage;
    private static TwoLevelCache twoLevelCache;
    private static FileCache fileCache;
    private static RAMCache ramCache;
    private static UIExecutor uiExecutor;
    private static JobExecutor jobExecutor;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PlaylistApi.YANDEX_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fileCache = new FileCache(this, new JsonSerializer(), new JsonDeserializer());
        ramCache = new RAMCache();
        twoLevelCache = new TwoLevelCache(fileCache, ramCache);
        uiExecutor = new UIExecutor();
        jobExecutor = new JobExecutor(3, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        storage = new StorageImpl(retrofit.create(PlaylistApi.class), twoLevelCache);
    }

    public static Storage getStorage() {
        return storage;
    }

    public static Executor getJobExecutor() {
        return jobExecutor;
    }

    public static Executor getUIExecutor() {
        return uiExecutor;
    }
}
