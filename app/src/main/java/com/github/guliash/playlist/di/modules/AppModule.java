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
import com.github.guliash.playlist.executors.PostExecutor;
import com.github.guliash.playlist.executors.ThreadExecutor;
import com.github.guliash.playlist.executors.UIExecutor;
import com.github.guliash.playlist.utils.DeviceStateResolver;
import com.github.guliash.playlist.utils.DeviceStateResolverImpl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).connectTimeout(30,
                TimeUnit.SECONDS).build();
    }

    @Provides
    @Singleton
    PlaylistApi provideCloudApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(PlaylistApi.YANDEX_API)
                .client(okHttpClient)
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
    ThreadExecutor provideJobExecutor() {
        return new JobExecutor(3, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    @Provides
    @Singleton
    PostExecutor provideUIExecutor() {
        return new UIExecutor();
    }

    @Provides
    @Singleton
    DeviceStateResolver provideDeviceStateResolver(Context context) {
        return new DeviceStateResolverImpl(context);
    }
}
