package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Created by gulash on 16.04.16.
 */
public class TwoLevelCache implements Cache {

    private FileCache mFileCache;
    private RAMCache mRamCache;

    public TwoLevelCache(FileCache fileCache, RAMCache ramCache) {
        mFileCache = fileCache;
        mRamCache = ramCache;
    }

    @Override
    public boolean isCached() {
        return mRamCache.isCached() || mFileCache.isCached();
    }

    @Override
    public void cache(List<Singer> singers) {
        mRamCache.cache(singers);
        mFileCache.cache(singers);
    }

    @Override
    public List<Singer> get() {
        if(mRamCache.isCached()) {
            return mRamCache.get();
        }
        if(mFileCache.isCached()) {
            return mFileCache.get();
        }
        return null;
    }

    @Override
    public boolean isExpired() {
        return mRamCache.isExpired() && mFileCache.isExpired();
    }
}
