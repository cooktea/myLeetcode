
/**
 * Author  :   ChenKang
 * Time    :   2019/7/12
 * Info    :   解决N皇后问题
 */

public class Solution_52 {
    int max = 0;
    int sum = 0;
    int[] place;    //保存皇后的位置信息，将第j个皇后放置在第j行

    public static void main(String[] args) {
        System.out.println(new Solution_52().totalNQueens(4));
    }

    /**
     * N皇后解决的入口方法
     * @param n 皇后数量
     * @return 解决方案的数量
     */

    public int totalNQueens(int n) {
        if(n == 0){
            return 1;
        }
        if(n < 0){
            return 0;
        }

        place = new int[n]; //初始化place
        max = n;
        place(0);   //放置第一个皇后
        return sum;
    }

    /**
     * 放置皇后
     * @param j 第j个皇后
     */
    public void place(int j){
        /*
            判断是否所有皇后都已经被放置
         */
        if(j == max){
            sum++;
            for (int t = 0;t<place.length;t++){
                System.out.print(place[t] + " ");
            }
            System.out.println(" ");
            return;
        }
        /*
            循环将第j个皇后放置在第i个位置
         */
        for(int i=0;i<max;i++){
            place[j] = i;   //将第j个皇后放置在第i个位置
            if(!isVaild(j)){
                continue;
            } else {
                place(j+1); //放置下一个皇后
            }
        }
    }

    /**
     * 第j个皇后放置的位置是否符合要求
     * @param 第j个皇后放置后判断
     * @return 是否符合要求
     */
    public boolean isVaild(int j){
        /*
            循环判断前j个皇后和第j个皇后的位置关系
         */
        for(int i=0;i<j;i++){
            /*
                两个皇后在同一列
             */
            if(place[i] == place[j]){
                return false;
            }
            /*
                两个皇后在同一个对角线
             */
            if(Math.abs(place[i]-place[j]) == j-i){
                return false;
            }
        }
        return true;
    }
}
