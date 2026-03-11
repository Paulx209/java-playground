package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【随机链表的复制】
 * 给定一串链表，深拷贝其中的内容。注意是深拷贝，不能直接指向对面的地址。
 * <p>
 * https://leetcode.cn/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-11
 */

public class Demo45 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node initNodeList() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt(); //5
        Node head = new Node(sc.nextInt());
        Node temp = head;
        for (int i = 1; i < size; i++) {
            temp.next = new Node(sc.nextInt());
            temp = temp.next;
        }
        return head;
    }

    /**
     * 深拷贝一串新的Node List
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        //1.收集
        Map<Node, Node> map = new HashMap<>();
        Node temp = head;
        while (temp != null) {
            map.put(temp, new Node(temp.val));
            temp = temp.next;
        }
        //2.拷贝
        temp = head;
        while (temp != null) {
            Node newNode = map.get(temp);
            newNode.next = map.get(temp.next);
            newNode.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

    public static void main(String[] args) {
        Demo45 demo45 = new Demo45();
        Node node = demo45.initNodeList();
        Node newNode = demo45.copyRandomList(node);
        Node temp = newNode;
        while (temp != null) {
            System.out.println(temp.val);
            System.out.println(temp.next.val);
            System.out.println(temp.random.val);
            temp = temp.next;
        }
    }
}
