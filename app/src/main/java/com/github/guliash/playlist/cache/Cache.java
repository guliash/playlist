package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Cache for singers
 */
public interface Cache {

    /**
     * Checks whether the cache contains actual data
     * @return true if the cache contains actual data, false if it does not
     */
    boolean hasActualData();

    /**
     * Caches the list of singers
     * @param singers the singers
     */
    void cache(List<Singer> singers);

    /**
     * Retrieves singers from the cache
     * @return the singers retrieved from the cache
     */
    List<Singer> get();

    /**
     * Checks if the cache is empty
     * @return true if the cache is not empty, false if it is
     */
    boolean isCached();

    /**
     * Checks if the cache is not expired
     * @return true if the cache is expired, false if it is not
     */
    boolean isExpired();

}
