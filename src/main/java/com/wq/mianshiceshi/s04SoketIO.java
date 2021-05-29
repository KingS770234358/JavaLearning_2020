package com.wq.mianshiceshi;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class s04SoketIO {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssChannel = ServerSocketChannel.open(); // 这个通道是主要通道 负责监听是否有用户请求连接
        ssChannel.configureBlocking(false); // 通道设置为非阻塞
        Selector selector = Selector.open(); // 创建一个多路复用器
        ssChannel.register(selector, SelectionKey.OP_ACCEPT); // 将通道注册到多路复用器上
        // 创建套接字
        ServerSocket serverSocket = ssChannel.socket(); // 通过通道获得套接字
        // 创建IP端口
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        // 绑定IP端口
        serverSocket.bind(address);

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) { // 这里是新收到一个连接请求（这个key是服务器通道的key）
                    ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel(); //
                    // 服务器会为每个新连接创建一个 SocketChannel
                    SocketChannel sChannel = ssChannel1.accept(); // 服务器监听通道accept创建 客户端连接通道 用于读取客户端数据
                    sChannel.configureBlocking(false);
                    // 将这个通道也注册到 多路复用器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) { // 已经建立的连接请求中收到了可读的数据（这个key是客户端的通道）
                    SocketChannel sChannel = (SocketChannel) key.channel();
                    System.out.println(readDataFromSocketChannel(sChannel));
                    sChannel.close();
                }
                keyIterator.remove();
            }
        }
    }

    private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder data = new StringBuilder();

        while (true) {

            buffer.clear();
            int n = sChannel.read(buffer); // 将客户端 通道的数据读入缓存
            if (n == -1) {
                break;
            }
            buffer.flip(); // 反转 确定缓冲区中 数据的起始位置
            int limit = buffer.limit();
            char[] dst = new char[limit];
            for (int i = 0; i < limit; i++) {
                dst[i] = (char) buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }
}


