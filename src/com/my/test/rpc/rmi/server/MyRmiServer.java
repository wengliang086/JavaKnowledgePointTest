package com.my.test.rpc.rmi.server;

import com.my.test.rpc.rmi.IHelloService;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MyRmiServer {

    public static void main(String[] args) {
        try {
            IHelloService helloService = new HelloServiceImpl();
            // 注册中心，这个概念在RMI中很早就有了
            LocateRegistry.createRegistry(1099);
            // 将创建的对象发布到一个地址上
            Naming.bind("rmi://127.0.0.1/Hello", helloService);
            System.out.println("服务注册成功！");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
