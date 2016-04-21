package com.github.guliash.playlist.ui.presenters;

import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.views.MainView;

/**
 * Presenter for {@link MainView}
 */
public interface MainPresenter {

    /**
     * Attaches view to the presenter
     * @param view the view to attach
     */
    void onViewAttach(MainView view);

    /**
     * Detaches view from the presenter
     */
    void onViewDetach();

    /**
     * Handles selection of a singer
     * @param singer the selected singer
     */
    void onSingerSelected(Singer singer);

    /**
     * Handles searching of singers
     * @param query the search query
     */
    void onSingersSearch(String query);

    /**
     * Handles refreshing of singer's list
     */
    void onSingersRefresh();
}
