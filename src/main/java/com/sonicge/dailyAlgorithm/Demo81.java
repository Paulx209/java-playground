package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【复原 IP 地址】 有点类似于回文字符串这道题
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-10
 */

public class Demo81 {
    static List<String> res= new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        String str = sc.next();
        List<String> res = restoreIpAddresses(str);
        for(String part:res){
            System.out.println(part);
        }
    }

    /**
     * 复原IP地址
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s){
        if(s == null || s.length()==0 || s.length()>12)return res;
        backTracking(s,0,0);
        return res;
    }

    public  static void backTracking(String s,int startIndex,int countNum){
        if(countNum > 3)return ;
        if(countNum == 3){
            if(isValid(s,startIndex,s.length()-1)){
                res.add(s);
                return ;
            }
        }
        for(int i=startIndex;i<s.length();i++){
            if(isValid(s,startIndex,i)){
                //如果符合的话，就修改s，然后递归
                s = s.substring(0,i+1) + "."+s.substring(i+1);
                backTracking(s,i+2,countNum+1);
                //这里要把那个.给删除掉
                s=s.substring(0,i+1) + s.substring(i+2);
            }else{
                //此时循环就不继续推进了
                break;
            }
        }

    }
    public static boolean isValid(String s,int start,int end){
        if(start >end)return false;
        if(start!=end && s.charAt(start) == '0')return false;
        if(end-start > 2)return  false;
        int val = 0;
        for(int i =start;i<=end;i++){
            val = val *10 + (s.charAt(i) - '0');
            if(val > 255)return false;
        }
        return true;
    }
}
