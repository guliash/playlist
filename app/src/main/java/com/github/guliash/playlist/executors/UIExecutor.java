package com.github.guliash.playlist.executors;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by gulash on 15.04.16.
 */
public class UIExecutor implements Executor {

    private Handler mHandler;

    public UIExecutor() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        mHandler.post(runnable);
    }
}
