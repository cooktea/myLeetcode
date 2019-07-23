import java.util.*;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/22
 * Info    :    隔离病毒
 */

public class Solution_749 {
    int height; //世界高度
    int width;  //世界长度
    Coordinate[][] grid;    //存储世界信息
    Set<Coordinate> isVisited;  //存储已经访问过的坐标
    List<List<Coordinate>> virusAreas;  //存储病毒区域

    public static void main(String[] args) {
        Solution_749 s = new Solution_749();
        int[][] grid = {
                {1,1,1,0,1,1,1},
                {1,0,1,0,1,0,1},
                {1,1,1,0,1,0,1}
        };
        s.containVirus(grid);
    }

    public int containVirus(int[][] grid) {
        /*
        初始化信息
         */
        height = grid.length;
        width = grid[0].length;
        this.grid = new Coordinate[height][width];
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                this.grid[i][j] = new Coordinate(j,i,grid[i][j]);
            }
        }

        int count = 0;  //防火墙总数
        /*
        当还有病毒区域时
         */
        while (getVirusAreas()!=0){ //如果病毒区域不为0，则进入循环
            List<Coordinate> virusArea = getMaxVirusArea(); //获取威胁最大的一块病毒区域
            count += addFireWall(virusArea);    //将这一轮建造的防火墙数量添加至总数中
            virusAreas.remove(virusArea);   //移除这块病毒区域
            refreshGrid();  //剩下的病毒区域进行感染
            /*
            判断还有没有能被感染的地方
             */
            if (!hasNoThreatedArea()){  //如果没有，跳出循环
                break;
            }
        }
        System.out.println(count);
        return count;
    }

    /**
     * 判断还有没有能被感染的区域
     * @return  true：有  false：没有
     */
    public boolean hasNoThreatedArea(){
        Set<Coordinate> infectedArea = new HashSet<>();
        for (List<Coordinate> area:virusAreas){
            for (Coordinate c : area){
                if (getThreatAreaNumber(c,infectedArea) != 0){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 刷新地图，进行一轮感染
     */
    public void refreshGrid(){
        for (List<Coordinate> area : virusAreas){
            for (Coordinate c : area){
                if (c.x+1 != width){
                    if (grid[c.y][c.x+1].value == 0){
                        grid[c.y][c.x+1].value = 1;
                    }
                }
                if (c.x-1 != -1){
                    if (grid[c.y][c.x-1].value == 0){
                        grid[c.y][c.x-1].value = 1;
                    }
                }
                if (c.y-1 != -1){
                    if (grid[c.y-1][c.x].value == 0){
                        grid[c.y-1][c.x].value = 1;
                    }
                }
                if (c.y+1 != height){
                    if (grid[c.y+1][c.x].value == 0){
                        grid[c.y+1][c.x].value = 1;
                    }
                }
            }
        }
    }

    /**
     * 建造防火墙
     * @param area  需要建造防火墙的病毒区域
     * @return  防火墙数量
     */
    public int addFireWall(List<Coordinate> area){
        int count = 0;
        Set<Coordinate> infectedArea = new HashSet<>();
        for (Coordinate c:area){
            if (c.x+1 != width){
                if (grid[c.y][c.x+1].value == 0){
                    count++;
                }
            }
            if (c.x-1 != -1){
                if (grid[c.y][c.x-1].value == 0){
                    count++;
                }
            }
            if (c.y-1 != -1){
                if (grid[c.y-1][c.x].value == 0){
                    count++;
                }
            }
            if (c.y+1 != height){
                if (grid[c.y+1][c.x].value == 0){
                    count++;
                }
            }
            c.value = 2;
        }
        return count;
    }

    /**
     * 获取下一轮感染时感染数量最多的病毒区域
     * @return
     */
    public List<Coordinate> getMaxVirusArea(){
        int max = 0;
        List<Coordinate> maxVirusArea = null;
        for (List<Coordinate> virusArea : virusAreas){
            Set<Coordinate> infectedArea = new HashSet<>();
            int count = 0;
            for (Coordinate coordinate : virusArea){
                count += getThreatAreaNumber(coordinate,infectedArea);
            }
            if (max < count){
                max = count;
                maxVirusArea = virusArea;
            }
        }
        return maxVirusArea;
    }

    /**
     * 获取地图上不相连的病毒区域
     * @return
     */
    public int getVirusAreas(){
        virusAreas = new ArrayList<>();
        isVisited = new HashSet<>();
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                List<Coordinate> virusArea = new ArrayList<>();
                int number = getVirusNumber(j,i,virusArea);
                if (number != 0){
                    virusAreas.add(virusArea);
                }
            }
        }
        return virusAreas.size();
    }

    /**
     * 获取某个病毒坐标能感染的数量
     * @param c 坐标
     * @param infectedArea  已经被感染的坐标集合
     * @return  能感染的数量
     */
    public int getThreatAreaNumber(Coordinate c,Set<Coordinate> infectedArea){
        int count = 0;
        if (c.y+1 != height){
            if (grid[c.y+1][c.x].value == 0 && !infectedArea.contains(grid[c.y+1][c.x])){
                infectedArea.add(grid[c.y+1][c.x]);
                count++;
            }
        }
        if (c.y-1 != -1){
            if (grid[c.y-1][c.x].value == 0 && !infectedArea.contains(grid[c.y-1][c.x])){
                infectedArea.add(grid[c.y-1][c.x]);
                count++;
            }
        }
        if (c.x+1 != width){
            if (grid[c.y][c.x+1].value == 0 && !infectedArea.contains(grid[c.y][c.x+1])){
                infectedArea.add(grid[c.y][c.x+1]);
                count++;
            }
        }
        if (c.x-1 != -1){
            if (grid[c.y][c.x-1].value == 0 && !infectedArea.contains(grid[c.y][c.x-1])){
                infectedArea.add(grid[c.y][c.x-1]);
                count++;
            }
        }
        return count;
    }

    /**
     * 获取一块病毒区域
     * @param x 横坐标
     * @param y 纵坐标
     * @param virusArea 病毒区域
     * @return  病毒区域的病毒数量
     */
    public int getVirusNumber(int x,int y,List<Coordinate> virusArea){
        if (x<0 || y<0 || x >= width || y >= height){
            return 0;
        }
        if (isVisited.contains(grid[y][x])){
            return 0;
        }
        isVisited.add(grid[y][x]);
        if (grid[y][x].value == 1){
            virusArea.add(grid[y][x]);
            return 1 +
                    getVirusNumber(x,y-1,virusArea) +
                    getVirusNumber(x,y+1,virusArea) +
                    getVirusNumber(x-1,y,virusArea) +
                    getVirusNumber(x+1,y,virusArea);
        }
        else {
            return 0;
        }
    }

    /*
    坐标类
     */
    class Coordinate{
        int x;
        int y;
        int value;

        public Coordinate(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

    }
}
