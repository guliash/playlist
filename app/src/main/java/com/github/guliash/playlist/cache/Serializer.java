package com.github.guliash.playlist.cache;

import android.support.annotation.NonNull;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Serializes a list of singers to a string
 */
public interface Serializer {

    /**
     * Serializes the list of singers to string
     * @param singers the list of singers
     * @return result of serialization
     */
    String serializeSingers(@NonNull List<Singer> singers);

}
