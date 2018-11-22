package com.my.test.rpc.myrpc.server;

import com.my.test.rpc.myrpc.IHelloService;

public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String msg) {
        return "RPC: Hello " + msg;
    }
}
