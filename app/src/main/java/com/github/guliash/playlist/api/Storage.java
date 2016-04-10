package com.github.guliash.playlist.api;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

import rx.Observable;

/**
 * Created by gulash on 10.04.16.
 */
public interface Storage {

    Observable<List<Singer>> getSingers();

}
