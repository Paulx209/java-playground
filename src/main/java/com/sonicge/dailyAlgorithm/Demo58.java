package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【二叉树中第k小的元素】
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
 *
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-20
 */

public class Demo58 {
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
        TreeNode node6 = new TreeNode(sc.nextInt());

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;

        return node1;
    }

    List<Integer> res = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        dfs(root);
        return res.get(k - 1);
    }

    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        res.add(root.val);
        dfs(root.right);
    }

    public static void main(String[] args) {
        Demo58 demo58 = new Demo58();
        TreeNode node = demo58.init();
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int index = demo58.kthSmallest(node, k);
        System.out.println("第k小的元素为:" + index);
    }
}
