package com.wq.mianshiceshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class xqpf03 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        String[] inputs = br.readLine().split("] \\[");
        inputs[0] = inputs[0].substring(1, inputs[0].length());
        inputs[1] = inputs[1].substring(0, inputs[1].length()-1);
        String [] firstRow = inputs[0].split(" ");
        String [] secondRow = inputs[1].split(" ");

        List<String> res = new ArrayList<>();
        List<String> left = new ArrayList<>();
        int i=0;
        for(; i<Math.min(firstRow.length, secondRow.length);i++){
            res.add(firstRow[i]);
            res.add(secondRow[i]);

        }
        if(i==firstRow.length){
            while(i<secondRow.length){
                left.add(secondRow[i]);
                i++;
            }
        }
        if(i==secondRow.length){
            while(i<firstRow.length){
                left.add(firstRow[i]);
                i++;
            }
        }
        for(int j=0;j<res.size();j++){
            if(j==0){
                System.out.print(res.get(j));
            }else {
                System.out.print("," + res.get(j));
            }
        }
        System.out.println("");
        for(int j=0;j<left.size();j++){
            if(j==0){
                System.out.print(left.get(j));
            }else {
                System.out.print("," + left.get(j));
            }
        }
        System.out.println("");
    }
}
