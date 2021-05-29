package com.wq.mianshiceshi;

import java.util.ArrayList;
import java.util.Scanner;

public class KMP {
    public static class Toy{
        private int appearance;
        private int price;
        public void setAppearance(int appearance){
            this.appearance = appearance;
        }
        public void setPrice(int price){
            this.price = price;
        }
        public int getAppearance(){
            return this.appearance;
        }
        public int getPrice(){
            return this.price;
        }
    }
    public static ArrayList<Toy> perfect_toys = new ArrayList<Toy>();
    public static int[] next;
    public static void getNext(){
        next[0] = -1;
        int j=-1;
        int i=0;
        while(i<perfect_toys.size()){
            if(j==-1||(perfect_toys.get(i).getPrice()==perfect_toys.get(j).getPrice()
                    &&perfect_toys.get(i).getAppearance()==perfect_toys.get(j).getAppearance())){
                ++j;
                ++i;
                next[i] = j;
            }else{
                j = next[j];
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int perfectK = sc.nextInt();
        next = new int[perfectK+1];
        for(int i=0;i<perfectK;i++){
            int a = sc.nextInt();
            Toy tmp = new Toy();
            tmp.setAppearance(a);
            perfect_toys.add(tmp);
        }
        for(int i=0;i<perfectK;i++){
            int b = sc.nextInt();
            perfect_toys.get(i).setPrice(b);
        }
        /*
        for (int i=0;i<perfectK;i++){
            System.out.println(perfect_toys.get(i).getAppearance() + " " + perfect_toys.get(i).getPrice());
        }*/
        getNext();
        int n = sc.nextInt();
        ArrayList<Toy> all_toys = new ArrayList<Toy>();
        for(int i=0;i<n;i++){
            int a = sc.nextInt();
            Toy tmp = new Toy();
            tmp.setAppearance(a);
            all_toys.add(tmp);
        }
        for(int i=0;i<n;i++){
            int b = sc.nextInt();
            all_toys.get(i).setPrice(b);
        }
        int i=0,j=0;
        while(i<all_toys.size()&&j<perfect_toys.size()){
            if(all_toys.get(i).getAppearance()==perfect_toys.get(j).getAppearance()&&all_toys.get(i).getPrice()==perfect_toys.get(j).getPrice()){
                ++i;
                ++j;
            }else{
                j = next[j];
            }
        }
        if(j==perfect_toys.size()){

            System.out.println(i-j+1);
        }else{
            System.out.println("s"+0);
        }
    }
}
