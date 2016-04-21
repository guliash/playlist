package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

public interface Serializer {

    String serializeSingers(List<Singer> singers);

}
