import java.util.concurrent.ConcurrentHashMap;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/21
 * Info    :    编辑距离
 */

public class Solution_72 {
    public static void main(String[] args) {
        Solution_72 s = new Solution_72();
        System.out.println(s.minDistance("intention","execution"));
    }

    public int minDistance(String word1, String word2) {
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        /*
        维护一个矩阵用来保存不同操作下需要的步骤
        matrix[a][b]+temp为执行替换到这一步需要的最小步数
        matrix[a-1][b]+1为执行删除
        matrix[a][b-1]+1为执行添加
         */
        int [][] matrix = new int[str1.length+1][str2.length+1];
        /*
        初始化矩阵
         */
        matrix[0][0] = 0;
        for (int i=1;i<str1.length+1;i++){
            matrix[i][0] = i;
        }
        for (int j=1;j<str2.length+1;j++){
            matrix[0][j] = j;
        }
        /*
        遍历矩阵
         */
        for (int i=1;i<str1.length+1;i++) {
            for (int j = 1; j < str2.length + 1; j++) {
                int temp = 0;   //如果两个字符不同，则需要替换，temp为1
                if (str1[i - 1] != str2[j-1]) {
                    temp = 1;
                }
                matrix[i][j] = Math.min(Math.min(matrix[i - 1][j - 1] + temp, matrix[i][j-1]+1), matrix[i - 1][j]+1);   //到达matrix的最小步数
            }
        }
        return matrix[str1.length][str2.length];
    }
}
