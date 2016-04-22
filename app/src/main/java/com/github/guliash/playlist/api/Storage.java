package com.github.guliash.playlist.api;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Interface which represents a storage of singers
 */
public interface Storage {

    /**
     * Gets singers from the storage
     * @return the list of singers
     * @throws Throwable
     */
    List<Singer> getSingers() throws Throwable;

    /**
     * Gets a singer from the storage by id
     * @param id the id of the singers
     * @return the singer
     * @throws Throwable
     */
    Singer getSinger(int id) throws Throwable;

}
