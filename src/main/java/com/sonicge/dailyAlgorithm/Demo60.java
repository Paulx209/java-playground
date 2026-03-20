package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【图论 - 岛屿数量】
 * 给你一个二维数组，其中1是陆地，0是水，然后让你找出岛屿的数量
 * <p>
 * 输入：grid = [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出：3
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-20
 */

public class Demo60 {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int numIslands(char[][] grid) {
        int x = grid.length;
        int y = grid[0].length;
        int res = 0;
        //初始值是false
        boolean[][] marked = new boolean[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (!marked[i][j] && grid[i][j] == '1') {
                    dfs(i, j, grid, marked);
                    res++;
                }
            }
        }
        return res;
    }

    public static void dfs(int x, int y, char[][] grid, boolean[][] marked) {
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == '1') {
                if (!marked[newX][newY]) {
                    dfs(newX, newY, grid, marked);
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        char[][] grid = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                grid[i][j] = sc.next().charAt(0);
            }
        }
        int res = numIslands(grid);
        System.out.println("岛屿的数量为:" + res);
    }
}
