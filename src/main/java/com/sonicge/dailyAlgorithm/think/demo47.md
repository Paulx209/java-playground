* 【反转链表II】
* 反转链表II是在反转链表的基础上做了升级，不是从头到尾挨个反转，而是只翻转部分的，所以参数会多一个[left,right]
* 输入：head = [1,2,3,4,5], left = 2, right = 4
* 输出：[1,4,3,2,5]

思路：

1.已知left，right。所以我们要找到左边界的前一个节点(pre)；然后找到右节点的后一个节点(tail)，然后将右节点的next 置 null。

2.然后对中间的链表翻转，翻转之后获取到newHead，pre.next.next = tail (之前pre节点的下一个节点变成了链表中的最后一个节点，所以要连接tail)

3.pre.next = newHead(pre节点的下一个节点是链表翻转后的头结点)

4.return dummyHead.next
