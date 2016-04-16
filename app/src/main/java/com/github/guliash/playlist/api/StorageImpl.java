package com.github.guliash.playlist.api;

import android.util.Log;

import com.github.guliash.playlist.cache.Cache;
import com.github.guliash.playlist.structures.Singer;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by gulash on 10.04.16.
 */
public class StorageImpl implements Storage {

    private PlaylistApi mApi;
    private Cache mCache;
    private Executor mExecutor;

    public StorageImpl(PlaylistApi api, Cache cache) {
        mApi = api;
        mCache = cache;
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public void getSingers() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if(mCache.isCached() && !mCache.isExpired()) {
                    Log.e("TAG", "CACHE");
                    EventBus.getDefault().post(mCache.get());
                } else {
                    try {
                        Log.e("TAG", "CLOUD");
                        List<Singer> singers = mApi.getSingers().execute().body();
                        mCache.cache(singers);
                        EventBus.getDefault().post(singers);
                    } catch(IOException e) {

                    }
                }
            }
        });

    }
}
