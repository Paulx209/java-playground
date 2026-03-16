* 【 从前序与中序遍历序列构造二叉树 】
* 给定两个整数数组 preorder 和 inorder ，
* 其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

思路：

~~待补充~~

1.前序遍历和中序遍历我们需要满足这样一个规则

无论这棵树长什么样，它的前序和中序遍历数组永远遵循以下固定排版：

前序遍历 (Preorder): [ 根节点 | -----左子树的所有节点----- | -----右子树的所有节点----- ]

中序遍历 (Inorder): [ -----左子树的所有节点----- | 根节点 | -----右子树的所有节点----- ]

2.所以主要就是计算左子树中的前序遍历和中序遍历；以及右子树中的前序遍历和中序遍历

左子树中的前序遍历(preorder , preStart + 1 ,preStart + leftTreeSize)

左子树的中序遍历(inorder,inStart,rootIndexInOrder-1)

右子树的前序遍历(preorder,preStart+1+leftTreeSize,preEnd)

右子树的中序遍历(inorder,rootIndexInOrder+1,inEnd)
