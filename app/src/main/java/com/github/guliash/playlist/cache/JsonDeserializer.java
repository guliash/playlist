package com.github.guliash.playlist.cache;

import com.github.guliash.playlist.structures.Singer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

public class JsonDeserializer implements Deserializer {

    @Inject
    public JsonDeserializer() {}

    @Override
    public List<Singer> deserializeSingers(String serialized) {
        Gson gson = new Gson();
        return gson.fromJson(serialized, new TypeToken<List<Singer>>(){}.getType());
    }
}
