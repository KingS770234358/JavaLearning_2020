package com.wq.mianshiceshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class xqpf01 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] numStr = br. readLine().split(" ");
        int posCount = 0;
        int negCount = 0;
        for(String nStr: numStr){
            double a = Double.valueOf(nStr);
            if(a>0){
                posCount++;
            }else if(a<0){
                negCount++;
            }
        }

        System.out.println(posCount+","+negCount);

    }
}
