package com.sonicge.dailyAlgorithm;
/**
 * 【岛屿的最大面积】
 *  从一个点出发：如果该点不能向外扩展，直接返回一；如果能向外扩展，返回 1 + dfs()
 *@Author: sonicge
 *@CreateTime: 2026-11-21
 */

public class Demo127 {
    static int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println("岛屿的最大面积为：" + maxAreaOfIsland(grid));
    }

    /**
     * 岛屿的最大面积
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] flags = new boolean[row][col];
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == 1 && !flags[i][j]){
                    int part = dfs(grid,i,j,flags);
                    res = Math.max(res,part);
                }
            }
        }
        return res;
    }
    public static int dfs(int[][] grid,int x,int y,boolean[][] flags){
        flags[x][y] = true;
        int res = 1;
        for(int[] dir :directions){
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && !flags[newX][newY] && grid[newX][newY] == 1){
                int part = dfs(grid,newX,newY,flags);
                res += part;
            }
        }
        return res;
    }

}
