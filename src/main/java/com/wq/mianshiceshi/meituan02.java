package com.wq.mianshiceshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class meituan02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int n = Integer.valueOf(inputs[0]);
        int x = Integer.valueOf(inputs[1]);
        int y = Integer.valueOf(inputs[2]);

        String[] numStr = br.readLine().split(" ");
        List<Integer> nums = new ArrayList<>();
        for(String num: numStr){
            nums.add(Integer.valueOf(num));
        }
        nums.sort((num1, num2)-> num1 - num2);

        boolean flag = false;
        int res = 0;
        for(int i = Math.max(x, n - y) ; i< nums.size(); i++){

            if(nums.get(i) == nums.get(i-1))continue;
            // System.out.println(i);
            if(i >=x && i <= y && n - i >=x && n - i <=y){
                flag = true;
                res = nums.get(i - 1);
                break;
            }
        }
        if(flag==true){
            System.out.println(res);
        }else{
            System.out.println("-1");
        }
    }
}
