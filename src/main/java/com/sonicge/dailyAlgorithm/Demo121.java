package com.sonicge.dailyAlgorithm;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 【二叉树最大宽度】 bfs + 存储每个节点的idx
 *@Author: sonicge
 *@CreateTime: 2026-11-03
 */

public class Demo121 {
    static class Pair {
        TreeNode node;
        long idx;

        public Pair(TreeNode node, long idx) {
            this.node = node;
            this.idx = idx;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 1.初始化
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;
        node4.left = node6;
        node5.left = node7;
        // 2. 开始处理
        System.out.println("最大宽度为:" + widthOfBinaryTree(node1));
    }

    public static int widthOfBinaryTree(TreeNode root) {
        //1.队列
        Queue<Pair> queue = new LinkedList<>();
        if (root != null) {
            queue.add(new Pair(root, 0L));
        }
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            long leftIdx = queue.peek().idx;
            long rightIdx = 0;
            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                TreeNode node = pair.node;
                long idx = pair.idx;
                rightIdx = idx;
                if(node.left!=null){
                    queue.add(new Pair(node.left,idx*2));
                }
                if(node.right!=null){
                    queue.add(new Pair(node.right,idx*2+1));
                }
            }
            maxWidth = Math.max(maxWidth,(int)(rightIdx-leftIdx+1));
        }
        return maxWidth;
    }
}
