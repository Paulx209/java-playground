package com.sonicge.dailyAlgorithm.think;

import java.util.Scanner;

/**
 * [有效的数独_v2]
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可！规则如下：
 * 1.大矩阵中一行和一列中的数字必须为1 -> 9，且不能出现重复的数字
 * 2.小矩阵为3*3，格子中不能出现重复的数字
 *
 *@Author: sonicge
 *@CreateTime: 2026-09-13
 */

public class Demo25_v2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        char[][] board = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = sc.next().charAt(0);
            }
        }
        System.out.println("是否是有效的数独：" + isValidSudoku(board));

    }

    /**
     * 有效的数独_v2
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        int x = board.length;
        int y = board[0].length;
        //行
        boolean[][] rows = new boolean[x][y];
        //列
        boolean[][] cols = new boolean[x][y];
        //块
        boolean[][] blocks = new boolean[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(board[i][j] != '.'){
                    int val = board[i][j] - '1';
                    int blockIndex = (i/3)*3 + j/3;
                    if(rows[i][val] || cols[val][j] || blocks[blockIndex][val]){
                        return false;
                    }
                    rows[i][val] = true;
                    cols[val][j] = true;
                    blocks[blockIndex][val] = true;
                }
            }
        }
        return true;

    }
}
