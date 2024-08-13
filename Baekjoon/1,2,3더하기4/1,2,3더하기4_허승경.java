import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String [] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int [][] dp = new int[10001][4];

        // 합이 3이하인 n은 초기값 1 (n = 1, 2, 3)
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for(int i = 4; i < 10001; i++){
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }

        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            int n = Integer.parseInt(br.readLine());

            int res = dp[n][1] + dp[n][2] + dp[n][3];
            sb.append(res+"\n");
        }

        System.out.print(sb);

    }
}
