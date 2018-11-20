package com.my.test.rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHelloSerice extends Remote {
    String sayHello(String msg) throws RemoteException;
}
