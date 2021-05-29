package com.wq.leetcodeDK;

import java.util.ArrayList;
import java.util.List;

public class wmddzjqd_1626 {
    public static class Member{
        public int score;
        public int age;
        public boolean flag;
        public Member(int score, int age){
            this.score = score;
            this.age = age;
            this.flag = true;
        }
    }
    public static void main(String[] args) {
        int [] scores = {1,3,5,10,15};
        int [] ages = {1,2,3,4,5};
        List<Member> mList = new ArrayList<>();
        for(int i=0;i<scores.length;i++){
            mList.add(new Member(scores[i], ages[i]));
        }
        mList.sort((a,b)-> a.age - b.age );
        int curScore = mList.get(0).score;
        for(int i=1;i<scores.length;i++){
            // mList.add(new Member(scores[i], ages[i]));
            if(mList.get(i).age==mList.get(i-1).age){
                curScore+=mList.get(i).score;
            }
        }
    }
}
