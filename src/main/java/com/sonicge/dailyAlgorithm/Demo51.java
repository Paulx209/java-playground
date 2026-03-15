package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【完全二叉树的节点个数】
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树：如果一个节点的左节点为null的话，那么右节点一定为null；如果左节点不为null的话，右节点可能为null
 * 也就是说节点的排序优先级 左 > 右
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-15
 */

public class Demo51 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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

    public void initTree() {
        //树的初始化
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

        node3.left = node6;

        int res = countNodes(node1);
        System.out.println("有" + res + "个节点") ;
    }

    public int countNodes(TreeNode root) {
        //计算完全二叉树的节点个数
        if (root == null) return 0;
        int leftHeight = 0;
        int rightHeight = 0;
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;

        while (leftNode != null) {
            leftNode = leftNode.left;
            leftHeight++;
        }
        while (rightNode != null) {
            rightNode = rightNode.right;
            rightHeight++;
        }
        //如果左右两边相等的话,说明是一个满二叉树 直接返回2^n -1
        if (leftHeight == rightHeight) return (2 << leftHeight) - 1;
        //如果两边不相等的话，就要左右两边挨个算了
        return countNodes(root.left) + countNodes(root.right) + 1;

    }

    public static void main(String[] args) {
        Demo51 demo51 = new Demo51();
        demo51.initTree();
    }
}
