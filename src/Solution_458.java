/**
 * Author  :   ChenKang
 * Time    :   2019/7/27
 * Info    :    可怜的小猪
 */

public class Solution_458 {
    public static void main(String[] args) {
        Solution_458 s = new Solution_458();
        System.out.println(s.poorPigs(17,60,60));
    }
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1){  //只有一桶水，不用区分
            return 0;
        }
        int drinkTimes = (int) minutesToTest/minutesToDie + 1;  //获取一只猪可以在规定时间内可以区分的数量
        int pigNumber = 1;  //猪的数量
        long temp = 1*drinkTimes;   //当前猪数量下最多可以区分的数量
        while (temp < buckets){
            pigNumber++;
            temp *= drinkTimes;
        }
        return pigNumber;
    }
}
