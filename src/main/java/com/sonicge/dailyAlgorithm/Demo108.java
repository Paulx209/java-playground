package com.sonicge.dailyAlgorithm;


import java.util.Scanner;

/**
 * 【二叉搜索树中第 K 小的元素】
 *  中序遍历过程中直接判断，空间复杂度O(1)
 *  @Author: sonicge
 *  @CreateTime: 2026-09-19
 */

public class Demo108 {
    static int index = 0;
    static int res;
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public  TreeNode(int val){
            this.val =val;
        }
        public TreeNode(int val,TreeNode left,TreeNode right){
            this(val);
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        root1.left = node2;
        root1.right  = node3;
        node2.right = node4;

        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        dfs(root1,k);
        System.out.println("第" + k + "小的值是:" + res);
    }
    private static void dfs(TreeNode root,int k){
        if(root == null){
            return;
        }
        if(root.left != null){
            dfs(root.left,k);
        }
        index++;
        if(index == k){
            res = root.val;
            return;
        }
        if(root.right != null){
            dfs(root.right,k);
        }
    }
}
