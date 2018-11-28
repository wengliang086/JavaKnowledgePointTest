package com.my.test.thread;

public class ThreadExceptionTest {

    public static void main(String[] args) {
        System.out.println("Main Thread 1");
        new Thread(new ChildThread()).start();
        System.out.println("Main Thread 2");
    }

    static class ChildThread implements Runnable {

        private ChildThreadExceptionHandler handler = new ChildThreadExceptionHandler();

        @Override
        public void run() {
            // 设置所有线程的默认异常处理器
//            Thread.setDefaultUncaughtExceptionHandler(handler);

            // 只设置当前线程异常处理器
            Thread.currentThread().setUncaughtExceptionHandler(handler);

            System.out.println("do something 1");
            exceptinMethod();
            System.out.println("do something 2");
        }

        void exceptinMethod() {
            throw new RuntimeException("模拟异常的发生");
        }
    }

    static class ChildThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(String.format("My Exception handler in thread %s, %s", t.getName(), e));
            e.printStackTrace();
        }
    }
}
