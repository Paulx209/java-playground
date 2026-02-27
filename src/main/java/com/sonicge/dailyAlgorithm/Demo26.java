package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * [螺旋矩阵]
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-27
 */

public class Demo26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        //开始做处理
        List<Integer> integers = spiralOrder(matrix);
        for (Integer num : integers) {
            System.out.println(num);
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        //开始做螺旋矩阵处理
        int rowStart = 0;
        int rowEnd = matrix.length-1;

        int colStart = 0;
        int colEnd = matrix[0].length-1;

        List<Integer> res = new ArrayList<>();

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                res.add(matrix[rowStart][i]);
            }
            rowStart++;
            for (int i = rowStart; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            if (rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--) {
                    res.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }
            if (colStart<=colEnd){
                for(int i =rowEnd ; i>=rowStart ; i--){
                    res.add(matrix[i][colStart]);
                }
                colStart++;
            }
        }
        return res;
    }
}
