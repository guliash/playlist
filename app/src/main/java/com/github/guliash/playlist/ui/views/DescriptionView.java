package com.github.guliash.playlist.ui.views;

import com.github.guliash.playlist.structures.Singer;

/**
 * View for displaying detailed info about a singer
 */
public interface DescriptionView {

    String SINGER_ID_EXTRA = "singer_id";

    /**
     * Displays info about the singer
     * @param singer a singer
     */
    void setSinger(Singer singer);

    /**
     * Displays error which occurred retrieving a singer from a storage
     * @param e the error
     */
    void onError(Throwable e);

    /**
     * Displays loading
     */
    void showLoading();

    /**
     * Hides loading
     */
    void hideLoading();

    /**
     * Goes to the url
     * @param url url
     */
    void goToUrl(String url);
}
