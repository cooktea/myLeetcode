import java.util.Collection;
import java.util.Collections;

/**
 * Author  :   ChenKang
 * Time    :   2019/8/2
 * Info    :    黑板异或游戏
 */

public class Solution_810 {
    int[] nums;

    public static void main(String[] args) {

    }

    public boolean xorGame(int[] nums) {
        int xor = 0;
        for (int num : nums){
            xor ^= num;
        }
        if (xor == 0){
            return true;
        } else {
            if (nums.length%2 == 0){
                return true;
            } else {
                return false;
            }
        }
    }
}
