package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

public interface GetSingersInteractor extends Interactor {

    interface Callbacks {
        void onSingers(List<Singer> singers);

        void onError(Throwable e);
    }

    void execute(Callbacks callbacks);

}
