import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static int n, house;
    static LinkedList<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        house = 0;
        arr = new int[n][n];
        result = new LinkedList<>();
        for (int i = 0; i<n; i++) {
            String str = br.readLine();
            for (int j = 0; j<n; j++) {
                arr[i][j] = str.charAt(j)-'0';
            }
        }
        int count = 0;
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                if (dfs(i, j)) {
                    count ++;
                    result.add(house);
                    house = 0;
                }
            }
        }

        System.out.println(count);
        Collections.sort(result);
        for (int i = 0; i<result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
    public static boolean dfs(int y, int x) {
        if (y<0 || x<0 || y>=n || x>=n) {
            return false;
        }
        if (arr[y][x]==1) {
            house++;
            arr[y][x] = 0;
            dfs(y-1, x);
            dfs(y, x-1);
            dfs(y+1, x);
            dfs(y, x+1);
            return true;
        }
        return false;
    }
}