package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【生命游戏】
 *
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-01
 */

public class Demo29 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        int[][] arr = new int[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        //矩阵置零
        gameOfLife(arr);
        //遍历输出
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.println(arr[i][j]);
            }
        }

    }

    public static  void gameOfLife(int[][] board) {
        int x = board.length;
        int y = board[0].length;

        // 1. 创建原数组的副本，保存初始状态（关键！）
        int[][] copyBoard = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int cells = haveLiveCell(copyBoard, i, j);
                if (cells < 2 && board[i][j] == 1)
                    board[i][j] = 0;
                else if ((cells == 2 || cells == 3) && board[i][j]==1)
                    board[i][j] = 1;
                else if (cells > 3 && board[i][j] == 1)
                    board[i][j] = 0;
                else if(cells == 3 && board[i][j] ==0 )
                    board[i][j] = 1;
            }
        }
    }

    //判断(x,y)该坐标周围有多少个活细胞
    public static  int haveLiveCell(int[][] board, int x, int y) {
        int liveCells = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                if ((newX < 0 || newX >= board.length) || (newY < 0 || newY >= board[0].length ||(i ==0 && j ==0)))
                    continue;
                if (board[newX][newY] == 1)
                    liveCells++;
            }
        }
        return liveCells;
    }
}
