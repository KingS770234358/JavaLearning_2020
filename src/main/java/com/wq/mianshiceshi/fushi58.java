package com.wq.mianshiceshi;

import java.io.*;

public class fushi58 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int count7 = 0;
        int count8 = 0;
        int count9 = 0;
        int count10 = 0;

        for(int i=1;i<=6;i++){
            for(int j=i;j<=6;j++){
                for(int k=j;k<=6;k++){
                    if(i+j+k==7)count7++;
                    else if(i+j+k==8)count8++;
                    else if(i+j+k==9)count9++;
                    else if(i+j+k==10)count8++;
                }
            }
        }
        System.out.println(count7 +","+count8+","+count9+","+count10);
    }
}
