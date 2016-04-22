package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Interactor that takes the singers from a {@link com.github.guliash.playlist.api.Storage}
 */
public interface GetSingersInteractor extends Interactor {

    /**
     * Callback which returns the result
     */
    interface Callbacks {
        /**
         * Returns the retrieved singers
         * @param singers the singers
         */
        void onSingers(List<Singer> singers);

        /**
         * Returns an error if there was one
         * @param e the error
         */
        void onError(Throwable e);
    }

    /**
     * Executes the interactor
     * @param callbacks the callback
     */
    void execute(Callbacks callbacks);

}
