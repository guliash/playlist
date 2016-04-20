package com.github.guliash.playlist.interactors;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.executors.JobExecutor;
import com.github.guliash.playlist.executors.UIExecutor;
import com.github.guliash.playlist.structures.Singer;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by gulash on 16.04.16.
 */
public class GetSingersInteractorImpl implements GetSingersInteractor {

    private Executor mExecutor;
    private Executor mPostExecutor;
    private Storage mStorage;
    private Callbacks mCallbacks;

    @Inject
    public GetSingersInteractorImpl(Storage storage, JobExecutor executor, UIExecutor postExecutor) {
        mExecutor = executor;
        mPostExecutor = postExecutor;
        mStorage = storage;
    }

    @Override
    public void execute(Callbacks callbacks) {
        mCallbacks = callbacks;
        mExecutor.execute(this);
    }

    private void onSingers(final List<Singer> singers) {
        mPostExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCallbacks.onSingers(singers);
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
            onSingers(mStorage.getSingers());
        } catch (Throwable e) {
            onError(e);
        }
    }
}
