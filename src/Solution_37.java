
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/17
 * Info    :
 */

//{
//        {'5','1','9','7','4','8','6','3','2'},
//        {'7','8','3','6','5','2','4','1','9'},
//        {'4','2','6','1','3','9','8','7','5'},
//        {'3','5','7','9','8','6','2','4','1'},
//        {'2','6','4','3','1','7','5','9','8'},
//        {'1','9','8','5','2','4','3','6','7'},
//        {'9','7','5','8','6','3','1','2','4'},
//        {'8','3','2','4','9','1','7','5','6'},
//        {'6','4','1','2','7','5','9','8','3'}
//}


public class Solution_37 {
    char[][] board;
    List<int[]> blocks = new LinkedList<>();
    int blocksNum = 0;

    public static void main(String[] args) {
        char[][] board =
                {
                        {'.','.','9','7','4','8','.','.','.'},
                        {'7','.','.','.','.','.','.','.','.'},
                        {'.','2','.','1','.','9','.','.','.'},
                        {'.','.','7','.','.','.','2','4','.'},
                        {'.','6','4','.','1','.','5','9','.'},
                        {'.','9','8','.','.','.','3','.','.'},
                        {'.','.','.','8','.','3','.','2','.'},
                        {'.','.','.','.','.','.','.','.','6'},
                        {'.','.','.','2','7','5','9','.','.'}
                };
        Solution_37 s = new Solution_37();
        s.solveSudoku(board);
        s.printBoard();
    }
    boolean[][] cols = new boolean[9][10];
    boolean[][] rows = new boolean[9][10];
    boolean[][] boxes = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        this.board = board;
        this.blocksNum = getBlocksLocation();
        //todo
//        while (!isFinish()){
//
//        }
    }

    public void clearSqureArea(char[] full,int y,int x){
        int yStart = 0;
        int xStart = 0;
        if(y <= 2){
            yStart = 0;
        } else if (y >= 6){
            yStart = 6;
        } else {
            yStart = 3;
        }
        if(x <= 2){
            xStart = 0;
        } else if (x >= 6){
            xStart = 6;
        } else {
            xStart = 3;
        }
        for (int i=yStart;i<yStart+3;i++){
            for (int j=xStart;j<xStart+3;j++){
                if (board[i][j] != '.'){
                    int number =  (int)board[i][j]-(int)'0';
                    full[number-1] = '0';
                }
            }
        }
    }

    public int getBlocksLocation(){
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if (board[i][j] == '.'){
                    int[] location = new int[2];
                    location[0] = i;
                    location[1] = j;
                    blocks.add(location);
                }
            }
        }
        return blocks.size();
    }

    public void printBoard(){
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}
