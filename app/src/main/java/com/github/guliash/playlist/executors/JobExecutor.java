package com.github.guliash.playlist.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JobExecutor implements Executor {

    private ThreadPoolExecutor mPoolExecutor;

    public JobExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit,
                       BlockingQueue<Runnable> workQueue) {

        mPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit,
                workQueue, new JobThreadFactory());
    }

    @Override
    public void execute(Runnable runnable) {
        mPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {

        private static final String THREAD_NAME = "android_";
        private static int counter = 1;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, THREAD_NAME + (counter++));
        }
    }

}
