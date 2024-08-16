package 더하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 더하기_이승헌 {

    static int[][] dp = new int[4][10_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        dp[2][2] = 1;
        dp[2][3] = 1;
        dp[3][3] = 1;

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(solve(N, 2) + solve(N, 3) + 1).append('\n');
        }
        System.out.println(sb);
    }

    private static int solve(int N, int idx) {
        if (idx == 1) {
            return 1;
        }else if (dp[idx][N] != 0 || N < 4) {
            return dp[idx][N];
        }

        int sum = 1;
        for (int i = 2; i <= idx; i++) {
            sum += solve(N - idx, i);
        }
        return dp[idx][N] = sum;
    }
}
