package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Created by gulash on 11.04.16.
 */
public interface Serializer {

    String serializeSingers(List<Singer> singers);

}
