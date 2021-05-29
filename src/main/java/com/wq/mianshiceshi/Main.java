package com.wq.mianshiceshi;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while(T!=0){
            T--;
            Map<Integer, String> fdToFileName = new HashMap<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int N = sc.nextInt();
            for(int i=0;i<=1000005;i++){
                pq.add(i);
            }
            for(int i=0;i<N;i++){
                String op = sc.next();
                if(op.equals("open")){
                    String filename;
                    filename = sc.next();
                    //System.out.println(filename);
                    int new_fd = pq.poll();
                    fdToFileName.put(new_fd, filename);
                    System.out.println(new_fd);
                }else if(op.equals("dup")){
                    int old_fd;
                    old_fd = sc.nextInt();
                    //System.out.println(old_fd);
                    int new_fd = pq.poll();
                    fdToFileName.put(new_fd, fdToFileName.get(old_fd));
                    System.out.println(new_fd);
                }else if(op.equals("dup2")){
                    int old_fd = sc.nextInt();
                    int new_fd = sc.nextInt();
                    //System.out.println(old_fd +" " +new_fd);
                    if(fdToFileName.containsKey(new_fd)){
                        fdToFileName.put(new_fd, fdToFileName.get(old_fd));
                    }else{
                        pq.remove(new_fd);
                        fdToFileName.put(new_fd, fdToFileName.get(old_fd));
                    }
                }else if(op.equals("close")){
                    int old_fd = sc.nextInt();
                    //System.out.println(old_fd);
                    pq.add(old_fd);
                    fdToFileName.remove(old_fd);
                }else if(op.equals("query")){
                    int old_fd = sc.nextInt();
                    System.out.println(fdToFileName.get(old_fd));
                }
            }
        }



    }
}
