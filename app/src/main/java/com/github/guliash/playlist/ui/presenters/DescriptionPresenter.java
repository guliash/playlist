package com.github.guliash.playlist.ui.presenters;

import com.github.guliash.playlist.ui.views.DescriptionView;

public interface DescriptionPresenter {

    void onViewAttach(DescriptionView view);

    void onViewDetach();

    void getSinger(int id);
}
