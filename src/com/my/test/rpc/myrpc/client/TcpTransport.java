package com.my.test.rpc.myrpc.client;

import com.my.test.rpc.myrpc.RpcRequest;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpTransport {

    private String host;
    private int port;

    public TcpTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(RpcRequest request) {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object result = objectInputStream.readObject();
            objectInputStream.close();
            objectOutputStream.close();
            outputStream.close();
            inputStream.close();
            return result;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
