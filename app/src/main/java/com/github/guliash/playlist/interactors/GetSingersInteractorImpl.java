package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.executors.PostExecutor;
import com.github.guliash.playlist.executors.ThreadExecutor;
import com.github.guliash.playlist.structures.Singer;

import java.util.List;

import javax.inject.Inject;

/**
 * Implementation of {@link GetSingersInteractor}
 */
public class GetSingersInteractorImpl implements GetSingersInteractor {

    /**
     * The storage to take a singer from
     */
    private Storage mStorage;

    /**
     * Background executor
     */
    private ThreadExecutor mExecutor;

    /**
     * Post executor (UI)
     */
    private PostExecutor mPostExecutor;

    private Callbacks mCallbacks;

    @Inject
    public GetSingersInteractorImpl(Storage storage, ThreadExecutor executor, PostExecutor postExecutor) {
        mExecutor = executor;
        mPostExecutor = postExecutor;
        mStorage = storage;
    }

    @Override
    public void execute(Callbacks callbacks) {
        mCallbacks = callbacks;
        mExecutor.execute(this);
    }

    /**
     * Posts the singers back to the UI thread
     * @param singers the singers
     */
    private void onSingers(final List<Singer> singers) {
        mPostExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCallbacks.onSingers(singers);
            }
        });
    }

    /**
     * Posts the error back to the UI thread
     * @param e the error
     */
    private void onError(final Throwable e) {
        mPostExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCallbacks.onError(e);
            }
        });
    }


    @Override
    public void run() {
        try {
            onSingers(mStorage.getSingers());
        } catch (Throwable e) {
            onError(e);
        }
    }
}
