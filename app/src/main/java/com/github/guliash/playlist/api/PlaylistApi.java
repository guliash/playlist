package com.github.guliash.playlist.api;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * The cloud API
 */
public interface PlaylistApi {

    String YANDEX_API = "http://download.cdn.yandex.net/mobilization-2016/";

    @GET("artists.json")
    Call<List<Singer>> getSingers();
}
