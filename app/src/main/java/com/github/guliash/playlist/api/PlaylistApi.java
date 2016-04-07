package com.github.guliash.playlist.api;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by gulash on 04.04.16.
 */
public interface PlaylistApi {

    String YANDEX_API = "http://download.cdn.yandex.net/mobilization-2016/";

    @GET("artists.json")
    Observable<List<Singer>> getSingers();
}
