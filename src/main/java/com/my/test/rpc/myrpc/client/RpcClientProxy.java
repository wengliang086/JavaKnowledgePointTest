package com.my.test.rpc.myrpc.client;

import java.lang.reflect.Proxy;

public class RpcClientProxy {

    public <T> T clientProxy(Class<T> interfaceClass, String host, int port) {
        ClassLoader classLoader = interfaceClass.getClassLoader();
        RemoteInvocationHandler invocationHandler = new RemoteInvocationHandler(host, port);
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{interfaceClass}, invocationHandler);
    }
}
