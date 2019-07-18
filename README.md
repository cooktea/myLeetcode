# LeetCode 题解

|题目编号|题目名称|链接|解题时间|
|:---:|:---:|:---:|:---:|
|4|[寻找两个有序数组的中位数](#Median-of-Two-Sorted-Arrays)|https://leetcode-cn.com/problems/median-of-two-sorted-arrays/|2019-07-11|
|52|[N皇后 II](#N-Queens-II)|https://leetcode-cn.com/problems/n-queens-ii/|2019-07-12|
|980|[不同路径 III](#Unique-Paths-III)|https://leetcode-cn.com/problems/unique-paths-iii/|2019-07-12|
|145|[二叉树的后序遍历](#Binary-Tree-Postorder-Traversal)|https://leetcode-cn.com/problems/binary-tree-postorder-traversal/|2019-07-13|
|51|[N皇后](#N-Queens)|https://leetcode-cn.com/problems/n-queens/|2019-07-14|
|1028|[从先序遍历还原二叉树](#Recover-a-Tree-From-Preorder-Traversal)|https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/|2019-07-16|
|773|[滑动谜题](#Sliding-Puzzle)|https://leetcode-cn.com/problems/sliding-puzzle/|2019-07-16|
|99|[恢复二叉搜索树](#Recover-Binary-Search-Tree)|https://leetcode-cn.com/problems/recover-binary-search-tree/|2019-07-18|


## 解题思路    
### Recover Binary Search Tree  
[查看代码](src/Solution_99.java)  
由于整个二叉搜索树只有两个节点位置错误，则只需要找到这两个节点并交换即可  
使用中序遍历可以获得二叉搜索树的升序排列，当出现错误时必将有一个节点大于后一个节点和一个节点小于前一个节点。
两个错误的节点可以相邻。

### Sliding Puzzle   
[查看代码](src/Solution_773.java)  
使用广度优先遍历，获得所有的移动可能性，直到得到需要的布局或所有可能性都不符合要求。  
在DFS时，维护一个队列用来保存board的状态。维护一个SET用来保存已经生成过的board状态。每次从队列中取出一个board进行判断，并判断该状态有没有生成过。
如果没有生成过，则判断该状态是否符合要求，如果不符合要求，则基于改状态生成“0”上下左右移动后的状态并添加至队列中，循环至队列为空，返回-1。

### Recover a Tree From Preorder Traversal
[查看代码](src/Solution_1028.java)  
首先分割字符串，将字符串按节点分割成子字符串序列，依次从子字符串序列中取出字串并根据字串生成节点和节点的层数，用一个map保存`(用map保存对于性能不够友好)`
生成map后将其添加进节点序列。  
节点序列全部生成后，遍历该节点序列，若节点的层数不为0，即不是根节点，则从该节点往前遍历，当遍历至比该节点的层数少一层的节点时，即遍历到了父节点。
将该节点和父节点相连即可。

### N-Queens  
[查看代码](src/Solution_51.java)  
解题思路和[#52](#N-Queens-II)基本相同。不同处在于当每个皇后都被放置后输出当前棋盘的状态。

### Binary Tree Postorder Traversal
[查看代码](src/Solution_145.java)  
- 递归    
略
- 迭代    
维护一个栈和一个节点对象，节点对象初始化为二叉树的根。同时维护一个链表用来保存后序遍历的序列。
当节点对象不为null且栈也不为空时，将该节点入栈，并将节点的值插入链表头。再将节点置为该节点的右子节点。若新节点为空，则将栈顶节点出栈，并赋值给新节点。
   
### Unique Paths III
[查看代码](src/Solution_980.java)  
使用深度优先搜索进行网格的遍历，当遍历至终点时判断是不是所有网格都已经被访问过，如果是则将路径数加一。  
具体实现方法是使用递归进行遍历操作，每访访问一个网格时先判断该网格是否可以到达，访问可到达的网格后将记录访问数量的变量加一，
并递归调用访问该网格上下左右的网格，直到访问到终点。

### N-Queens II
[查看代码](src/Solution_52.java)  
使用递归，递归体中使用一个循环将皇后放置在对应的位置，判断当前位置是否符合要求，若符合要求则调用递归放置下一个皇后，若不符合要求则continue进入
下一次循环。当递归调用至所有皇后均被放置后将解决方案的次数自增。

### Median of Two Sorted Arrays
[查看代码](src/Solution_4.java)  
首先判断是否有一个数组为空，如有空数组则返回另一个数组的中位数  
单个有序数组的中位数按数组长度是否为偶数分两种情况讨论  
- 若长度为偶数，则返回中间两个元素的平均值。
- 若长度为奇数，则返回中间元素的值。    

若两个数组均不为空，则按从小到大的顺序将两个数组合并，即依次从两个数组中选择较小的值放入容器中。合并后的数组按总长度是否时偶数进行讨论。  
Tips:较好的时间复杂度可以在放置数组元素时进行判断，
- 如果总长度为奇数则放置到总长度一半时返回当前放置的元素
- 如果总长度为偶数则放置到总长度一半时返回当前元素和下一个要放置的元素的平均数
