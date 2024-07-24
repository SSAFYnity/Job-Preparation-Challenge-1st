import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[][] map;
    static int[] curIdx;

    static TreeSet<Number> treeSet;

    static class Number {
        int location, number;
        Number(int _location, int _number) {
            location = _location; number = _number;
        }
    }

    static void input() throws IOException{

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        treeSet = new TreeSet<>((o1, o2) -> o2.number - o1.number);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        curIdx = new int[n];
        Arrays.fill(curIdx, n - 1);
    }

    static void pro() {

        for (int i = 0; i < n; i++) {
            treeSet.add(new Number(i, map[n - 1][i]));
        }

        while (--n != 0) {
            Number curNumber = treeSet.pollFirst();
            int nextNumber = --curIdx[curNumber.location];
            treeSet.add(new Number(curNumber.location, map[nextNumber][curNumber.location]));
        }
        System.out.println(treeSet.pollFirst().number);
    }

    public static void main(String[] args) throws IOException{
        input();
        pro();
    }
}