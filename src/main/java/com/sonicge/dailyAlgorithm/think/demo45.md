* 给定一串链表，深拷贝其中的内容。
* 注意是深拷贝，不能直接指向对面的地址。
* https://leetcode.cn/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-interview-150

思路：

1.由于是深拷贝，就必须创建每一个新的节点，因此要先获取旧链表的所有节点，这里使用一个Map<Node,Node>来存储。

2.每次遍历到一个旧节点的时候，就创建一个新的节点，这里只赋予初始的值，后面的next和random，到后面第二次遍历的时候再算。

3.新的节点创建好之后，然后map.put(node,newNode)

4.开始第一波遍历，遍历的时候获取到node，然后将newNode的next 和 random  赋值为map.get(temp.next) 和 map.get(temp.random)
