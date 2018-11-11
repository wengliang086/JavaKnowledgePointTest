package com.my.test.concurrent;

import java.util.concurrent.*;

/**
 * 线程池作用就是限制系统中执行线程的数量。
 * 减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。
 */
public class ExecutorTest {

    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static ExecutorService executorService = new ThreadPoolExecutor(1, 4, 1, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("threadName-1");
            return thread;
        }
    });

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
//        testSingleThreadPool();
//        testScheduledThreadPool();
        testFixedThreadPool();
    }

    private static void testFixedThreadPool() {
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        fixedThreadPool.shutdown();
    }

    private static void testScheduledThreadPool() {
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, and execute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    private static void testSingleThreadPool() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
