package com.github.guliash.playlist.executors;

public class FakeThreadExecutor implements ThreadExecutor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
