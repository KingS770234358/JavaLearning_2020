package com.wq.mianshiceshi;

import java.util.Scanner;

public class mianshiTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        //判断区间的每一个数是否是水仙花数，i是区间的整数
        for (int num = num1 + 1; ; num++) {
            int sum = 0;
            //n是整数i的位数
            int n = fun01(num);
            for (int i = 0; i < n; i++) {
                int num3 = 0;
                num3 = (int) (num / Math.pow(10,i));
                num3 = num3 % 10;
                sum += Math.pow(num3, n);
            }
            if (sum == num) {
                System.out.println(num);
                break;
            }
        }
    }
    public static int fun01(int num) {
        int n = 0;
        while (num != 0) {
            num = num / 10;
            n++;
        }
        return n;
    }

}
