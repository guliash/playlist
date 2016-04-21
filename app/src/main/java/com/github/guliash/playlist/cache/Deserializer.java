package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Deserializes singers from a {@link String}
 */
public interface Deserializer {

    /**
     * Deserializes singers from the string
     * @param serialized serialized singers
     * @return deserialized singers
     */
    List<Singer> deserializeSingers(String serialized);

}
