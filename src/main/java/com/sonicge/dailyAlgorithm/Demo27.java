package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【旋转图像】
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-28
 */

public class Demo27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for(int i = 0; i < n;i++){
            for(int j = 0;j < n;j++){
                arr[i][j] = sc.nextInt();
            }
        }

        //开始旋转图像
        rotate(arr);
        //遍历打印
        for(int part[] : arr){
            for(int num :part){
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }

    public static void rotate(int[][] matrix) {
        //对角线旋转
        int x = matrix.length;
        int y = matrix[0].length;
        for(int i=0;i<x;i++){
            for(int j=0;j<i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //按照中间列进行替换
        for(int i = 0 ; i< x; i++){
            for(int j = 0; j < y/2 ; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][y-1-j];
                matrix[i][y-1-j] = temp;
            }
        }
    }
}
