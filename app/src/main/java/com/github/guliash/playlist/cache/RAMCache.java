package com.github.guliash.playlist.cache;

import android.util.Log;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * RAM {@link Cache} implementation
 */
public class RAMCache implements Cache {

    /**
     * Time of the last update
     */
    private long mLastUpdate;

    /**
     * Cached list of singers
     */
    private List<Singer> mSingers;

    private static final int EXPIRATION_INTERVAL = 30 * 1000;

    @Override
    public boolean isCached() {
        return mSingers != null;
    }

    @Override
    public boolean hasActualData() {
        return isCached() && !isExpired();
    }

    @Override
    public void cache(List<Singer> singers) {
        mSingers = singers;
        mLastUpdate = System.currentTimeMillis();
    }

    @Override
    public List<Singer> get() {
        Log.e(null, "RAM CACHE");
        return mSingers;
    }

    @Override
    public boolean isExpired() {
        if(mSingers == null) {
            return false;
        }
        Log.e("TAG", (System.currentTimeMillis() - mLastUpdate) + "");
        return System.currentTimeMillis() - mLastUpdate >= EXPIRATION_INTERVAL;
    }
}
