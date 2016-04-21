package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

public class JsonSerializer implements Serializer {

    @Inject
    public JsonSerializer() {}

    @Override
    public String serializeSingers(List<Singer> singers) {
        Gson gson = new Gson();
        return gson.toJson(singers);
    }
}
