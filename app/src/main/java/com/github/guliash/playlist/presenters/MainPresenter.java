package com.github.guliash.playlist.presenters;

import android.os.Bundle;

import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.views.MainView;

import java.util.List;

/**
 * Created by gulash on 07.04.16.
 */
public interface MainPresenter extends BasePresenter {

    void onCreate(MainView view, Bundle bundle);

    void onSingerClicked(Singer singer);

    void onSingersSearch(String query);

    void onSingers(List<Singer> singers);
}
