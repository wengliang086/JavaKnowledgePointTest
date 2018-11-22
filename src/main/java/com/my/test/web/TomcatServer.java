package com.my.test.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.StringTokenizer;

/**
 * 模拟Tomcat处理Http请求
 */
public class TomcatServer {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            ServletHandler servletHandler = new ServletHandler(serverSocket);
            servletHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ServletHandler extends Thread {

        private ServerSocket serverSocket;

        ServletHandler(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // ServerSocket阻塞等待客户端请求数据
                    Socket client = serverSocket.accept();
                    if (client != null) {
                        System.out.println("接收到一个客户端请求");

                        //根据客户端的Socket对象获取输入流对象。
                        //封装字节流到字符流
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

                        // GET /test.jpg /HTTP1.1
                        //http请求由三部分组成，分别是：请求行、消息报头、请求正文。
                        //这里取的第一行数据就是请求行。http协议详解可以参考http://www.cnblogs.com/li0803/archive/2008/11/03/1324746.html说的很详细
                        String line = bufferedReader.readLine();
                        System.out.println("line: " + line);

                        //拆分http请求路径，取http需要请求的资源完整路径
                        String resource = line.substring(line.indexOf('/'), line.lastIndexOf('/') - 5);
                        System.out.println("The resource you request is: " + resource);
                        resource = URLDecoder.decode(resource, "utf-8");
                        //获取到这次请求的方法类型，比如get或post请求
                        String method = new StringTokenizer(line).nextElement().toString();
                        System.out.println("The request method you send is: " + method);

                        //继续循环读取浏览器客户端发出的一行一行的数据
                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.length() == 0) {//当line等于空行的时候标志Header消息结束
                                break;
                            }
                            System.out.println("The http header is: " + line);
                        }
                        //如果是POST的请求，直接打印POST提交上来的数据
                        if ("POST".equalsIgnoreCase(method)) {
                            System.out.println("The post request body is: " + bufferedReader.readLine());
                        } else if ("GET".equalsIgnoreCase(method)) {
                            //判断是get类型的http请求处理
                            //根据http请求的资源后缀名来确定返回数据

                            //比如下载一个图片文件，我这里直接给定一个图片路径来模拟下载的情况
                            if (resource.endsWith(".bmp")) {
                                transferFileHandle("C:\\Users\\phoenix\\Desktop\\笔记/curl.bmp", client);
                            } else {
                                //直接返回一个网页数据
                                //其实就是将html的代码以字节流的形式写到IO中反馈给客户端浏览器。
                                //浏览器会根据http报文“Content-Type”来知道反馈给浏览器的数据是什么格式的，并进行什么样的处理
                                PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
                                printWriter.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
                                printWriter.println("Content-Type:text/html;charset=utf-8");
                                printWriter.println();
                                printWriter.println("<html><body>");
                                printWriter.println("<a href='www.baidu.com'>百度</a>");
                                printWriter.println("<img src='https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'></img>");
                                printWriter.println("</html></body>");
                                //writer.println("HTTP/1.0 404 Not found");// 返回应答消息,并结束应答
                                printWriter.println();// 根据 HTTP 协议, 空行将结束头信息
                                printWriter.close();
                            }
                        }
                        closeSocket(client);//请求资源处理完毕，关闭socket链接
                    }
                } catch (Exception e) {
                    System.out.println("HTTP服务器错误: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }

        private void closeSocket(Socket socket) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(socket + " 离开了HTTP服务器");
        }

        private void transferFileHandle(String path, Socket client) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                try {
                    PrintStream printStream = new PrintStream(client.getOutputStream());
                    printStream.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
                    printStream.println("Content-Type:application/binary");
                    printStream.println("Content-Length:" + file.length());// 返回内容字节数
                    printStream.println("");// 根据 HTTP 协议, 空行将结束头信息

                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] buf = new byte[fileInputStream.available()];
                    fileInputStream.read(buf);

                    printStream.write(buf);
                    printStream.close();
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
