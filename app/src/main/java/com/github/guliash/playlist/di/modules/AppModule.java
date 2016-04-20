package com.github.guliash.playlist.di.modules;

import android.content.Context;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.api.PlaylistApi;
import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.api.StorageImpl;
import com.github.guliash.playlist.cache.Deserializer;
import com.github.guliash.playlist.cache.FileCache;
import com.github.guliash.playlist.cache.JsonDeserializer;
import com.github.guliash.playlist.cache.JsonSerializer;
import com.github.guliash.playlist.cache.RAMCache;
import com.github.guliash.playlist.cache.Serializer;
import com.github.guliash.playlist.cache.TwoLevelCache;
import com.github.guliash.playlist.executors.JobExecutor;
import com.github.guliash.playlist.executors.UIExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gulash on 20.04.16.
 */
@Module
public class AppModule {

    private PlaylistApplication mApplication;

    public AppModule(PlaylistApplication playlistApplication) {
        mApplication = playlistApplication;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Serializer provideSerializer(JsonSerializer serializer) {
        return serializer;
    }

    @Provides
    @Singleton
    Deserializer provideDeserializer(JsonDeserializer deserializer) {
        return deserializer;
    }

    @Provides
    @Singleton
    PlaylistApi provideCloudApi() {
        return new Retrofit.Builder().baseUrl(PlaylistApi.YANDEX_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PlaylistApi.class);
    }

    @Provides
    @Singleton
    Storage provideStorage(Context context, Serializer serializer, Deserializer deserializer,
                           PlaylistApi playlistApi) {
        return new StorageImpl(playlistApi, new TwoLevelCache(
                new FileCache(context, serializer, deserializer), new RAMCache()));
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
