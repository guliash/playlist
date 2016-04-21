package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.structures.Singer;

public interface GetSingerInteractor extends Interactor {

    interface Callbacks {
        void onSinger(Singer singer);

        void onError(Throwable e);
    }

    void execute(Callbacks callbacks, int singerId);

}
