package com.wan.network.IO.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(9000));
        // 选择器
        Selector selector = Selector.open();
        
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            System.out.println("等待");
            // 轮询监听channel里的key，select阻塞，accept阻塞
            int select = selector.select();

            System.out.println("有事件发生");
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                handle(key);
            }
        }
    }

    private static void handle(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            System.out.println("与客户端建立连接");
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ);
        } else if(key.isReadable()){
            System.out.println("读取客户端数据");
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel sc = ssc.accept();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len = sc.read(buffer);
            if(len != -1){
                System.out.println("：" + new String(buffer.array(), 0, len));
            }
            ByteBuffer bufferToWrite = ByteBuffer.wrap("helloClient".getBytes());
            sc.write(bufferToWrite);
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } else if(key.isWritable()){
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            System.out.println("写事件");
            key.interestOps(SelectionKey.OP_READ);
        }

    }
}
