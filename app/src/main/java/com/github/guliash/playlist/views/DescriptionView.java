package com.github.guliash.playlist.views;

import com.github.guliash.playlist.structures.Singer;

/**
 * Created by gulash on 07.04.16.
 */
public interface DescriptionView {

    String SINGER_ID_EXTRA = "singer_id";

    void setSinger(Singer singer);

    void onError(Throwable e);
}
