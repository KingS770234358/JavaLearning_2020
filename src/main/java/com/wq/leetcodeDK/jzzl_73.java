package com.wq.leetcodeDK;

import java.util.ArrayList;
import java.util.List;

public class jzzl_73 {
    public static void main(String[] args) {
        int [][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        // 可使用单个变量 位运算存储
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for(int i=0; i<matrix.length;i++){
            for(int j=0; j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for(int i=0; i<rows.size();i++){
            for(int j=0;j<matrix[0].length;j++){
                matrix[rows.get(i)][j] = 0;
            }
        }
        for(int i=0; i<cols.size();i++){
            for(int j=0;j<matrix.length;j++){
                matrix[j][cols.get(i)] = 0;
            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
