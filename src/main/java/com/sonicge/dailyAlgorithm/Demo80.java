package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【分割回文串】
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-10
 */

public class Demo80 {
    static List<List<String>> res = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        backTracking(str,0,new StringBuilder(),new ArrayList<>());
        for(List<String> part:res){
            for(String s:part){
                System.out.print(s +" ");
            }
            System.out.println("");
        }
    }

    /**
     * 回溯
     */
    public static void backTracking(String str, int start, StringBuilder sb, List<String> list){
        if(start == str.length()){
            res.add(new ArrayList<>(list));
            return ;
        }
        for(int i= start;i<str.length();i++){
            sb.append(str.charAt(i));
            if(isHw(sb.toString())){
                list.add(sb.toString());
                backTracking(str,i+1,new StringBuilder(),list);
                //这里是必须删除的！如果不删除的话，你使用aa的话，就会多加一个"a"的字符串！
                list.remove(list.size()-1);
            }
        }
    }
    public static boolean isHw(String str){
        int left = 0;
        int right = str.length()-1;
        while(left < right){
            if(str.charAt(left) != str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
