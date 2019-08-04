import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer,Integer> scoreNum = new TreeMap<Integer,Integer>();
        int n;
        n = scanner.nextInt();
        int[] scores = new int[n];
        for (int i=0;i<n;i++){
            scores[i] = scanner.nextInt();
        }
        for (int i=0;i<n;i++){
            if (scoreNum.get(scores[i]) == null){
                scoreNum.put(scores[i],1);
            } else {
                int num = scoreNum.get(scores[i])+1;
                scoreNum.put(scores[i],num);
            }
        }
        int q = scanner.nextInt();
        for (int i=0;i<q;i++){
            int x = scanner.nextInt()-1;
            int score = scores[x];
            int count = 0;
            for (Integer integer : scoreNum.keySet()){
                if (integer <= score){
                    count += scoreNum.get(integer);
                }
            }
            double res = 100.0*(count-1)/n;
            System.out.printf("%.6f\n",res);
        }
    }

}

