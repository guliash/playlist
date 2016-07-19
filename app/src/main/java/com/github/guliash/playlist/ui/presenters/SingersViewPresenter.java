package com.github.guliash.playlist.ui.presenters;

import android.content.pm.ApplicationInfo;

import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.views.SingersView;

/**
 * Presenter for {@link SingersView}
 */
public interface SingersViewPresenter {

    /**
     * Attaches view to the presenter
     * @param view the view to attach
     */
    void onViewAttach(SingersView view);

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

    /**
     * Handles selecting of an app
     * @param app chosen app
     */
    void onAppSelected(ApplicationInfo app);

}
