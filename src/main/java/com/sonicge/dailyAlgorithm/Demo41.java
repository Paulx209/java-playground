package com.sonicge.dailyAlgorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 【简化路径】
 *  给你一串绝对路径(以'/'开头)，请你将其转化为 更加简洁的规范路径。
 *  在 Unix 风格的文件系统中规则如下：
 * 一个点 '.' 表示当前目录本身。
 * 此外，两个点 '..' 表示将目录切换到上一级（指向父目录）。
 * 任意多个连续的斜杠（即，'//' 或 '///'）都被视为单个斜杠 '/'。
 * 任何其他格式的点（例如，'...' 或 '....'）均被视为有效的文件/目录名称。
 *
 * 一句话解释：请你将路径中所有的. , ..去除掉，并且去除多余的 / ，然后返回出来。
 * 输入：path = "/home/user/Documents/../Pictures"
 * 输出："/home/user/Pictures"
 * 解释：两个点 ".." 表示上一级目录（父目录）。
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-08
 */

public class Demo41 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String res = simplifyPath(str);
        System.out.println("简化后的路径为:" + res);
    }

    public static String simplifyPath(String path) {
        String[] split = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < split.length; i++) {
            String part = split[i];
            if(part.equals(".") || part.equals("")){
                continue;
            }else if(part.equals("..")){
                //如果出现了..的话， 上一个路径part要删除，也就是stack中的栈顶元素
                stack.poll(); // 温和取栈顶元素，如果栈顶为空的话，只会返回null，不抛出异常。
            }else{
                stack.push(part);
            }
        }
        //拼接最后的简洁路径
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        while(!stack.isEmpty()){
            String str = stack.pollLast();
            sb.append(str).append("/");
        }
        //去除最后的 '/' 字符
        if(sb.length() > 1){
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }
}
