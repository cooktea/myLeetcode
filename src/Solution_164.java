/**
 * Author  :   ChenKang
 * Time    :   2019/7/25
 * Info    :    最大间距
 */

public class Solution_164 {
     public static void main(String[] args) {
         int[] nums = {1,1,1,1,1,1,1,1,1,1,1000};
         Solution_164 s = new Solution_164();
         System.out.println(s.maximumGap(nums));
     }

    public int maximumGap(int[] nums) {
        int size = nums.length; //数组长度
        if(size <= 1){  //如果数组长度小于2，返回0
            return 0;
        }
        /*
        获得最大值和最小值
         */
        int max = nums[0];
        int min = nums[0];
        for (int i=0;i<size;i++){
            if (nums[i] < min){
                min = nums[i];
            }
            if (nums[i] > max){
                max = nums[i];
            }
        }
        if(max == min){ //如果最大值和最小值相同则返回0
            return 0;
        }

        int bucketNumber = (int)Math.ceil((double)(max-min)/(size-1));  //获得桶大小
        Bucket[] buckets = new Bucket[size];    //构造size个桶
        for (int i=0;i<size;i++){   //遍历数组
            int idx = (int)((nums[i]-min)/bucketNumber);    //获得该数组元素应该放入的桶的序号
            if (buckets[idx] == null){  //如果桶不存在
                buckets[idx] = new Bucket();    //新建一个桶
            }
            buckets[idx].setNumber(nums[i]);    //将数组元素放入桶内
        }
        int result = buckets[0].max - buckets[0].min;   //初始化最大间距
        int point = buckets[0].max; //当前桶的最大值
        for(int i=1;i<size;i++){
            if(buckets[i] == null){
                continue;
            }
            if(result < buckets[i].min-point){  //如果最大间距小于当前桶的最小值和前一个桶的最大值
                result = buckets[i].min-point;  //赋值
            }
            point = buckets[i].max; //将当前桶的最大值给point
        }
        return result;
    }

    /*
    桶类
     */
    class Bucket{
        int max;    //最大值
        int min;    //最小值
        boolean isEmpty;    //该桶是不是空的
        public Bucket(){
            this.isEmpty = true;
            this.max = Integer.MIN_VALUE;
            this.min = Integer.MAX_VALUE;
        }
        public void setNumber(int num){
            if (isEmpty){
                max = num;
                min = num;
                isEmpty = false;
            }
            if (max < num){
                max = num;
            }
            if (min > num){
                min = num;
            }
        }
    }

}
