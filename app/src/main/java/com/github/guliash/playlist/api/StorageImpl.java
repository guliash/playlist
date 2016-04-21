package com.github.guliash.playlist.api;

import android.util.Log;

import com.github.guliash.playlist.cache.Cache;
import com.github.guliash.playlist.structures.Singer;

import java.util.List;

public class StorageImpl implements Storage {

    private PlaylistApi mApi;
    private Cache mCache;

    public StorageImpl(PlaylistApi api, Cache cache) {
        mApi = api;
        mCache = cache;
    }

    @Override
    public synchronized List<Singer> getSingers() throws Throwable {
        List<Singer> singers;
        if (mCache.hasActualData()) {
            Log.e("TAG", "CACHE");
            singers = mCache.get();
        } else {
            Log.e("TAG", "CLOUD");
            singers = mApi.getSingers().execute().body();
            mCache.cache(singers);
        }

        return singers;
    }


    @Override
    public Singer getSinger(final int id) throws Throwable {
        List<Singer> singers = getSingers();
        for(Singer singer : singers) {
            if(singer.id == id) {
                return singer;
            }
        }
        throw new RuntimeException("Not found");
    }
}
