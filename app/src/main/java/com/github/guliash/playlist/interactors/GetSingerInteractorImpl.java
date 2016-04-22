package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.executors.JobExecutor;
import com.github.guliash.playlist.executors.UIExecutor;
import com.github.guliash.playlist.structures.Singer;

import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Implementation of {@link GetSingerInteractor}
 */
public class GetSingerInteractorImpl implements GetSingerInteractor {

    /**
     * The storage to take a singer from
     */
    private Storage mStorage;

    /**
     * Background executor
     */
    private Executor mExecutor;

    /**
     * Post executor (UI)
     */
    private Executor mPostExecutor;

    private Callbacks mCallbacks;
    private int mSingerId;

    @Inject
    public GetSingerInteractorImpl(Storage storage, JobExecutor executor, UIExecutor postExecutor) {
        mStorage = storage;
        mExecutor = executor;
        mPostExecutor = postExecutor;
    }

    @Override
    public void execute(Callbacks callbacks, final int singerId) {
        mCallbacks = callbacks;
        mSingerId = singerId;
        mExecutor.execute(this);
    }

    /**
     * Posts the singer back to the UI thread
     * @param singer the singer
     */
    private void onSinger(final Singer singer) {
        mPostExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCallbacks.onSinger(singer);
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
            onSinger(mStorage.getSinger(mSingerId));
        } catch (Throwable e) {
            onError(e);
        }
    }
}
