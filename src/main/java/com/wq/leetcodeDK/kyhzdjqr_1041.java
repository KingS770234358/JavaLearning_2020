package com.wq.leetcodeDK;

public class kyhzdjqr_1041 {
    public static void main(String[] args) {
        int [] dir = {0, 1, 0, -1, 0};
        int x = 0;
        int y = 0;
        int d = 0;
        // String instruction = "GGLLGG";
        String instructions = "GL";
//        System.out.println(-1 % 4);
        for(int i=0;i<instructions.length();i++){
            if(instructions.charAt(i)=='G'){
                x += dir[d];
                y += dir[d+1];
            }else if(instructions.charAt(i)=='L'){
                d--;
                if(d<0){
                    d += 4;
                }
                //System.out.println(d);
            }else{
                d++;
                if(d>3){
                    d -= 4;
                }
                //System.out.println(d);
            }
        }
        System.out.println(x==0&&y==0||d%4!=0);
    }
}
