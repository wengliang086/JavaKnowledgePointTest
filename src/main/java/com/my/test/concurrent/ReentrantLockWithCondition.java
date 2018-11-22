package com.my.test.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://www.cnblogs.com/sheeva/p/6484224.html
 * 公平锁模式下，的tryLock也是非公平的
 */
public class ReentrantLockWithCondition implements Runnable {

    private ReentrantLock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("子线程 HoldCount=" + lock.getHoldCount());
            System.err.println(Thread.currentThread().getName() + "-线程开始等待...");
            // 当调用了condition对象的await()方法后，线程将释放持有的lock
            condition.await();
            // 当有其他线程把condition修改为满足，即调用condition.signal()方法，线程会重新等待获取锁
            System.out.println("子线程 HoldCount=" + lock.getHoldCount());
            System.err.println(Thread.currentThread().getName() + "-线程继续进行了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockWithCondition obj = new ReentrantLockWithCondition();
        new Thread(obj, "线程name").start();
        Thread.sleep(1000);
        System.err.println("过了1秒后...");
        System.out.println("主线程 HoldCount=" + obj.lock.getHoldCount());
        obj.lock.lock();
        try {
            System.out.println("主线程 HoldCount=" + obj.lock.getHoldCount());
            obj.condition.signal();// 调用该方法前需要获取到创建该对象的锁否则会产生 IllegalMonitorStateException
        } finally {
            obj.lock.unlock();
        }
    }
}
