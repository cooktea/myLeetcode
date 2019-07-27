import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/26
 * Info    :    特殊的二进制序列
 */

public class Solution_761 {
    public static void main(String[] args) {
        Solution_761 s = new Solution_761();
        s.makeLargestSpecial("11010011101000");
    }

    public String makeLargestSpecial(String S) {
        char[] chars = S.toCharArray();
        int[] heights = new int[chars.length+1];
        heights[0] = 0;
        for (int i=0;i<chars.length;i++){
            if (chars[i] == '1'){
                heights[i+1] = heights[i]+1;
            } else {
                heights[i+1] = heights[i]-1;
            }
        }
        List<Integer> tops = getTops(heights);
        List<Mountain> mountains = getMountains(heights,tops);
        List<Mountain> sortMountains = sortMountains(mountains);
        for (Mountain mountain : mountains){
            System.out.println(mountain.toString());
        }
        System.out.println("---------------------------");
        for (Mountain mountain : sortMountains){
            System.out.println(mountain.toString());
        }
        StringBuilder sb  = new StringBuilder();
        int point = 0;
        for (int i=0;i<mountains.size();i++){
            while (point < mountains.get(i).start){
                sb.append(chars[point]);
                point++;
            }
            sb.append(getMountain(sortMountains.get(i).height));
            point = mountains.get(i).end+1;
        }
        while (point < chars.length){
            sb.append(chars[point]);
            point++;
        }
        System.out.println(sb.toString());
        return null;
    }

    public String getMountain(int height){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<height;i++){
            sb.append("1");
        }
        for (int i=0;i<height;i++){
            sb.append("0");
        }
        return sb.toString();
    }

    public List<Mountain> sortMountains(List<Mountain> mountains){
        List<Mountain> sortMountains = new ArrayList<>();
        for (Mountain mountain : mountains){
            sortMountains.add(mountain);
        }
        for (int i=0;i<sortMountains.size()-1;i++){
            for (int j=i+1;j<sortMountains.size();j++){
                if (sortMountains.get(i).height < sortMountains.get(j).height ){
                    Mountain temp = sortMountains.get(i);
                    sortMountains.set(i,sortMountains.get(j));
                    sortMountains.set(j,temp);
                }
            }
        }
        return sortMountains;
    }

    boolean isInflectionPoint(int point,int[] heights){
        if (heights[point-1] > heights[point] && heights[point+1] > heights[point]){
            return true;
        }
        return false;
    }

    List<Mountain> getMountains(int[] heights,List<Integer> tops){
        List<Mountain> mountains = new LinkedList<>();
        for (Integer top : tops){
            int left = top;
            int right = top;
            Mountain mountain = new Mountain();
            while (heights[left] == heights[right]){
                left--;
                right++;
                if (left == 0 || right == heights.length-1){
                    mountain.start = left;
                    mountain.end = right-1;
                    mountain.height = right-top;
                    break;
                }
                if (isInflectionPoint(left,heights)){
                    mountain.start = left;
                    mountain.end = right-1;
                    mountain.height = right-top;
                    break;
                }
                if (isInflectionPoint(right,heights)){
                    mountain.start = left;
                    mountain.end = right-1;
                    mountain.height = right-top;
                    break;
                }
            }
            mountains.add(mountain);
        }
        return mountains;
    }

    List<Integer> getTops(int[] heights){
        List<Integer> tops = new LinkedList<>();
        for (int i=1;i<heights.length-1;i++){
            if (heights[i-1] < heights[i] && heights[i+1] < heights[i]){
                tops.add(i);
            }
        }
        return tops;
    }

    class Mountain{
        int start;
        int end;
        int height;

        @Override
        public String toString() {
            return "Mountain{" +
                    "start=" + start +
                    ", end=" + end +
                    ", height=" + height +
                    '}';
        }
    }

}
