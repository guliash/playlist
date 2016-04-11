package com.github.guliash.playlist;

import android.app.Application;

import com.github.guliash.playlist.api.PlaylistApi;
import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.api.StorageImpl;
import com.github.guliash.playlist.cache.FileCache;
import com.github.guliash.playlist.cache.JsonDeserializer;
import com.github.guliash.playlist.cache.JsonSerializer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gulash on 07.04.16.
 */
public class PlaylistApplication extends Application {

    private static Storage storage;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PlaylistApi.YANDEX_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storage = new StorageImpl(retrofit.create(PlaylistApi.class), new FileCache(this),
                new JsonSerializer(), new JsonDeserializer());
    }

    public static Storage getStorage() {
        return storage;
    }
}
