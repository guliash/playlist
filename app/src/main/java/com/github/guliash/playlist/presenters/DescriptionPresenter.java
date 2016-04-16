package com.github.guliash.playlist.presenters;

import com.github.guliash.playlist.views.DescriptionView;

/**
 * Created by gulash on 07.04.16.
 */
public interface DescriptionPresenter {

    void onViewAttach(DescriptionView view);

    void onViewDetach();

    void getSinger(int id);
}
