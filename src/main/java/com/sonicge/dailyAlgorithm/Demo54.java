package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【二叉树展开为链表】
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-17
 */

public class Demo54 {
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
        TreeNode node6 = new TreeNode(sc.nextInt());

        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        node5.right = node6;
    }

    public void flatten1(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        dfs(path, root);
        TreeNode temp = root;
        //先序遍历将所有的节点值存起来
        for (int i = 1; i < path.size(); i++) {
            TreeNode node = new TreeNode(path.get(i));
            temp.left = null;
            temp.right = node;
            temp = temp.right;
        }
    }

    /**
     * 思路一：遍历获取所有的节点val
     *
     * @param path
     * @param root
     */
    public void dfs(List<Integer> path, TreeNode root) {
        if (root == null) return;
        path.add(root.val);
        dfs(path, root.left);
        dfs(path, root.right);
    }

    /**
     * 思路二
     *
     * @param root 根节点
     */
    public void flatten2(TreeNode root) {
        if (root == null) return;
        //始终保持一个原则：展开为链表之后，左子树的顺序是在右子树之前的
        flatten2(root.left);
        flatten2(root.right);
        TreeNode oldRight = root.right;
        root.right = root.left;
        root.left = null;
        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = oldRight;
    }
}
