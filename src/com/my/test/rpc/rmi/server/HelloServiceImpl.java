package com.my.test.rpc.rmi.server;

import com.my.test.rpc.rmi.IHelloSerice;

import java.io.Serializable;
import java.rmi.RemoteException;

public class HelloServiceImpl implements IHelloSerice, Serializable {
    @Override
    public String sayHello(String msg) throws RemoteException {
        return "Hello " + msg;
    }
}
