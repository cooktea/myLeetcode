import java.util.*;

/**
 * Author  :   ChenKang
 * Time    :   2019/8/1
 * Info    :    破解保险箱
 */

public class Solution_753 {
    int n,k;



    public static void main(String[] args) {
        Solution_753 s = new Solution_753();
        System.out.println(s.crackSafe(2,4));
    }

    public String crackSafe(int n, int k) {
        if (n == 0){
            return "";
        }
        this.n = n;
        this.k = k;

        long start = System.currentTimeMillis();

        System.out.println(System.currentTimeMillis()-start);
        return "";
    }
}
