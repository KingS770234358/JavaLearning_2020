package com.wq.leetcodeDK;

import java.util.Stack;

public class mst1626jsq {
    public static void main(String[] args) {
        //String exp = "3+2*2";
        String s = "3+2*2";
        /*
        // 不能进行预处理。。。。
        String fexp = "";
        for(int i=0; i<s.length(); i++){
            Character c = s.charAt(i);
            if(!c.equals(' '))fexp+=c;
        }
        */
        Stack<Integer> ns = new Stack<>();
        char[] cs = s.toCharArray();
        int i = 0;
        while(i<cs.length){
            if(cs[i] == ' ') {i++;continue;}
            char tmp = cs[i];
            if(tmp=='+'||tmp=='-'||tmp=='*'||tmp=='/'){
                i++;
                while(i < cs.length && cs[i] == ' ') i++;
            }
            int curNum = 0;
            while(i < cs.length && Character.isDigit(cs[i])){
                curNum = curNum * 10 + cs[i] - '0';
                i++;
            }
            switch (tmp){
                case '-':
                    curNum = -curNum;
                    break;
                case '*':
                    curNum = ns.pop() * curNum;
                    break;
                case '/':
                    curNum = ns.pop() / curNum;
                    break;
                default:
                    break;
            }
            ns.push(curNum);
        }
        int res = 0;
        while(!ns.isEmpty()){
            res += ns.pop();
        }
        System.out.println(res);
    }
}
