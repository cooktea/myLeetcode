import java.util.*;

/**
 * Author  :   ChenKang
 * Time    :   2019/8/3
 * Info    :
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cycle = scanner.nextInt();
        for (int i=0;i<cycle;i++){
            int max = Integer.MIN_VALUE;
            int s_max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int n = scanner.nextInt();
            if (n<=2){
                System.out.println("NO");
            }
            for (int j=0;j<n;j++){
                int num = scanner.nextInt();
                if (num > s_max){
                    if (num > max){
                        s_max = max;
                        max = num;
                    } else {
                        s_max = num;
                    }
                }
                if (num<min){
                    min = num;
                }
            }
            System.out.println(max);
            System.out.println(s_max);
            System.out.println(min);
            if (max>min+s_max){
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
