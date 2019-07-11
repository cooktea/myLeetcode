# LeetCode 题解

|题目编号|题目名称|链接|解题时间|
|:---:|:---:|:---:|:---:|
|4|[寻找两个有序数组的中位数](#Median-of-Two-Sorted-Arrays)|https://leetcode-cn.com/problems/median-of-two-sorted-arrays/|2019-07-11| 
|52|[N皇后 II](#N-Queens-II)|https://leetcode-cn.com/problems/n-queens-ii/|2019--7-12|

           
## 解题思路                                                                   
### Median of Two Sorted Arrays
首先判断是否有一个数组为空，如有空数组则返回另一个数组的中位数  
单个有序数组的中位数按数组长度是否为偶数分两种情况讨论  
- 若长度为偶数，则返回中间两个元素的平均值。
- 若长度为奇数，则返回中间元素的值。    

若两个数组均不为空，则按从小到大的顺序将两个数组合并，即依次从两个数组中选择较小的值放入容器中。合并后的数组按总长度是否时偶数进行讨论。  
Tips:较好的时间复杂度可以在放置数组元素时进行判断，
- 如果总长度为奇数则放置到总长度一半时返回当前放置的元素
- 如果总长度为偶数则放置到总长度一半时返回当前元素和下一个要放置的元素的平均数

### N-Queens II
使用递归，递归体中使用一个循环将皇后放置在对应的位置，判断当前位置是否符合要求，若符合要求则调用递归放置下一个皇后，若不符合要求则continue进入
下一次循环。当递归调用至所有皇后均被放置后将解决方案的次数自增。
   
   
