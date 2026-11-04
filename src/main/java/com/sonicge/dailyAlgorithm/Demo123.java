package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【路径总和II】
 * @Author: sonicge
 * @CreateTime: 2026-11-04
 */

public class Demo123 {
    static List<List<Integer>> res = new ArrayList<>();
    static class TreeNode {
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

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.left = node9;
        node6.right = node10;

        Scanner sc = new Scanner(System.in);
        int targetSum = sc.nextInt();
        pathSum(node1,targetSum);

        for(List<Integer> part : res){
            for(Integer val :part){
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum,new ArrayList<>());
        return res;
    }
    public static void dfs(TreeNode root,int targetSum,List<Integer> part){
        if(root == null){
            return;
        }
        part.add(root.val);
        targetSum-=root.val;
        if(targetSum == 0 && root.left == null && root.right == null){
            res.add(new ArrayList<>(part));
        }
        dfs(root.left,targetSum,part);
        dfs(root.right,targetSum,part);
        part.remove(part.size()-1);
    }

}
