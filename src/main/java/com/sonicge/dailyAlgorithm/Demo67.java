package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【单词搜索】
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
 * 输出：true
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-01
 */

public class Demo67 {
    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    static boolean flag = false;
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
        String word = sc.next();
        boolean flag = exist(board, word);
        System.out.println("该单词是否存在：" + flag);
    }

    public static boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == word.charAt(0)){
                    backTracking(board,word,i,j,0);
                    if(flag) return true;
                }
            }
        }
        return flag;
    }
    public static void backTracking(char[][] board,String word,int x,int y,int index){
        if(index == word.length()){
            flag = true;
            return ;
        }
        if(x < 0 || x>=board.length || y<0 || y>=board[0].length || board[x][y] != word.charAt(index) || flag){
            return;
        }

        char temp = board[x][y];
        board[x][y] = '!';
        for(int[] d : directions){
            int newX = d[0]+ x;
            int newY = d[1]+ y;
            backTracking(board,word,newX,newY,index+1);
        }
        board[x][y] = temp;
    }
}
