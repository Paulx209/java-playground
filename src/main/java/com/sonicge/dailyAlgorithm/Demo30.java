package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【赎金信】
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-01
 */

public class Demo30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ransomNote = sc.next();
        String magazine = sc.next();
        boolean flag = canConstruct(ransomNote,magazine);
        System.out.println("是否可以被赎回来：" + flag);

    }
    public static boolean canConstruct(String ransomNote, String magazine){
        //ransomNote中的字符以及数量 在magazine中是否存在
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            int index = magazine.charAt(i) - 'a';
            arr[index]++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';
            arr[index]--;
            if(arr[index] < 0 ){
                return false;
            }
        }
        return true;
    }
}
