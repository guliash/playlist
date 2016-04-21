package com.github.guliash.playlist.ui.views;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

public interface MainView {

    void showProgress();

    void hideProgress();

    void setSingers(List<Singer> singers);

    void onSingersError(Throwable e);

    void navigateToDescription(Singer singer);

}
