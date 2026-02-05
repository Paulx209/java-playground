package com.sonicge.dailyAlgorithm;


import java.util.HashMap;
import java.util.Scanner;

/**
 * 罗马数字转整数
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 输入: s = "III"
 * 输出: 3
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-05
 */
public class Demo13 {
    static HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int i = romanToInt(str);
        System.out.println("罗马数字转换为十进制数字为:" + i);

    }
    public static int romanToInt(String s){
        int res = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            Integer val1 = hashMap.get(c);
            Integer val2 = i+1 < s.length() ? hashMap.get(s.charAt(i+1)) : 0;
            if( i+1 < s.length() && val1 < val2){
                //如果遇到了IV这种情况 就要减去当前的值 
                res -= val1;
                continue;
            }
            res += val1;
        }
        return res;
    }
}
