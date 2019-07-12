/**
 * Author  :   ChenKang
 * Time    :   2019/7/12
 * Info    :   不同路径 III
 */

public class Solution_980 {
    int sumGird = 0;    //总可到达方块数
    int length = 0; //长度
    int width = 0;  //宽度
    int sumPath = 0;    //路径总数
    Coordinate start = new Coordinate();    //起始坐标
    Coordinate end = new Coordinate();  //终点坐标
    int [][] grid;  //二维网格
    char[] isVisit; //网格遍历情况

    public static void main(String[] args) {
        int[][] test = {
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,2}
        };
        long mem = Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();
        for(int i=0;i<10;i++){
            Solution_980 t = new Solution_980();
            t.uniquePathsIII(test);
            System.out.println((mem-Runtime.getRuntime().freeMemory()));
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println((mem-Runtime.getRuntime().freeMemory()));


    }

    /**
     * @param grid  二维网格
     * @return  路径数
     */
    public int uniquePathsIII(int[][] grid) {
        /*
        初始化
         */
        this.grid = grid;
        getGridInfo();
        isVisit = new char[width*length];
        for (int i=0;i<isVisit.length;i++){
            isVisit[i] = 0;
        }
        /*
        访问起点
         */
        goToNext(isVisit,new Coordinate(start.getX(),start.getY()),0);
        return this.sumPath;
    }

    /**
     * 访问下一个网格
     * @param visit 网格访问情况
     * @param c 网格坐标
     * @param visitNum  已经访问的网格数量
     */
    public void goToNext(char[] visit,Coordinate c,int visitNum){
        /*
        判断访问的是不是终点
         */
        if (c.getX() == end.getX() && c.getY() == end.getY()){
            /*
            判断是否所有可到达网格均被访问过
             */
            if (visitNum == sumGird+1){
//                System.out.println(pathNum);
                sumPath ++;
            }
            return;
        }
        if(isCoordinateCanVisit(c,visit)){  //判断当前访问的网格是否可以到达
            /*
             *  可以到达
             */
            visit[c.getY()*width+c.getX()] = 1; //记录当前网格已经被访问
            visitNum++; //访问网格的数量加一
            goToNext(visit.clone(),c.changeCoor(1),visitNum);   //访问上方的网格
            goToNext(visit.clone(),c.changeCoor(2),visitNum);   //访问下方的网格
            goToNext(visit.clone(),c.changeCoor(3),visitNum);   //访问左方的网格
            goToNext(visit.clone(),c.changeCoor(4),visitNum);   //访问右方的网格
        }
        return;
    }

    /**
     * 判断网格是否可以到达
     * @param c 网格坐标
     * @param visit 访问情况
     * @return  是否可以到达
     */
    public boolean isCoordinateCanVisit(Coordinate c,char[] visit){
        /*
        网格是否超出边界
         */
        if (c.getX() < 0 || c.getX() >= width){
            return false;
        }
        if (c.getY() < 0 || c.getY() >= length){
            return false;
        }
        /*
        判断网格是不是不可访问的类型
         */
        if (grid[c.getY()][c.getX()] == -1){
            return false;
        }
        /*
        判断网格是否已经被访问过
         */
        if(visit[c.getY()*width+c.getX()] == 1){
            return false;
        }
        return true;
    }

    /**
     * 初始化信息
     */
    public void getGridInfo(){
        length = grid.length;
        width = grid[0].length;
        for(int i=0;i<grid.length;i++){
            for (int j=0;j<width;j++){
                switch (grid[i][j]){
                    case 2 :
                        end.setX(j);
                        end.setY(i);
                        break;
                    case 1 :
                        start.setX(j);
                        start.setY(i);
                        break;
                    case 0 :
                        sumGird++;
                        break;
                }
            }
        }
    }

    /**
     * 坐标类
     */
    class Coordinate implements Cloneable{
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate(){

        }

        /**
         * 改变坐标
         * @param dire  坐标方向
         *              1：向上
         *              2：向下
         *              3：向左
         *              4：向右
         */
        public Coordinate changeCoor(int dire){
            Coordinate newCoor;
            try {
                newCoor = (Coordinate) this.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
            switch (dire){
                /*
                向上
                 */
                case 1 :
                    newCoor.y--;
                    break;
                 /*
                 向下
                  */
                case 2 :
                    newCoor.y++;
                    break;
                /*
                向左
                 */
                case 3 :
                    newCoor.x--;
                    break;
                /*
                向右
                 */
                case 4 :
                    newCoor.x++;
                    break;
            }
            return newCoor;
        }


        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Coordinate(this.x,this.y);
        }

        @Override
        public String toString() {
            return "coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
