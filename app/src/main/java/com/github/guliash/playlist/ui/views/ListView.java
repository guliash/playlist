package com.github.guliash.playlist.ui.views;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * View for displaying singers
 */
public interface ListView {

    /**
     * Displays loading
     */
    void showProgress();

    /**
     * Hides loading
     */
    void hideProgress();

    /**
     * Displays the singers list
     * @param singers the singers
     */
    void setSingers(List<Singer> singers);

    /**
     * Displays the error which occurred retrieving users
     * @param e the error
     */
    void onSingersError(Throwable e);

    /**
     * Navigates to view which shows detailed info about the singer
     * @param singer the singer
     */
    void navigateToDescription(Singer singer);

}
