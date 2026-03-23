package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【被围绕的区域】
 * 给定一个二维数组，由X和O两种字符组合而成，如果O连成的区域到达边缘的话，那么这一块区域就不算被围绕的；
 * 否则需要将被围绕的区域全部改成'X'
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-21
 */

public class Demo61 {
    static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void solve(char[][] board) {
        int x = board.length;
        int y = board[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
                    if (board[i][j] == 'O') {
                        dfs(board, x, y);
                    }
                }
            }
        }

        //现在只要还有O 就是被围住的
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        //将所有的A替换成对应的'O'
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void dfs(char[][] board, int x, int y) {
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
            board[x][y] = 'A';
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                dfs(board, newX, newY);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        char[][] board = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = sc.next().charAt(i);
            }
        }
        solve(board);
    }
}
