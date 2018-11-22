package com.my.test.rpc.myrpc.server;

import com.my.test.rpc.myrpc.IHelloService;

public class ServerDemo {

    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();

        RpcServer rpcServer = new RpcServer();
        rpcServer.publish(helloService, 8888);
    }
}
