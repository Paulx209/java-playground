# 【快速排序 —— 三路快排】


思路：

1. 随机选取一个基准值pivot，可以使用ThreadLocalRandom.current().nextInt(left,right)来随机取一个下标，然后获取他的值

2. 然后定义`less` `current` `greater` 三个变量，以及数组的左右边界`left` `right` ，一共五个变量，我们通过处理这五个变量来维护三个区域

3. 小于基准值的区域：[left,less]  ; 等于基准值的区域：[less+1,greater]  ;  大于基准值的区域：[greater+1,righter]

4. 维护这套区域的逻辑是什么？ 
   1. 如果arr[current] < pivot的话,current 和 less swap，然后less current 扩大 ; 

   2. 如果arr[current] > pivot的话，current 和 greater swap，然后greater -- ，但是current不能++，因为还没检查交换后的值
   3. 如果arr[current] == pivot的话，current++即可，这样left+1 和 current这个区域就是 == pivot的区域
