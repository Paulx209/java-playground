* 【两两交换链表中的节点】
* 给定一个链表，然后每次将两个相邻的节点交换，并且返回交换后链表的头结点
* 输入：head = [1,2,3,4]
* 输出：[2,1,4,3]

思路：

1.只需要定义一个pre节点，然后维护first \ second \ newHead节点就可以了

2.first.next = null ; second.next = first; pre.next = second; first.next = newHead;


