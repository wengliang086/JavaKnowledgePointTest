package com.my.test.rpc.rmi.client;

import com.my.test.rpc.rmi.IHelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyRmiClient {

    public static void main(String[] args) {
        try {
            IHelloService helloSerice = (IHelloService) Naming.lookup("rmi://127.0.0.1/Hello");
            System.out.println(helloSerice.sayHello("Jack"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
