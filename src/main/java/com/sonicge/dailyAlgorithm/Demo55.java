package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【二叉树中的最大路径和】
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 意思就是：如果你从根节点出发，你只能走向一边，不能走来时路；一条边只能走一次
 * <p>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-18
 */

public class Demo55 {
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

    public void init() {
        Scanner sc = new Scanner(System.in);
        TreeNode node1 = new TreeNode(sc.nextInt());
        TreeNode node2 = new TreeNode(sc.nextInt());
        TreeNode node3 = new TreeNode(sc.nextInt());
        TreeNode node4 = new TreeNode(sc.nextInt());
        TreeNode node5 = new TreeNode(sc.nextInt());

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        int i = maxPathSum(node1);
        System.out.println("二叉树中的最大路径和为: " + i);
    }

    private int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    /**
     * 二叉树中的最大路径和
     *
     * @param root
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftVal = Math.max(0, dfs(root.left));
        int rightVal = Math.max(0, dfs(root.right));

        res = Math.max(res, leftVal + rightVal + root.val);
        return Math.max(leftVal, rightVal) + root.val;
    }

    public static void main(String[] args) {

    }
}
