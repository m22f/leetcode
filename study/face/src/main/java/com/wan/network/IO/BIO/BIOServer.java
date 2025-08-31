package com.wan.network.IO.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待连接");
            Socket socket = serverSocket.accept();
            System.out.println("连接成功");
            new Thread(()->{
                try {
                    handler(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void handler(Socket socket) throws IOException {
        System.out.println("线程id=" + Thread.currentThread().threadId());
        byte[] bytes = new byte[1024];
        System.out.println("从客户端得到消息：");
        socket.getInputStream().read(bytes);
        System.out.println(new String(bytes));

    }
}
