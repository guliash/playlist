package com.github.guliash.playlist.executors;

import android.os.Handler;
import android.os.Looper;

/**
 * Executor which runs on the main thread
 */
public class UIExecutor implements PostExecutor {

    private Handler mHandler;

    public UIExecutor() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Executes the runnable on the main thread
     * @param runnable the runnable
     */
    @Override
    public void execute(Runnable runnable) {
        mHandler.post(runnable);
    }
}
