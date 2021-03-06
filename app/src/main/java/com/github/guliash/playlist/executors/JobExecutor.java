package com.github.guliash.playlist.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Simple executor based on {@link ThreadPoolExecutor}
 */
public class JobExecutor implements ThreadExecutor {

    private ThreadPoolExecutor mPoolExecutor;

    /**
     * See {@link ThreadPoolExecutor#ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue)}
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param timeUnit
     * @param workQueue
     */
    public JobExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit,
                       BlockingQueue<Runnable> workQueue) {
        mPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit,
                workQueue, new JobThreadFactory());
    }

    /**
     * Executes the runnable in the executor
     * @param runnable the runnable
     */
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
