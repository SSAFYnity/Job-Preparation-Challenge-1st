import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException{

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int[] time = new int[n + 1];
        int[] cost = new int[n + 1];

        for (int i = 0 ; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
            if (time[i] + i > n) continue;
            dp[time[i] + i] = Math.max(dp[time[i] + i], dp[i] + cost[i]);
        }
        System.out.println(dp[n]);
    }

}
