import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int[] dp = new int[10001];
        for (int i = 0; i < 6; i++) {
            dp[i] = i;
        }
        for (int i = 6; i < 10001; i++) {
            dp[i] = dp[i-1] + i/6;
            if (i%6!=1) dp[i]++;
        }

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }

    }
}
