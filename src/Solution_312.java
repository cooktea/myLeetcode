import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/19
 * Info    :    戳气球
 */

public class Solution_312 {
    int[] balloons;
    public static void main(String[] args) {
        Solution_312 s = new Solution_312();
        int[] balloons = {3,1,5,8};
        System.out.println(s.maxCoins(balloons));
    }

    public int maxCoins(int[] nums) {
        balloons = new int[nums.length+2];  //保存添加虚拟气球后的气球
        System.arraycopy(nums,0,balloons,1,nums.length);
        balloons[0] = 1;
        balloons[balloons.length-1] = 1;

        /*
        dp[i][j]表示第i个和第j个之间能获得的最大数，i和j不能戳
         */
        int dp[][] = new int[balloons.length][balloons.length];
        /*
        初始化dp
         */
        for (int i = 0;i<dp.length;i++){
            Arrays.fill(dp[i],0);
        }
        /*
        从长度为2的子范围开始，直到长度为总长度为止
         */
        for (int len = 2;len<balloons.length;len++){
            /*
            从第一个虚拟气球开始
             */
            for (int start = 0;start<balloons.length-len;start++){
                /*
                k表示最后一个戳的地球位置
                 */
                for (int k=start+1;k<start+len;k++){
                    dp[start][start+len] = Math.max(dp[start][start+len],
                            dp[start][k]+dp[k][start+len]+balloons[start]*balloons[k]*balloons[start+len]);
                }
            }
        }
        return dp[0][balloons.length-1];
    }
}
