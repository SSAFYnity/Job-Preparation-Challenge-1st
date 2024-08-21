package 퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사_이승헌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 2][2];

        for (int idx = 1; idx <= N; idx++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[idx][0] = Integer.parseInt(st.nextToken());
            map[idx][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve(map));
    }

    private static int solve(int[][] map) {
        int[] dp = new int[map.length];

        for (int idx = 1; idx < map.length; idx++) {

            if (dp[idx] < dp[idx - 1]) {
                dp[idx] = dp[idx - 1];
            }
            if (map[idx][0] + idx < map.length && dp[map[idx][0] + idx] < dp[idx] + map[idx][1]) {
                dp[map[idx][0] + idx] = dp[idx] + map[idx][1];
            }
        }
        return dp[map.length - 1];
    }
}
