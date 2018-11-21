package com.my.test.concurrent;

import java.util.concurrent.*;

/**
 * 参考博客：https://www.cnblogs.com/superfj/p/7544971.html
 * https://www.cnblogs.com/dolphin0520/p/3932921.html
 * <p>
 * 一般需要根据任务的类型来配置线程池大小：(NCPU = CPU的数量)
 * 　　如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1
 * 　　如果是IO密集型任务，参考值可以设置为2*NCPU
 */

/**
 * 线程池作用就是限制系统中执行线程的数量。
 * 减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。
 * <p>
 * 任务缓存队列
 * workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：
 * <p>
 * 1）有界任务队列ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；
 * 2）无界任务队列LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；
 * 3）直接提交队列synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
 */
public class ExecutorTest {

    /**
     * 以下阿里编码规范里面说的一段话：
     * <p>
     * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors各个方法的弊端：
     * 1）newFixedThreadPool和newSingleThreadExecutor:
     *   主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
     * 2）newCachedThreadPool和newScheduledThreadPool:
     *   主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
     */
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


    /**
     * 拒绝策略
     * AbortPolicy:丢弃任务并抛出RejectedExecutionException
     * CallerRunsPolicy：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降。
     * DiscardOldestPolicy：丢弃队列中最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务。
     * DiscardPolicy：丢弃任务，不做任何处理。
     */
    private static RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
    private static ExecutorService executorService = new ThreadPoolExecutor(1, 4, 1, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            /*
            自定义的线程工厂可以做很多事情，比如可以跟踪线程池在何时创建了多少线程，也可以自定义线程名称和优先级。
            如果将新建的线程都设置成守护线程，当主线程退出后，将会强制销毁线程池。
             */
            Thread thread = new Thread(r);
            thread.setName("threadName-1");
            return thread;
        }
    });

    public static void main(String[] args) {
        System.out.println("availableProcessors=" + Runtime.getRuntime().availableProcessors());
//        testSingleThreadPool();
//        testScheduledThreadPool();
//        testFixedThreadPool();
        testCachedThreadPool();
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

    private static void testCachedThreadPool() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI + "--" + System.currentTimeMillis() / 1000);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
