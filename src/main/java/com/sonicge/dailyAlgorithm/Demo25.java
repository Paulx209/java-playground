package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * [有效的数独]
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可！规则如下：
 * 1.大矩阵中一行和一列中的数字必须为1 -> 9，且不能出现重复的数字
 * 2.小矩阵为3*3，格子中不能出现重复的数字
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-26
 */

public class Demo25 {
    public static void main(String[] args) {
        //1.初始化
        Scanner sc = new Scanner(System.in);
        char[][] array = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                array[i][j] = sc.next().charAt(0);
            }
        }
        //2.判断数独是否有效
        boolean isValidate = isValidSudoku(array);
        System.out.println("数组是有效的吗：" + isValidate);

    }
    public static boolean isValidSudoku(char[][] board){
        //1.先判断大的方格
        //1.1 判断行
        for (int i = 0; i < 9; i++) {
            Map<Integer, Integer> hashMap = new HashMap<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;
                int value = board[i][j] - '0';
                if (hashMap.getOrDefault(value, 0) != 0)
                    return false;
                hashMap.put(value, 1);
            }
        }
        //1.2 判断列
        for (int i = 0; i < 9; i++) {
            Map<Integer, Integer> hashMap = new HashMap<>();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.')
                    continue;
                int value = board[j][i] - '0';
                if (hashMap.getOrDefault(value, 0) != 0)
                    return false;
                hashMap.put(value, 1);
            }
        }
        //2.再判断小的方格
        Map<Integer,Integer>[] boxMaps = new HashMap[9];
        for(int i=0;i<9;i++){
            boxMaps[i] = new HashMap<Integer,Integer>();
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                int index = (i/3)*3+(j/3);
                Map<Integer,Integer> hashMap = boxMaps[index];
                if(board[i][j] == '.')continue;
                int value = board[i][j] - '0';
                if (hashMap.getOrDefault(value, 0) != 0)
                    return false;
                hashMap.put(value, 1);
            }
        }
        //3.最后返回true即可
        return true;
    }
}
