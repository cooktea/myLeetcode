import javax.annotation.processing.SupportedSourceVersion;
import java.util.*;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/16
 * Info    :    滑动谜题
 */

public class Solution_773 {
    Set<String> paths = new HashSet<String>();
    Queue<boardInfo> boards = new LinkedList<>();

    public static void main(String[] args) {
        int[][] board = {{1,2,3},{4,0,5}};
        Solution_773 s =  new Solution_773();
        int step = s.slidingPuzzle(board);
        System.out.println(step);
    }

    /*
    board信息
     */
    class boardInfo{
        int[][] board;  //board状态
        int step;   //获得当前状态需要移动几步
        int X;  //“0"的X坐标
        int Y;  //”0“的Y坐标

        public boardInfo() {

        }

        public boardInfo(int[][] board, int step) {
            this.board = board;
            this.step = step;
            int[] location = getZeroLocation(board);
            X = location[1];
            Y = location[0];
        }
        /*
        用于空构造器实例化对象后的坐标信息初始化
         */
        public void init(){
            int[] location = getZeroLocation(board);
            X = location[1];
            Y = location[0];
        }

        /**
         * 移动”0“
          * @param direction 移动的方向
         * @param step  移动了几步
         * @return  新的board信息
         */
        public boardInfo move(Direction direction,int step){
            boardInfo bf = new boardInfo();
            bf.step = step;
            int[][] newBoard;
            int temp;
            switch (direction){
                case UP:
                    newBoard = cloneBoard(board);
                    temp = newBoard[Y][X];
                    newBoard[Y][X] = newBoard[Y-1][X];
                    newBoard[Y-1][X] = temp;
                    bf.board = newBoard;
                    break;
                case DOWN:
                    newBoard = cloneBoard(board);
                    temp = newBoard[Y][X];
                    newBoard[Y][X] = newBoard[Y+1][X];
                    newBoard[Y+1][X] = temp;
                    bf.board = newBoard;
                    break;
                case LEFT:
                    if (canMove(Y,X-1)) {
                        newBoard = cloneBoard(board);
                        temp = newBoard[Y][X];
                        newBoard[Y][X] = newBoard[Y][X-1];
                        newBoard[Y][X-1] = temp;
                        bf.board = newBoard;
                    }
                    break;
                case RIGHT:
                    if (canMove(Y,X+1)) {
                        newBoard = cloneBoard(board);
                        temp = newBoard[Y][X];
                        newBoard[Y][X] = newBoard[Y][X+1];
                        newBoard[Y][X+1] = temp;
                        bf.board = newBoard;
                    }
                    break;
            }
            bf.init();
            return bf;
        }

        /**
         * 用于clone一个新的board
         * @param board 需要clone的board
         * @return 新的board
         */
        public int[][] cloneBoard(int[][] board){
            int[][] newBoard = new int[2][3];
            for (int i=0;i<2;i++){
                for (int j=0;j<3;j++){
                    newBoard[i][j] = board[i][j];
                }
            }
            return newBoard;
        }

        /**
         * 获取”0“的坐标
         * @param board 需要获取坐标的board
         * @return 一个长度为2的int数组 nums[0]为Y坐标,nums[1]为X坐标
         */
        public int[] getZeroLocation(int[][] board){
            for (int i=0;i<2;i++){
                for (int j=0;j<3;j++){
                    if (board[i][j] == 0){
                        int[] location = new int[2];
                        location[0] = i;
                        location[1] = j;
                        return location;
                    }
                }
            }
            return null;
        }

    }

    public int slidingPuzzle(int[][] board) {
        boardInfo bf = new boardInfo(board,0);  //初始board信息
        boards.offer(bf);   //将初始状态的board信息添加至队列
        /*
        当队列不为空时，依次取出board信息进心操作
         */
        while (!boards.isEmpty()) {
            bf = boards.poll(); //取出board信息
            /*
            判断board是否符合要求
             */
            if (isFininsh(bf.board)){
                return bf.step; //返回移动的步数
            }
            /*
            判断该board的状态是否已经遇到过
             */
            if (paths.contains(toPath(bf.board))){
                continue;
            }
            paths.add(toPath(bf.board));    //将该board的状态添加至状态集合中
            if (canMove(bf.Y-1,bf.X)){  //”0“能不能上移
                boards.offer(bf.move(Direction.UP,bf.step+1));  //将上移后的board信息添加至队列中
            }
            if (canMove(bf.Y+1,bf.X)){  //”0“能不能下移
                boards.offer(bf.move(Direction.DOWN,bf.step+1));    //将下移后的board信息添加至队列中
            }
            if (canMove(bf.Y,bf.X-1)){  //”0“能不能左移
                boards.offer(bf.move(Direction.LEFT,bf.step+1));    //将左移后的board信息添加至队列中
            }
            if (canMove(bf.Y,bf.X+1)){  //”0“能不能右移
                boards.offer(bf.move(Direction.RIGHT,bf.step+1));   //将右移后的board信息添加至队列中
            }
        }
        return -1;
    }

    /**
     * 判断是否可以将0移动至Y坐标为i，X坐标为j的位置
     * @param i 新的Y坐标
     * @param j 新的X坐标
     * @return 可以或不可以
     */
    public boolean canMove(int i,int j){
        if (i >= 2 || i < 0 || j >= 3|| j <0){
            return false;
        }
        return true;
    }

    /**
     * 获取当前board状态的字符串表示形式
     * @param board 需要获取状态字符串的board
     * @return board的状态字符串
     */
    public String toPath(int[][] board){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<2;i++){
            for (int j=0;j<3;j++){
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    /**
     * 用于调试，生成当前board的状态
     * @param board 需要生成状态的board
     */
    public void printBoard(int[][] board){
        for (int i=0;i<2;i++){
            for (int j=0;j<3;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    /**
     * 判断board是否符合要求
     * @param board 需要判断的board
     * @return  是或否
     */
    public boolean isFininsh(int[][] board){
        if(board[0][0] != 1){
            return false;
        }
        if(board[0][1] != 2){
            return false;
        }
        if(board[0][2] != 3){
            return false;
        }
        if(board[1][0] != 4){
            return false;
        }
        if(board[1][1] != 5){
            return false;
        }
        if(board[1][2] != 0){
            return false;
        }
        return true;
    }

    public enum Direction{
        UP,DOWN,LEFT,RIGHT
    }
}
