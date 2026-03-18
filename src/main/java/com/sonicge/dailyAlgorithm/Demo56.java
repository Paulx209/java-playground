package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【填充每个节点的下一个右侧节点指针 II】
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * 就是将每个节点的next指针置为右侧的节点，如果右侧为null的话直接设置为NULL
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-18
 */

public class Demo56 {
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

        TreeNode connect = connect(node1);

    }

    public TreeNode connect(TreeNode root) {
        if (root == null) return null;
        List<TreeNode> part = new ArrayList<>();
        part.add(root);

        //如果part不为空的话
        while (!part.isEmpty()) {
            List<TreeNode> temp = part;
            part = new ArrayList<>();
            for (int i = 0; i < temp.size(); i++) {
                TreeNode node = part.get(i);
                if (i > 0) {
                    part.get(i - 1).next = node;
                }
                if (node.left != null) {
                    part.add(node.left);
                }
                if (node.right != null) {
                    part.add(node.right);
                }
            }
        }
        return root;
    }
}
