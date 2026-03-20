package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【二叉搜索树中的最小绝对差】
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-20
 */

public class Demo59 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode init() {
        Scanner sc = new Scanner(System.in);
        TreeNode node1 = new TreeNode(sc.nextInt());
        TreeNode node2 = new TreeNode(sc.nextInt());
        TreeNode node3 = new TreeNode(sc.nextInt());
        TreeNode node4 = new TreeNode(sc.nextInt());
        TreeNode node5 = new TreeNode(sc.nextInt());

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        return node1;
    }

    int min = Integer.MAX_VALUE;
    TreeNode pre = null;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (pre != null) {
            min = Math.min(min, root.val - pre.val);
        }
        //这里先更新 再dfs右边  符合root.val - pre.val 为正数
        pre = root;
        dfs(root.right);
    }

    public static void main(String[] args) {
        Demo59 test = new Demo59();
        TreeNode root = test.init();
        int minimumDifference = test.getMinimumDifference(root);
        System.out.println(minimumDifference);
    }

}
