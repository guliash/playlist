package com.github.guliash.playlist.cache;

import android.support.annotation.NonNull;

import com.github.guliash.playlist.structures.Singer;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

/**
 * Serializes list of singers to JSON string
 */
public class JsonSerializer implements Serializer {

    @Inject
    public JsonSerializer() {}

    /**
     * Serializes singers to JSON string
     * @param singers the list of singers to serialize
     * @return the serialized JSON string
     */
    @Override
    public String serializeSingers(@NonNull List<Singer> singers) {
        Gson gson = new Gson();
        return gson.toJson(singers);
    }
}
