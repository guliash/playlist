package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.structures.Singer;

/**
 * Created by gulash on 16.04.16.
 */
public interface GetSingerInteractor extends Interactor {

    interface Callbacks {
        void onSinger(Singer singer);

        void onError(Throwable e);
    }

    void execute(Callbacks callbacks, int singerId);

}
