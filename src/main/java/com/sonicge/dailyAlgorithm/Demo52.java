package com.sonicge.dailyAlgorithm;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 【 从前序与中序遍历序列构造二叉树 】
 * 给定两个整数数组 preorder 和 inorder ，
 * 其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-15
 */

public class Demo52 {
    HashMap<Integer,Integer> hashMap = new HashMap<>();
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
        int[] preorder = new int[size1];
        int[] inorder = new int[size2];

        for (int i = 0; i < size1; i++) {
            preorder[i] = sc.nextInt();
        }

        for (int i = 0; i < size2; i++) {
            inorder[i] = sc.nextInt();
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder){
        for(int i=0;i<inorder.length;i++){
            hashMap.put(inorder[i],i); //存放的是 值:下标
        }
        //然后开始递归
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    /**
     *
     * @param preorder 前序数组
     * @param preStart 前序遍历的开始
     * @param preEnd   前序遍历的末尾
     * @param inorder  中序数组
     * @param inStart  中序遍历的开始
     * @param inEnd    中序遍历的末尾
     * @return
     */
    public TreeNode build(int[] preorder,int preStart,int preEnd ,int[] inorder,int inStart,int inEnd){
        //1.对于当前来说，前序遍历中的第一个节点就是根节点
        int rootVal = preorder[preStart];
        //获取中序中该值的下标
        Integer indexOfInOrder = hashMap.get(rootVal);

        //2.创建根节点
        TreeNode root = new TreeNode(rootVal);
        int leftTreeSize = indexOfInOrder-preStart;

        //3.递归获取左子树根节点，和右子树根节点！
        //左子树的前序：第一个就是根节点，所以要跳过第一个，到什么时候停止呢？也就是左子树的个数有多少个
        //左子树的中序  第一个点就是
        //右子树的前序
        //右子树的中序
        root.left = build(preorder,preStart+1,preStart+leftTreeSize,inorder,inStart,leftTreeSize-1);
        root.right =build(preorder,preStart+leftTreeSize+1,preEnd,inorder,indexOfInOrder+1,inEnd);
        return root;
    }

}
