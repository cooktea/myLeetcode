import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Author  :   ChenKang
 * Time    :   2019/8/5
 * Info    :    移除盒子
 */

class Solution_546 {
    int[] boxes;
    List<Group> groups = new LinkedList<>();
    public static void main(String[] args) {
        Solution_546 s = new Solution_546();
        int[] boxes = {2, 5, 10, 9, 4, 8, 6, 9, 9, 1};
        System.out.println(s.removeBoxes(boxes));
    }
    public int removeBoxes(int[] boxes) {
        this.boxes = boxes;
        int tempColor = boxes[0];
        int count = 0;
        for (int i=0;i<boxes.length;i++){
            if (boxes[i] == tempColor){
                count++;
            } else {
                groups.add(new Group(count,tempColor));
                tempColor = boxes[i];
                count = 1;
            }
        }
        groups.add(new Group(count,tempColor));
        int sum = 0;
        while (groups.isEmpty() == false){
            int max = Integer.MIN_VALUE;
            int maxGroupIndex = 0;
            Group maxGroup = null;
            for (int i=0;i<groups.size();i++){
                if (max < getSum(i)){
                    max = getSum(i);
                    maxGroupIndex = i;
                    maxGroup = groups.get(i);
                }
            }
            if (maxGroupIndex != 0 && maxGroupIndex != groups.size()-1) {
                    if (groups.get(maxGroupIndex+1).color == groups.get(maxGroupIndex-1).color){
                        groups.get(maxGroupIndex+1).num+=groups.get(maxGroupIndex-1).num;
                        groups.remove(maxGroupIndex-1);
                    }
            }
            System.out.println(maxGroup);
            sum += maxGroup.num*maxGroup.num;
            groups.remove(maxGroup);
        }
        return sum;
    }

    public int getSum(int i){
        int count = groups.get(i).num*groups.get(i).num;
        for (int k=0;k<groups.size();k++){
            if (k == i){
                continue;
            }
            count += groups.get(k).num*groups.get(k).num;
        }
        if (i != 0 && i != groups.size()-1){
            if (groups.get(i+1).color == groups.get(i-1).color){
                count-=groups.get(i+1).num*groups.get(i+1).num;
                count-=groups.get(i-1).num*groups.get(i-1).num;
                count+=(groups.get(i+1).num+groups.get(i-1).num)*(groups.get(i+1).num+groups.get(i-1).num);
            }
        }
        return count;
    }

}

class Group{
    int num;
    int color;

    public Group(int num, int color) {
        this.num = num;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Group{" +
                "num=" + num +
                ", color=" + color +
                '}';
    }
}