package com.my.test.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * ArrayBlockingQueue ：一个由数组支持的有界队列。
 * LinkedBlockingQueue ：一个由链接节点支持的可选有界队列。
 * PriorityBlockingQueue ：一个由优先级堆支持的无界优先级队列。
 * DelayQueue ：一个由优先级堆支持的、基于时间的调度队列。
 * SynchronousQueue ：一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制。
 */
public class QueueTest {

    public static void main(String[] args) {

    }

    private static void typeOfQueue() {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(1000);
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(500);
    }
}
