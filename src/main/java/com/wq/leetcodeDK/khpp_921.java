package com.wq.leetcodeDK;

import java.util.Stack;

public class khpp_921 {
    public static void main(String[] args) {
        String S = "()))((";
        Stack<Character> cs = new Stack<>();
        int count = 0;
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)=='('){
                cs.push(S.charAt(i));
            }else if(S.charAt(i)==')'){
                if(!cs.empty()&&cs.peek()=='('){
                    cs.pop();
                }else if(cs.empty()){
                    count++;
                }
            }
        }
        while(!cs.empty()){
            count++;
            cs.pop();
        }
        System.out.println(count);
    }
}
