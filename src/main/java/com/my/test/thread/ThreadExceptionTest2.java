package com.my.test.thread;

import java.util.concurrent.*;

public class ThreadExceptionTest2 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new ChildThread());
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    static class ChildThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("do something 1");
            exceptinMethod();
            System.out.println("do something 2");
            return "success result!";
        }

        void exceptinMethod() {
            throw new RuntimeException("模拟异常的发生");
        }
    }
}
