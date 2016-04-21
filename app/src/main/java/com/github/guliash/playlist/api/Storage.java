package com.github.guliash.playlist.api;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

public interface Storage {

    List<Singer> getSingers() throws Throwable;

    Singer getSinger(int id) throws Throwable;

}
