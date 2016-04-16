package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.structures.Singer;

import java.util.concurrent.Executor;

/**
 * Created by gulash on 16.04.16.
 */
public class GetSingerInteractorImpl implements GetSingerInteractor {

    private Storage mStorage;
    private Executor mExecutor;
    private Executor mPostExecutor;
    private Callbacks mCallbacks;

    public GetSingerInteractorImpl(Storage storage, Executor executor, Executor postExecutor) {
        mStorage = storage;
        mExecutor = executor;
        mPostExecutor = postExecutor;
    }

    @Override
    public void execute(Callbacks callbacks, final int singerId) {
        mCallbacks = callbacks;
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    onSinger(mStorage.getSinger(singerId));
                } catch (Throwable e) {
                    onError(e);
                }
            }
        });
    }

    private void onSinger(final Singer singer) {
        mPostExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCallbacks.onSinger(singer);
            }
        });
    }

    private void onError(final Throwable e) {
        mPostExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCallbacks.onError(e);
            }
        });
    }
}