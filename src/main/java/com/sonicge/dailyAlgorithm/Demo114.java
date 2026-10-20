package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【不同路径 II -> 网格中的障碍物和空位置分别用 1 和 0 来表示】
 *@Author: sonicge
 *@CreateTime: 2026-09-27
 */

public class Demo114 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] grid =  new int[row][col];
        for(int i = 0;i<row;i++){
            for(int j = 0;j<col;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println("机器人到达右下角的路径数量为:" + uniquePathsWithObstacles(grid));

    }
    public static int uniquePathsWithObstacles(int[][] obstacleGrid){
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        //初始化 第一行
        for(int i = 0;i<col;i++) {
            //只有不碰
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = 1;
            }
        }
        //初始化 第一列
        for(int j=0;j<row;j++){
            if(obstacleGrid[j][0] == 0){
                dp[j][0] = 1;
            }
        }
        //处理
        for(int i = 1;i<row;i++){
            for(int j = 1;j<col;j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] =dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[row-1][col-1];
    }
}
