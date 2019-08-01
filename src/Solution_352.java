import java.util.*;

/**
 * Author  :   ChenKang
 * Time    :   2019/8/2
 * Info    :    将数据流变为多个不相交区间
 */

public class Solution_352 {

    public static void main(String[] args) {
        Solution_352 s = new Solution_352();
    }

    Intervals intervals = new Intervals();

    public Solution_352() {

    }

    /**
     * add a number
     * @param val   number
     */
    public void addNum(int val) {
        intervals.add(val);
    }

    /**
     * get intervals
     * @return  intervals
     */
    public int[][] getIntervals() {
        int[][] theIntervals = new int[intervals.starts.size()][2];
        int count = 0;
        for (Integer integer : intervals.starts.keySet()){
            theIntervals[count][0] = integer;
            theIntervals[count][1] = intervals.starts.get(integer);
            count++;
        }
        return theIntervals;
    }
}

/*
The package of intervals
 */
class Intervals{
    Map<Integer,Integer> starts = new TreeMap<>();
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    public void add(int num){
        if (max < num){
            max = num;
        }
        if (min > num){
            min = num;
        }
        if (starts.size() == 0){    //is the first add?
            starts.put(num,num);
            return;
        }
        if (starts.get(num) != null){   //already have the key
            return;
        }
        int previous;   //the previous interval's start number
        for (previous=num;previous>=min;previous--){    //get the previous number
            if (starts.get(previous) != null){
                break;
            }
        }
        int next;   //the next interval's start number
        for (next=num;next<=max;next++){    //get the next number
            if (starts.get(next) != null){
                break;
            }
        }
        if (num == min){    //if the num is the minimum interval's start
            if (num == next-1){ //next interval is near num
                starts.put(num,starts.get(next));   //set next's start
                starts.remove(next);    //remove next interval
            } else {
                starts.put(num,num);    //add an interval
            }
        } else if(num == max){    //if the num is the maximum interval's start;
            if (starts.get(previous).intValue() == num-1){  //if the prebvious's end is near num
                starts.remove(previous);    //remove previous
                starts.put(previous,num);   //change previous's end to num
            } else if (starts.get(previous) == num){    //if the num is the previous's end,then nothing to do

            } else {
                starts.put(num,num);    //add an interval
            }
        } else {
            if (num <= starts.get(previous)){   //if the number is in the privious interval
                return;
            }else if (starts.get(previous).intValue() == next-2){   //if add the num,previous and next could be merged
                starts.remove(previous);
                starts.put(previous,starts.get(next));
                starts.remove(next);
            } else {
                if (starts.get(previous).intValue() == num-1){  //if the prebvious's end is near num
                    starts.remove(previous);    //remove previous
                    starts.put(previous,num);   //change previous's end to num
                } else if (num == next-1){ //next interval is near num
                    starts.put(num,starts.get(next));   //set next's start
                    starts.remove(next);    //remove next interval
                } else {
                    starts.put(num,num);    //add an interval
                }
            }
        }
    }

}
