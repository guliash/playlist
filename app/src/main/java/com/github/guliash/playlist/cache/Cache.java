package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Created by gulash on 11.04.16.
 */
public interface Cache {

    boolean isCached();

    void cache(List<Singer> singers, Serializer serializer);

    List<Singer> get(Deserializer deserializer);

    boolean isExpired();

}