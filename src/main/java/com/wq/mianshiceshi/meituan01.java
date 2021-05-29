package com.wq.mianshiceshi;

import java.io.*;
import java.util.*;

public class meituan01 {
    public static void main(String[] args) throws IOException {

        // Scanner sc = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        int n = Integer.valueOf(a);
        // System.out.println(n);
        String[] numStr = br.readLine().split(" ");
        List<Integer> nums = new ArrayList<>();
        for(String num: numStr){
            nums.add(Integer.valueOf(num));
        }
        nums.sort((num1, num2)-> num1 - num2);
        int res = 0;
        for (int i=0; i<nums.size(); i++){
            // System.out.println(num);
            res += Math.abs(nums.get(i) - i - 1);
        }
        System.out.println(res);
    }
}
