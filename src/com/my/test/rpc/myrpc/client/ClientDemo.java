package com.my.test.rpc.myrpc.client;

import com.my.test.rpc.myrpc.IHelloService;

public class ClientDemo {

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IHelloService helloService = rpcClientProxy.clientProxy(IHelloService.class, "localhost", 8888);
        System.out.println(helloService);
        System.out.println(helloService.getClass());
        System.out.println(helloService.sayHello("Tom"));
    }
}
