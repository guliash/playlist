package com.github.guliash.playlist.presenters;

import android.os.Bundle;

import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.views.MainView;

/**
 * Created by gulash on 07.04.16.
 */
public interface MainPresenter extends BasePresenter {

    void onCreate(MainView view, Bundle bundle);

    void onSingerClicked(Singer singer);
}
