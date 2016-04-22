package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Two level cache: File and Ram caches are used
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
    public boolean hasActualData() {
        return mRamCache.hasActualData() || mFileCache.hasActualData();
    }

    @Override
    public void cache(List<Singer> singers) {
        mRamCache.cache(singers);
        mFileCache.cache(singers);
    }

    @Override
    public List<Singer> get() {
        if(mRamCache.hasActualData()) {
            return mRamCache.get();
        }
        if(mFileCache.hasActualData()) {
            return mFileCache.get();
        }
        return null;
    }

    @Override
    public boolean isExpired() {
        return mRamCache.isExpired() && mFileCache.isExpired();
    }
}
