package com.my.test.rpc.rmi.server;

import com.my.test.rpc.rmi.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    protected HelloServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String msg) throws RemoteException {
        return "RMI: Hello " + msg;
    }
}
