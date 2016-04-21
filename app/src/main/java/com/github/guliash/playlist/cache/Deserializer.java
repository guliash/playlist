package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

public interface Deserializer {

    List<Singer> deserializeSingers(String serialized);

}
