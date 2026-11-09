package com.sonicge.dailyAlgorithm;


/**
 * 【子结构判断】
 *@Author: sonicge
 *@CreateTime: 2026-11-09
 */

public class Demo125 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(3);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(8);
        head1.left = node1;
        head1.right = node2;
        node1.left = node3;
        node1.right = node4;

        TreeNode head2 = new TreeNode(6);
        head2.left = new TreeNode(1);

        System.out.println("head1树中是否存在head2树: " + (isSubStructure(head1,head2) ? "存在" : "不存在"));
    }
    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null)return false;
        return match(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }
    public static boolean match(TreeNode node1,TreeNode node2){
        if(node2 == null)return true;
        if(node1 == null)return false;
        if(node1.val != node2.val)return false;
        return match(node1.left,node2.left) && match(node1.right,node2.right);
    }
}
