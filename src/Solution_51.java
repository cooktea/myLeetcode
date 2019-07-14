import java.util.ArrayList;
import java.util.List;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/14
 * Info    :    N皇后
 */

public class Solution_51 {
    int[] place;
    List<List<String>> paths = new ArrayList<>();
    int n = 0;

    public static void main(String[] args) {
        System.out.println(new Solution_51().solveNQueens(0));
    }

    public List<List<String>> solveNQueens(int n) {
        if (n<=0){
            List<String> p = new ArrayList<>();
            paths.add(p);
            return paths;
        }
        this.n = n;
        place = new int[n];
        goToNextGrid(0);
        return paths;
    }

    private void goToNextGrid(int j){

        if (j == n){
            List<String> temp = new ArrayList<>();
            /*
            获取当前棋盘状态
             */
            for (int t=0;t<n;t++){
                StringBuilder s = new StringBuilder();
                for (int k = 0;k<n;k++){
                    if (k == place[t]){
                        s.append("Q");
                    } else {
                        s.append(".");
                    }
                }
                temp.add(s.toString());
            }
            paths.add(temp);
            return;
        }
        for (int i=0;i<n;i++){
            place[j] = i;
            if (canQueenInGrid(j)){
                goToNextGrid(j + 1);
            }
        }
    }

    private boolean canQueenInGrid(int i){
        for (int j=0;j<i;j++){
            if (place[j] == place[i]){
                return false;
            }
            if (Math.abs(place[i]-place[j]) == i-j){
                return false;
            }
        }
        return true;
    }



}
