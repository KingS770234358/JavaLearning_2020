package com.wq.mianshiceshi;
import java.util.*;
public class MainMT {
    public static void main(String[] args) {
        String ipv4 = "123  .456      .    789.         112";
        String res_ipv4 = "";
        int cntPoint = 0;
        for(int i=0; i<ipv4.length(); i++){
            char c = ipv4.charAt(i);
            if(c==' '){
                res_ipv4+=" ";
                while(ipv4.charAt(++i)==' ');
                i--;
            }else{
                res_ipv4+=c;
            }
        }
        System.out.println("res_ipv4"+" "+res_ipv4);
//        System.out.println("================");
//        for(int i=0;i<res_ipv4.length();i++){
//            System.out.println("当前字符:"+res_ipv4.charAt(i));
//        }
//        System.out.println("================");
        String ansStr = "";
        String curNum = "";
        for(int i=0;i<res_ipv4.length();i++){
            char c = res_ipv4.charAt(i);
//            System.out.println("当前字符:"+res_ipv4.charAt(i));
            if(c==' '){
                if(i==0||i==res_ipv4.length()-1){
                    System.out.println(false);
                }else if(Character.isDigit(res_ipv4.charAt(i-1))&&Character.isDigit(res_ipv4.charAt(i+1))){
                    System.out.println(false);
                }else if(res_ipv4.charAt(i-1)=='.'&&res_ipv4.charAt(i+1)=='.'){
                    System.out.println(false);
                }
            }else if(c=='.'){
                ansStr = ansStr + curNum;
                curNum = "";
            }else if(Character.isDigit(c)){
                curNum = curNum + c;
                if(Integer.valueOf(curNum)>255) System.out.println(false);
                if(i+1==res_ipv4.length()){
                    ansStr = ansStr + curNum;
                }
            }
        }
        System.out.println(Long.valueOf(ansStr));
    }
}
