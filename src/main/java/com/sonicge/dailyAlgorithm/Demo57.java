package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【二叉树的中序遍历系列题 --- 二叉树的右视图】
 *
 * 输入：root = [1,2,3,null,5,null,4]
 * 输出：[1,3,4]
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-19
 */

public class Demo57 {
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
        node3.right = node4;
        node2.right = node5;

        return node1;
    }

    /**
     * 右视图
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        //这里其实也可以使用队列
//        Queue<TreeNode> queue =new ArrayDeque<>();
//        List<Integer> res = new ArrayList<>();
//        if(root == null)return res;
//        queue.add(root);
//        while(!queue.isEmpty()){
//            int size = queue.size();
//            while(size -- > 0){
//                TreeNode node = queue.poll();
//                if(size == 1){
//                    res.add(node.val);
//                }
//                if(node.left!=null){
//                    queue.add(node.left);
//                }
//                if(node.right!=null){
//                    queue.add(node.right);
//                }
//            }
//        }
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            List<TreeNode> temp = list;
            list = new ArrayList<>();
            int length = temp.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = temp.get(i);
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }
                if (i == length - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Demo57 test = new Demo57();
        TreeNode node = test.init();
        List<Integer> res = test.rightSideView(node);
        for (Integer num : res) {
            System.out.println(num);
        }
    }
}
