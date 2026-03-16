package com.sonicge.dailyAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 【 从中序与后序遍历序列构造二叉树】 类似于中序和前序 难度不低
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-16
 */

public class Demo53 {
    Map<Integer,Integer> map = new HashMap<>();
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
        int size1 = sc.nextInt();
        int size2 = sc.nextInt();
        int[] inorder = new int[size1];
        int[] postorder = new int[size2];

        for (int i = 0; i < size1; i++) {
            inorder[i] = sc.nextInt();
        }

        for (int i = 0; i < size2; i++) {
            postorder[i] = sc.nextInt();
        }

        buildTree(inorder,postorder);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i = 0; i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    public TreeNode build(int[] inorder,int inStart,int inEnd ,int[] postOrder,int postStart,int postEnd){
        if(inStart > inEnd || postStart > postEnd) return null;
        int rootVal = postOrder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        int rootOfInorderIndex = map.get(rootVal);
        //左子树的节点数量
        int leftSize = rootOfInorderIndex - inStart;


        root.left = build(inorder,inStart,rootOfInorderIndex-1,postOrder,postStart,postStart+leftSize-1);
        root.right = build(inorder,rootOfInorderIndex+1,inEnd,postOrder,rootOfInorderIndex-inStart+postStart,postEnd-1);

        return root;
    }

    public static void main(String[] args) {
        Demo53 demo53 = new Demo53();
        demo53.initTree();
    }
}
