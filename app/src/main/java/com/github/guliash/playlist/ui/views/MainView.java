package com.github.guliash.playlist.ui.views;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Created by gulash on 07.04.16.
 */
public interface MainView {

    void showProgress();

    void hideProgress();

    void setSingers(List<Singer> singers);

    void onSingersError(Throwable e);

    void navigateToDescription(Singer singer);

}