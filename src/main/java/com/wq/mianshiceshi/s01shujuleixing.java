package com.wq.mianshiceshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class s01shujuleixing {
    public static <T extends Comparable<T>> T Compare(T a, T b) //T指任意类型  继承了Comparable接口，查看JDK文档法相有解释
    {
        if (a.compareTo(b)<0)return a;
        else return b;
    }

    public static void main(String[] args) throws IOException {
        // Integer x = 2;     // 装箱 调用了 Integer.valueOf(2)
        // int y = x;         // 拆箱 调用了 X.intValue()
        Integer x = new Integer(123);
        Integer y = new Integer(123);
        System.out.println(x == y);    // false
        Integer z = Integer.valueOf(123); // 常量池
        Integer k = Integer.valueOf(123); // 常量池
        System.out.println(z == k);   // true

        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2);           // false
        String s3 = s1.intern();
        String s4 = s1.intern();
        System.out.println(s3 == s4);           // true

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String str = br.readLine();
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        for(int i=0;i<n;i++){
//            int a = sc.nextInt();
//            int b = sc.nextInt();
//            for(int j=0;j<m;j++){
//                System.out.println(a*b+j);
//            }
//        }

        int a = 1,b=2;
        double ad = 1.1f,bd = 0.3f;
        System.out.println(Compare(a,b));
        System.out.println(Compare(ad,bd));
    }
}
