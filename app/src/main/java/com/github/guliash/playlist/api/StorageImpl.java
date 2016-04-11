package com.github.guliash.playlist.api;

import android.util.Log;

import com.github.guliash.playlist.cache.Cache;
import com.github.guliash.playlist.cache.Deserializer;
import com.github.guliash.playlist.cache.Serializer;
import com.github.guliash.playlist.structures.Singer;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gulash on 10.04.16.
 */
public class StorageImpl implements Storage {

    private List<Singer> mSingers;
    private PlaylistApi mApi;
    private Cache mCache;
    private Serializer mSerializer;
    private Deserializer mDeserializer;

    public StorageImpl(PlaylistApi api, Cache cache, Serializer serializer, Deserializer deserializer) {
        mApi = api;
        mCache = cache;
        mSerializer = serializer;
        mDeserializer = deserializer;
    }

    public void getSingers() {
        if(mCache.isCached() && !mCache.isExpired()) {
            Log.e(null, "FROM CACHE");
            EventBus.getDefault().post(mCache.get(mDeserializer));
        } else {
            Log.e(null, "FROM CLOUD");
            mApi.getSingers().enqueue(new Callback<List<Singer>>() {
                @Override
                public void onResponse(Call<List<Singer>> call, Response<List<Singer>> response) {
                    mCache.cache(response.body(), mSerializer);
                    EventBus.getDefault().post(response.body());
                }

                @Override
                public void onFailure(Call<List<Singer>> call, Throwable t) {
                }
            });
        }

    }
}
