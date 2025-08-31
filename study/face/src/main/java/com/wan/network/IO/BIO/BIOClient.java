package com.wan.network.IO.BIO;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class BIOClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 9000);
        System.out.println("向服务端发送数据");
        socket.getOutputStream().write("helloserver".getBytes());
        socket.getOutputStream().flush();
        System.out.println("发送数据成功");
        byte[] bytes = new byte[1024];
        socket.getInputStream().read(bytes);
        System.out.println("接收到服务端消息：" + new String(bytes));
        socket.close();
    }
}
