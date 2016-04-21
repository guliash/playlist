package com.github.guliash.playlist.ui.views;

import com.github.guliash.playlist.structures.Singer;

public interface DescriptionView {

    String SINGER_ID_EXTRA = "singer_id";

    void setSinger(Singer singer);

    void onError(Throwable e);

    void showLoading();

    void hideLoading();
}
