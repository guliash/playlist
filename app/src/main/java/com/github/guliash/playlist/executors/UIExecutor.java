package com.github.guliash.playlist.executors;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

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
