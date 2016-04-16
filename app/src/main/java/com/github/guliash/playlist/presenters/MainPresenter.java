package com.github.guliash.playlist.presenters;

import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.views.MainView;

/**
 * Created by gulash on 07.04.16.
 */
public interface MainPresenter {

    void onViewAttach(MainView view);

    void onViewDetach();

    void onSingerClicked(Singer singer);

    void onSingersSearch(String query);

    void onSingersRefresh();
}
