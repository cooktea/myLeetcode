import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    public static void main(String[] args) {
        System.out.println(""+new Solution_4().findMedianSortedArrays(new int[]{1,3},new int[]{2}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length == 0){
            if(nums2.length % 2 == 0){
                return (nums2[nums2.length/2-1]+nums2[nums2.length/2])/2.0;
            } else {
                return nums2[(int)Math.floor(nums2.length/2)]*1.0;
            }
        } else if(nums2.length == 0){
            if(nums1.length % 2 == 0){
                return (nums1[nums1.length/2-1]+nums1[nums1.length/2])/2.0;
            } else {
                return nums1[(int)Math.floor(nums1.length/2)]*1.0;
            }
        } else {
            int sum = nums1.length+nums2.length;
            int i = 0;
            int j = 0;
            if(sum % 2 == 0){
                List<Integer> temp = new ArrayList<>();
                while (temp.size() != sum/2+1){
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
                return (temp.get(temp.size()-1)+temp.get(temp.size()-2))/2.0;
            } else {
                List<Integer> temp = new ArrayList<>();
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
                return temp.get(temp.size()-1)/1.0;
            }
        }
    }

}

