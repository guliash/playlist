package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.structures.Singer;

/**
 * Interactor that takes the singer from a {@link com.github.guliash.playlist.api.Storage}
 */
public interface GetSingerInteractor extends Interactor {

    /**
     * Callback which returns the result
     */
    interface Callbacks {
        /**
         * Returns the retrieved singer
         * @param singer the singer
         */
        void onSinger(Singer singer);

        /**
         * Returns an error if there was one
         * @param e the error
         */
        void onError(Throwable e);
    }

    /**
     * Executes the interactor
     * @param callbacks the callback
     * @param singerId id of the singer
     */
    void execute(Callbacks callbacks, int singerId);

}
