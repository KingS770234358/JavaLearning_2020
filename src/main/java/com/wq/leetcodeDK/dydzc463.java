package com.wq.leetcodeDK;

public class dydzc463 {
    public static void main(String[] args) {
        int [][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };
        int res = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]==1){
                    int tmp = 4;
                    // System.out.println(i + " " + j);
                    if(i-1>=0&&grid[i-1][j]==1){
                        tmp--;
                    }
                    if(j+1<grid[0].length&&grid[i][j+1]==1){
                        tmp--;
                    }
                    if(i+1<grid.length&&grid[i+1][j]==1){
                        tmp--;
                    }
                    if(j-1>=0&&grid[i][j-1]==1){
                        tmp--;
                    }

                    res+=tmp;
                }
            }
        }
        System.out.println(res);
    }
}
