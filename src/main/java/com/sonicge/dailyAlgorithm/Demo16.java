package com.sonicge.dailyAlgorithm;


import java.util.Scanner;

/**
 * 【最长公共前缀】
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * @Author: sonicge
 * @CreateTime: 2026-02-15
 */

public class Demo16 {
    public static void main(String[] args) {
        //1.初始化
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        String[] strs = new String[size];
        for(int i=0;i<size;i++){
            strs[i] = sc.next();
        }
        //2.调用处理函数
        String commonPrefix = longestCommonPrefix(strs);
        System.out.println("共同的字符串前缀为:"+commonPrefix);

    }
    public static String longestCommonPrefix (String[] args){
        String str = args[0];
        int res = 0;
        for(int i=0;i<str.length();i++){
            //截取一部分
            String substring = str.substring(0, i + 1);
            boolean flag = isSimilar(args,substring);
            if(!flag) return str.substring(0,res);
            res = i+1;
        }
        return str.substring(0,res);
    }

    public static boolean isSimilar(String[] args,String subString){
        for(String str:args){
            boolean b = str.startsWith(subString);
            if(!b)return false;
        }
        return true;
    }
}
