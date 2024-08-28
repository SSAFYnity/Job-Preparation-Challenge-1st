import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        long[] dp = new long[1000001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        long div = 1000000009;
        for(int i = 4; i < 1000001; i++){
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % div;
        }

        for(int tc = 0; tc < t; tc++){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n] +"\n");
        }

        System.out.print(sb);
    }
}
