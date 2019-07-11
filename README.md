# LeetCode 题解

|题目编号|题目名称|链接|解题时间|
|:---:|:---:|:---:|:---:|
|4|[寻找两个有序数组的中位数](#Median-of-Two-Sorted-Arrays)||https://leetcode-cn.com/problems/median-of-two-sorted-arrays/|2019-07-11| 
|52|[N皇后 II](N-Queens-II)|https://leetcode-cn.com/problems/n-queens-ii/|2019--7-12|

           
## 解题思路                                                                   
### Median of Two Sorted Arrays
待完成

### N-Queens II
使用递归，递归体中使用一个循环将皇后放置在对应的位置，判断当前位置是否符合要求，若符合要求则调用递归放置下一个皇后，若不符合要求则continue进入
下一次循环。当递归调用至所有皇后均被放置后将解决方案的次数自增。
   
   
