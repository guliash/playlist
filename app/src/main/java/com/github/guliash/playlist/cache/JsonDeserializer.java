package com.github.guliash.playlist.cache;

import android.support.annotation.NonNull;

import com.github.guliash.playlist.structures.Singer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

/**
 * Deserializes singers from JSON
 */
public class JsonDeserializer implements Deserializer {

    @Inject
    public JsonDeserializer() {}

    /**
     * Deserializes singers from the given JSON string
     * @param serialized serialized singers
     * @return list of singers
     */
    @Override
    public List<Singer> deserializeSingers(@NonNull String serialized) {
        Gson gson = new Gson();
        return gson.fromJson(serialized, new TypeToken<List<Singer>>(){}.getType());
    }
}
