package com.github.guliash.playlist.ui.presenters;

import com.github.guliash.playlist.ui.views.DescriptionView;

/**
 * The presenter for {@link DescriptionView}
 */
public interface DescriptionViewPresenter {

    /**
     * Attaches the view to the presenter
     * @param view the view to attach
     */
    void onViewAttach(DescriptionView view);

    /**
     * Detaches the view from the presenter
     */
    void onViewDetach();

    /**
     * Loads a singer by id
     * @param id singer's id
     */
    void getSinger(int id);

    /**
     * Handles a click of the url button
     */
    void urlButtonClicked();
}
