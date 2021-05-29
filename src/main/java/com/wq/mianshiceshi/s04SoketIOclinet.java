package com.wq.mianshiceshi;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class s04SoketIOclinet {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream out = socket.getOutputStream();
        String s = "23131";
        out.write(s.getBytes());
        out.close();
    }
}
