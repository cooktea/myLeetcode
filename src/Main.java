import java.sql.CallableStatement;
import java.util.*;

/**
 * Author  :   ChenKang
 * Time    :   2019/8/3
 * Info    :
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n,m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        String str = scanner.nextLine();
        char[][] map = new char[n][];
        for (int i=0;i<n;i++){
            String temp = scanner.nextLine();
            map[i] = temp.toCharArray();
        }
        Coordinate box = getStrat(map);
        Coordinate end = getEnd(map);
        Coordinate start = getPerson(map);
        int count = 0;
        Set<Coordinate> needVisit = new HashSet<>();
        Set<Coordinate> nextVisit = new HashSet<>();
        Set<Coordinate> isVisit = new HashSet<>();
        boolean isArrive = false;
        needVisit.add(start);
        while (needVisit.size()!=0){
            Iterator iterator = needVisit.iterator();
            while (iterator.hasNext()){
                Coordinate coordinate = (Coordinate) iterator.next();
                if (coordinate.x!=0 && coordinate.x!=m-1){
                    if (map[coordinate.y][coordinate.x+1]!='#' && map[coordinate.y][coordinate.x-1]!='#'){
                        Coordinate left = new Coordinate(coordinate.x-1,coordinate.y);
                        Coordinate right = new Coordinate(coordinate.x+1,coordinate.y);
                        if (!isVisit.contains(left)){
                            nextVisit.add(left);
                        }
                        if (!isVisit.contains(right)){
                            nextVisit.add(right);
                        }
                    }
                }
                if (coordinate.y!=0 && coordinate.y!=n-1){
                    if (map[coordinate.y+1][coordinate.x]!='#' && map[coordinate.y-1][coordinate.x]!='#'){
                        Coordinate down = new Coordinate(coordinate.x,coordinate.y+1);
                        Coordinate up = new Coordinate(coordinate.x,coordinate.y-1);
                        if (!isVisit.contains(down)){
                            nextVisit.add(down);
                        }
                        if (!isVisit.contains(up)){
                            nextVisit.add(up);
                        }
                    }
                }
                isVisit.add(coordinate);
            }
            if (isVisit.contains(end)){
                System.out.println(count);
                isArrive = true;
                break;
            }
            count++;
            needVisit = nextVisit;
            nextVisit = new HashSet<>();
        }
        if (!isArrive){
            System.out.println(-1);
        }
    }
    static Coordinate getEnd(char[][] map){
        Coordinate coordinate = null;
        out:for (int i=0;i<map.length;i++){
            for (int j=0;j<map[0].length;j++){
                if (map[i][j] == 'E'){
                    coordinate = new Coordinate(j,i);
                    break out;
                }
            }
        }
        return coordinate;
    }

    static Coordinate getPerson(char[][] map){
        Coordinate coordinate = null;
        out:for (int i=0;i<map.length;i++){
            for (int j=0;j<map[0].length;j++){
                if (map[i][j] == 'S'){
                    coordinate = new Coordinate(j,i);
                    break out;
                }
            }
        }
        return coordinate;
    }


    static Coordinate getStrat(char[][] map){
        Coordinate coordinate = null;
        out:for (int i=0;i<map.length;i++){
            for (int j=0;j<map[0].length;j++){
                if (map[i][j] == '0'){
                    coordinate = new Coordinate(j,i);
                    break out;
                }
            }
        }
        return coordinate;
    }

    static class Coordinate{
        int x;
        int y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return y*100+x;
        }

        @Override
        public boolean equals(Object obj) {
            obj = (Coordinate)obj;
            if (((Coordinate) obj).x == this.x && ((Coordinate) obj).y == this.y){
                return true;
            }
            return false;
        }
    }


}
