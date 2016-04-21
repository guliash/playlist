package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.executors.JobExecutor;
import com.github.guliash.playlist.executors.UIExecutor;
import com.github.guliash.playlist.structures.Singer;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class GetSingerInteractorImpl implements GetSingerInteractor {

    private Storage mStorage;
    private Executor mExecutor;
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

    @Override
    public void run() {
        try {
            onSinger(mStorage.getSinger(mSingerId));
        } catch (Throwable e) {
            onError(e);
        }
    }
}
