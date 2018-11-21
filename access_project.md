## 自定义机制
1. 为了快速定位问题二引入的 **requestId**
    
    generateRequestId=groupId+atomicLong.getAndIncrement()+getInternalIpLastPart()
    
2. 通过java的SPI机制，扩展dubbo的filter，传递 requestId
3. filter实现自定义异常和返回值的传递
4. 自定义HandlerMapping中的urlPathHelper，实现URL动态，缩小苹果审核黑名单影响
5. 自定义PermissionAnnotation，控制接口访问权限，在自定义的HandlerAdapter中做统一验证
6. 自定义ChannelInfo的Annotation，控制哪些参数要暴露给Android客户端，还可以扩展注解功能，控制每个参数的合法性，在web页面提交的时候做参数合法性验证。
7. 通过Java的内省Introspector，动态把web中input标签的值设置到对应的bean对象上

## 可能存在的问题
1. 线程池的使用方式，跟直接new cup个线程没有区别
2. sql缓存的使用对源码的侵入性太强，可以通过实现Mybatis的Cache接口来实现
3. 定时刷新数据库缓存的线程，为了能够在集群中单机执行，使用了配置固定IP的策略，
这样会存在单点问题（当然我们的业务，这个并不重要），可以使用分布式锁优化，
比如zookeeper的 临时类型的znode节点来实现

## 指标
1. nginx 每日大约 四千万 访问
2. DAU 约一百万
3. 

## 需要继续深入了解的地方
1. Druid SQL统计的实现
2. Nginx 统计实现
3. 各种统计的实现逻辑。。。