package com.sonicge.dailyAlgorithm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 【矩阵置零】
 *
 * 给定一个m * n 的矩阵，如果一个元素为0的话，其所在列和行的所有元素都为0。
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-01
 */

public class Demo28 {
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
        setZeroes(arr);
        //遍历输出
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.println(arr[i][j]);
            }
        }

    }

    public static void setZeroes(int[][] matrix) {
        int x = matrix.length;
        int y = matrix[0].length;
        //矩阵置零
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        //遍历所有的matrix，找到矩阵为0的行和列
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int row : rows) {
            for (int j = 0; j < y; j++) {
                matrix[row][j] = 0;
            }
        }
        for (int col : cols) {
            for (int i = 0; i < x; i++) {
                matrix[i][col] = 0;
            }
        }
    }
}
