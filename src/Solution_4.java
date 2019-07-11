import java.util.ArrayList;
import java.util.List;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/12
 * Info    :   寻找两个有序数组的中位数
 */

class Solution_4 {
    /**
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return  数组1和数组2的中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*
            如果数组1为空
         */
        if(nums1.length == 0){
            /*
                数组2的长度为偶数
             */
            if(nums2.length % 2 == 0){
                return (nums2[nums2.length/2-1]+nums2[nums2.length/2])/2.0;
            }
            /*
                数组2的长度为奇数
             */
            else {
                return nums2[(int)Math.floor(nums2.length/2)]*1.0;
            }

        }
        /*
            如果数组2为空
         */
        else if(nums2.length == 0){
            /*
                数组1的长度为偶数
             */
            if(nums1.length % 2 == 0){
                return (nums1[nums1.length/2-1]+nums1[nums1.length/2])/2.0;
            }
            /*
                数组1的长度为奇数
             */
            else {
                return nums1[(int)Math.floor(nums1.length/2)]*1.0;
            }
        }
        /*
            数组1和数组2的均不为空
         */
        else {
            int sum = nums1.length+nums2.length;    //数组1和数组2的总长度
            int i = 0;  //指向数组1的当前访问位置
            int j = 0;  //指向数组2的当前访问位置
            /*
                总长度为偶数
             */
            if(sum % 2 == 0){
                List<Integer> temp = new ArrayList<>(); //临时存放已经遍历的数组元素
                /*
                    按顺序将总长度一半加一长度的数组元素放置在临时容器中
                 */
                while (temp.size() != sum/2+1){
                    if(i == nums1.length){  //如果数组1已经全被放置进临时容器中
                        temp.add(nums2[j]);
                        j++;
                        continue;
                    }
                    if(j == nums2.length){  //如果数组2已经全被放置进临时容器中
                        temp.add(nums1[i]);
                        i++;
                        continue;
                    }
                    if(nums1[i] > nums2[j]){    //如果当前访问的数组1元素大于数组2元素
                        temp.add(nums2[j]); //将数组2当前的元素放置于临时容器中
                        j++;    //指针自增1
                    } else {
                        temp.add(nums1[i]); //将数组1当前的元素放置于临时容器中
                        i++;
                    }
                }
                System.out.println(temp.toString());
                return (temp.get(temp.size()-1)+temp.get(temp.size()-2))/2.0;   //返回临时容器最后两个数的平均值
            }
            /*
                总长度为奇数
             */
            else {
                List<Integer> temp = new ArrayList<>();
                /*
                    按顺序将总长度中间的数组元素放置在临时容器中
                 */
                while (temp.size() != (int)Math.floor(sum/2)+1){
                    if(i == nums1.length){
                        temp.add(nums2[j]);
                        j++;
                        continue;
                    }
                    if(j == nums2.length){
                        temp.add(nums1[i]);
                        i++;
                        continue;
                    }
                    if(nums1[i] > nums2[j]){
                        temp.add(nums2[j]);
                        j++;
                    } else {
                        temp.add(nums1[i]);
                        i++;
                    }
                }
                System.out.println(temp.toString());
                return temp.get(temp.size()-1)/1.0; //返回临时容器中的最后一个数
            }
        }
    }
}