package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by gulash on 11.04.16.
 */
public class JsonSerializer implements Serializer {

    @Override
    public String serializeSingers(List<Singer> singers) {
        Gson gson = new Gson();
        return gson.toJson(singers);
    }
}
