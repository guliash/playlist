package com.github.guliash.playlist.api;

import android.util.Log;

import com.github.guliash.playlist.cache.Cache;
import com.github.guliash.playlist.cache.Deserializer;
import com.github.guliash.playlist.cache.Serializer;
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
    private Serializer mSerializer;
    private Deserializer mDeserializer;
    private Executor mExecutor;

    public StorageImpl(PlaylistApi api, Cache cache, Serializer serializer, Deserializer deserializer) {
        mApi = api;
        mCache = cache;
        mSerializer = serializer;
        mDeserializer = deserializer;
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public void getSingers() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if(mCache.isCached() && !mCache.isExpired()) {
                    Log.e("TAG", "CACHE");
                    EventBus.getDefault().post(mCache.get(mDeserializer));
                } else {
                    try {
                        Log.e("TAG", "CLOUD");
                        List<Singer> singers = mApi.getSingers().execute().body();
                        mCache.cache(singers, mSerializer);
                        EventBus.getDefault().post(singers);
                    } catch(IOException e) {

                    }
                }
            }
        });

    }
}
