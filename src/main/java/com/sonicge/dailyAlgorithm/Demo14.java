package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 整数转罗马数字 (罗马数字转整数的进阶版)
 *  符号 值
 *  I	1
 *  V	5
 *  X	10
 *  L	50
 *  C	100
 *  D	500
 *  M	1000
 *
 * 如果该值不是以 4 或 9 开头，请选择可以从输入中减去的最大值的符号，将该符号附加到结果，减去其值，然后将其余部分转换为罗马数字。
 * 如果该值以 4 或 9 开头，使用 减法形式，表示从以下符号中减去一个符号，例如 4 是 5 (V) 减 1 (I): IV ，9 是 10 (X) 减 1 (I)：IX。仅使用以下减法形式：4 (IV)，9 (IX)，40 (XL)，90 (XC)，400 (CD) 和 900 (CM)。
 * 只有 10 的次方（I, X, C, M）最多可以连续附加 3 次以代表 10 的倍数。你不能多次附加 5 (V)，50 (L) 或 500 (D)。如果需要将符号附加4次，请使用 减法形式。
 *
 * 输入：num = 3749
 *
 * 输出： "MMMDCCXLIX"  MMM 3000 DCC 700  XL 40 IX 9
 *
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-10
 */

public class Demo14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String res = intToRoman(num);
        System.out.println("转换后的结果为：" + res);
    }
    public static String intToRoman(int num){
        int[] values = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strings = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<values.length;i++){
            int value = values[i];
            String string = strings[i];
            while(num >=value){
                sb.append(string);
                num-=value;
            }
        }
        return sb.toString();
    }
}
