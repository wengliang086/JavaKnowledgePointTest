## Java重要知识点备忘

* classLoader
    * 在JVM中表示两个class对象是否为同一个类对象存在两个必要条件
    
        1. 类的完整类名必须一致，包括包名。
        2. 加载这个类的ClassLoader(指ClassLoader实例对象)必须相同。
* concurrent 并发包

    * 线程池
         1. newFixedThreadPool和newSingleThreadExecutor:
             * 主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
         2. newCachedThreadPool和newScheduledThreadPool:
             * 主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
    * Lock 
        - 公平锁模式下，的tryLock也是非公平的
* container 容器
    * 非并发容器
    * 并发容器
    
        ![collections](imgs/collections.png)
        * CopyOnWrite
* objectmemory 对象内存大小计算，运行程序，dump内存
* RPC
    1. rmi 方式实现远程方法调用
    2. ObjectInputStream 和 ObjectOutputStream 方式实现远程调用
* web HTTP协议理解，用Socket模拟TomcatServer，可以接收并返回HTTP请求
* thread
    * 子线程异常处理（父线程不能捕获子线程抛出的异常）
        1. 在子线程中 try ... catch
        2. 设置 UncaughtExceptionHandler
        3. 通过Future的get方法捕获异常
* future
