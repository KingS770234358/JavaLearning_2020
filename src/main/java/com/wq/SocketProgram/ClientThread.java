package com.wq.SocketProgram;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread {
    public static void main(String[] args) throws IOException {
        Thread t1 = new Thread(()->{
            Socket s = null;
            try {
                s = new Socket("localhost", 8888);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("建立了与服务器的连接");

            OutputStream os = null;
            try {
                os = s.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String str = "";
            while(str.length()<1000){
                str+="夏天来了，天真热";
            }
            try {
                os.write(str.getBytes());
                os.flush();
                os.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        Thread t2 = new Thread(()->{
            Socket s = null;
            try {
                s = new Socket("localhost", 8888);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("建立了与服务器的连接");

            OutputStream os = null;
            try {
                os = s.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String str = "";
            while(str.length()<1000){
                str+="夏天来了，天真热";
            }
            try {
                os.write(str.getBytes());
                os.flush();
                os.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        t1.start();
        t2.start();
    }
}
