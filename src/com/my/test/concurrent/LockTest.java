package com.my.test.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest implements Runnable {

    private ReentrantLock lock = new ReentrantLock();
    private int sum = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            lock.lock();
            try {
                sum++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        Thread t1 = new Thread(lockTest);
        Thread t2 = new Thread(lockTest);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(lockTest.sum);
    }
}
