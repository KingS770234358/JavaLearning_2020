package com.wq.leetcodeDK;

import java.util.ArrayList;
import java.util.List;

public class sppj_1024 {
    public static class Extent{
        public int start;
        public int end;
        public Extent(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
//        int [][] clips = {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
//        int T = 10;
//        int [][] clips = {{0,1},{1,2}};
//        int T = 5;
//        int [][] clips = {{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
//        int T = 9;
        int [][] clips = {{0,4},{2,8}};
        int T = 5;
        List<Extent> cList = new ArrayList<>();

        for(int i=0;i<clips.length;i++){
            Extent e = new Extent(clips[i][0], clips[i][1]);
            cList.add(e);
        }
        cList.sort((a, b)->a.start-b.start);
        int curStart = 0;
        int curEnd = -1;
        boolean over = false;
        int need = 0;
        for(int i=0; i<cList.size(); ){
           // System.out.println(e.start + " " + e.end);
            boolean b = false;
            // int tmpEnd = curEnd;
            // System.out.println("=====================");
            while(i<cList.size()&&cList.get(i).start<=curStart){
                b = true;
                if(cList.get(i).end>=curEnd){
                    curEnd = cList.get(i).end;
                    //System.out.println("取"+cList.get(i).start+" "+cList.get(i).end);
                }
                i++;
            }
            if(b==false){
                // System.out.println(-1);
                break;
            }
            curStart = curEnd;
            need++;
            if(curEnd>=T){
                System.out.println(need);
                break;
            }
        }
        if(curEnd<T){
            System.out.println(-1);
        }
    }
}
