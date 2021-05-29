package com.wq.mianshiceshi;

public class bldh {
    String str = new String("dahua");
    char [] ch = {'a', 'b', 'c'};
    public static void main(String[] args) {
        int num = -120;
        int a = num/100;
        int b = num/10 % 10;
        int c = num%10;
        System.out.println( c*100 + b*10 + a );
    }
}
