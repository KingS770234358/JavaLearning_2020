package com.wq.SocketProgram;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws IOException {


        Socket s = new Socket("localhost", 8888);
        System.out.println("建立了与服务器的连接");

        //由Socket对象得到输出流，并构造PrintWriter对象
        Scanner sc = new Scanner(System.in);

        PrintWriter out = new PrintWriter(s.getOutputStream());

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        while(true){
            String info = sc.nextLine();
            // System.out.println(info);
            out.println(info);
            out.flush();
            System.out.println("服务器说："+in.readLine());
            if(info.equals("bye")){
                in.close();
                out.close();
                s.close();
                break;
            }
        }

    }
}
