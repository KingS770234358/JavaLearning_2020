package com.wq.mianshiceshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class xqpf02 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] inputs = br.readLine().split(",");
        String stardard = inputs[0];
        String res = "<";
        for(int i=0;i<stardard.length(); i++){
            String suffix = stardard.substring(stardard.length()-i, stardard.length());
            boolean flag=true;
            for(int j=1;j<inputs.length;j++) {
                if (inputs[j].endsWith(suffix)) {
                    continue;
                } else {
                    flag = false;
                    break;
                }
            }
            if(flag){
                res = suffix;
            }
            else{
                break;
            }
        }
        if(res.equals("<")){
            System.out.println("NULL");
        }else{
            System.out.println(res);
        }
    }
}
