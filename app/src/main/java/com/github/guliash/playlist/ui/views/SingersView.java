package com.github.guliash.playlist.ui.views;

import android.content.pm.ApplicationInfo;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * View for displaying singers
 */
public interface SingersView {

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

    /**
     * Shows preview of apps
     */
    void previewApps();

    /**
     * Shows the list of apps
     */
    void showApps();

    /**
     * Hides the list of apps
     */
    void hideApps();

    /**
     * Provides the view with apps
     * @param apps apps
     */
    void setApps(List<ApplicationInfo> apps);

}
