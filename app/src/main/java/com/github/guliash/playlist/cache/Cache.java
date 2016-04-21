package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

public interface Cache {

    boolean hasActualData();

    void cache(List<Singer> singers);

    List<Singer> get();

    boolean isCached();

    boolean isExpired();

}
