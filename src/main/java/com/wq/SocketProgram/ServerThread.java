package com.wq.SocketProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket s) {
        this.socket = s;
        System.out.println(socket);
    }

    public void run() {
        System.out.println(Thread.currentThread().getId());
        try {
            //构造socket的输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //根据socket构造输出流
            PrintWriter out = new PrintWriter(socket.getOutputStream());


            // Mutil User but can't parallel
            while (true) {
                //读取客户端输入流中内容
                String str = in.readLine();
                System.out.println(str);
                //向客户端输出
                out.println("has receive....");
                out.flush();
                if(str.equals("end"))
                    break;
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
        } finally {
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8888);

        System.out.println("服务器开始监听...");


        while(true) {
            //每一个客户端连接（server.accept()）分配一个线程来处理
            Socket s = server.accept();
            System.out.println("收到一个连接请求" + s);

            ServerThread mc = new ServerThread(s);
            mc.start();

            //Map<String, String>  te = new HashMap<>
        }
    }
}
