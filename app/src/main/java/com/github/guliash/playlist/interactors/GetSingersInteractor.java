package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

/**
 * Created by gulash on 16.04.16.
 */
public interface GetSingersInteractor extends Interactor {

    interface Callbacks {
        void onSingers(List<Singer> singers);

        void onError(Throwable e);
    }

    void execute(Callbacks callbacks);

}
