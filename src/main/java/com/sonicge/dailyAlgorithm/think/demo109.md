* 【除法求值】
* 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
* 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
* 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
* 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
* 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
*
* 思路：有向带权图 + dfs

1. 构建带向有全图，使用Map<String,List<Pair<String,Double>>>来进行构建。

2. 开始处理每一个queries，每一对<String To> 都采用dfs的方式进行搜索，如果当前遍历的点正好是To的话，说明搜到了；如果不是的话，就继续搜索当前点可以到达的下一个点

3. 最终如果搜不到的话，就直接返回-1.0；需要通过一个Set<String>集合判断已经走过的点，防止往回走。

4. 如果queries中的from 和 to 本身在有向带权图中不存在的话，直接置为-1.0。
