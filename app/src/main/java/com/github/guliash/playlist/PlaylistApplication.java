package com.github.guliash.playlist;

import android.app.Application;

import com.github.guliash.playlist.api.PlaylistApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by gulash on 07.04.16.
 */
public class PlaylistApplication extends Application {

    private static PlaylistApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(
                Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PlaylistApi.YANDEX_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        api = retrofit.create(PlaylistApi.class);
    }

    public static PlaylistApi getPlaylistApi() {
        return api;
    }
}
