package com.wq.mianshiceshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sale {
    private static BufferedReader bs = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        String [] parameters = bs.readLine().split(" ");
        // System.out.println(parameters[0]);
        int tieNum = Integer.valueOf(parameters[0]);
        int pantNum = Integer.valueOf(parameters[1]);
        int hatNum = Integer.valueOf(parameters[2]);
        int shirtNum = Integer.valueOf(parameters[3]);
        int tieAndshirtPro = Integer.valueOf(parameters[4]);
        int pantAndshirtPro = Integer.valueOf(parameters[5]);
        int hatAndshirtPro = Integer.valueOf(parameters[6]);
        //System.out.println(tieNum + " " + pantNum + " " + hatNum + " " + shirtNum);
        int firstNum;
        int secondNum;
        int thirdNum;

        int secondPro;
        int thirdPro;
        int firstPro = Math.max(tieAndshirtPro, Math.max(pantAndshirtPro, hatAndshirtPro));
        // 确定价格排序 记录每次要卖的物品
        if(tieAndshirtPro==firstPro){
            firstNum=tieNum;
            if(pantAndshirtPro>hatAndshirtPro){
                secondNum = pantNum;
                secondPro = pantAndshirtPro;
                thirdNum = hatNum;
                thirdPro = hatAndshirtPro;
            }else{
                secondNum = hatNum;
                secondPro = hatAndshirtPro;
                thirdNum = pantNum;
                thirdPro = pantAndshirtPro;
            }
        }
        else if(pantAndshirtPro==firstPro){
            firstNum=pantNum;
            if(tieAndshirtPro>hatAndshirtPro){
                secondNum = tieNum;
                secondPro = tieAndshirtPro;
                thirdNum = hatNum;
                thirdPro = hatAndshirtPro;
            }else{
                secondNum = hatNum;
                secondPro = hatAndshirtPro;
                thirdNum = tieNum;
                thirdPro = tieAndshirtPro;
            }
        }
        else{
            firstNum = hatNum;
            if(tieAndshirtPro>pantAndshirtPro){
                secondNum = tieNum;
                secondPro = tieAndshirtPro;
                thirdNum = pantNum;
                thirdPro = pantAndshirtPro;
            }else{
                secondNum = pantNum;
                secondPro = pantAndshirtPro;
                thirdNum = tieNum;
                thirdPro = tieAndshirtPro;
            }
        }
        // System.out.println(firstPro+" "+firstNum);
        int resPro=0;
        resPro += Math.max(Math.min(shirtNum, firstNum), 0) * firstPro;
        System.out.println(resPro);
        shirtNum-= firstNum;
        resPro += Math.max(Math.min(shirtNum, secondNum), 0) * secondPro;
        shirtNum-= secondNum;
        System.out.println(resPro);
        resPro += Math.max(Math.min(shirtNum, thirdNum), 0) * thirdPro;
        shirtNum-= secondNum;
        System.out.println(resPro);


    }
}
