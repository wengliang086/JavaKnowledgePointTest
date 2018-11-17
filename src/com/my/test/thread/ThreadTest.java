package com.my.test.thread;

public class ThreadTest implements Runnable {

    private String name;

    public ThreadTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadTest("t1"));
        Thread t2 = new Thread(new ThreadTest("t2"));
        Thread t3 = new Thread(new ThreadTest("t3"));
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

        System.out.println(3d * 0.1d == 0.3d);
    }
}
