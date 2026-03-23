package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 【克隆图】
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-23
 */

public class Demo62 {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private Node initNode() {
        Scanner sc = new Scanner(System.in);
        int val1 = sc.nextInt();
        int val2 = sc.nextInt();
        int val3 = sc.nextInt();
        int val4 = sc.nextInt();

        Node node1 = new Node(val1);
        Node node2 = new Node(val2);
        Node node3 = new Node(val3);
        Node node4 = new Node(val4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        return node1;
    }

    HashMap<Node, Node> map = new HashMap<>();

    private Node cloneGraph(Node node) {
        //第一步，判断node是否为null
        if (node == null) return null;
        //第二步，判断map中已经创建过
        if (map.containsKey(node)) {
            return map.get(node);
        }
        //第三步，如果没有存在的话，先克隆，然后再处理临边关系
        Node cloneNode = new Node(node.val);
        map.put(node, cloneNode);
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }

    public static void main(String[] args) {
        Demo62 test = new Demo62();
        Node node = test.initNode();
        Node cloneNode = test.cloneGraph(node);
    }

}
