package com.wq.leetcodeDK;

import com.wq.mianshiceshi.S;

import java.util.Stack;

public class khpp_678 {
    public static class BracketWithIndex{
        public Character c;
        public int index;
        public BracketWithIndex(Character c, int index){
            this.c = c;
            this.index = index;
        }
    }
    public static void main(String[] args) {
        String s = "(())((())()()(*)(*()(())())())()()((()())((()))(*";
        Stack<BracketWithIndex> cs = new Stack<>();
        Stack<BracketWithIndex> xings = new Stack<>();


        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                cs.push(new BracketWithIndex('(', i));
            }else if(s.charAt(i)=='*'){
                xings.push(new BracketWithIndex('*',i));
            }else {
                if(!cs.isEmpty()&&cs.peek().c=='('){
                    cs.pop();
                }else if(cs.isEmpty()&&!xings.isEmpty()){
                    xings.pop();
                }else if(cs.isEmpty()&&xings.isEmpty()){
                    System.out.println(false);
                    return ;
                }
            }
        }
        while(!cs.isEmpty()&&!xings.isEmpty()){
            if(xings.peek().index>cs.peek().index){
                cs.pop();
                xings.pop();
            }else{
                break;
            }
        }
        System.out.println(cs.isEmpty());
    }
}
