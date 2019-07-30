import java.util.TreeMap;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/30
 * Info    :    我的日程安排表 III
 */

public class MyCalendarThree {
    public static void main(String[] args) {
        MyCalendarThree calendarThree = new MyCalendarThree();
        System.out.println(calendarThree.book(10,20));
        System.out.println(calendarThree.book(50,60));
        System.out.println(calendarThree.book(10,40));
        System.out.println(calendarThree.book(5,15));
        System.out.println(calendarThree.book(5,10));
        System.out.println(calendarThree.book(25,55));
    }

    TreeMap<Integer,Integer> nodeMap = new TreeMap<>(); //报错节点的map

    public MyCalendarThree() {

    }

    public int book(int start, int end) {
        if (nodeMap.get(start) == null){    //如果没有start节点
            nodeMap.put(start,1);   //新建start节点
        } else {    //如果有
            nodeMap.put(start,nodeMap.get(start).intValue()+1); //将start节点的值+1
        }
        if (nodeMap.get(end) == null){
            nodeMap.put(end,-1);
        } else {
            nodeMap.put(end,nodeMap.get(end).intValue()-1);
        }
        int max = 0;    //最大的预定数
        int temp = 0;   //某一时刻的预定数
        for (Integer keys :nodeMap.keySet()){   //遍历map
            temp += nodeMap.get(keys).intValue();
            max = Math.max(max,temp);
        }
        return max;
    }
}
