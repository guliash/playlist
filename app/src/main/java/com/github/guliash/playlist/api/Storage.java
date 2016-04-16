package com.github.guliash.playlist.api;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Created by gulash on 10.04.16.
 */
public interface Storage {

    List<Singer> getSingers() throws Throwable;

    Singer getSinger(int id) throws Throwable;

}
