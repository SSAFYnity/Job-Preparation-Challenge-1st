import java.io.*;

public class Main {
    private static int top;
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        dp = new long[1_000_001];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        top = 3;

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(calDP(n)).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static long calDP(int n) {
        if (n > top) {
            for (int i = top + 1; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1_000_000_009;
            }
            top = n;
        }
        return dp[n];
    }
}
/*
<1, 2, 3 의 덧셈으로 n을 만드는 경우의 수>
 - 1+2+2, 2+1+2, 2+2+1 가능 (중복아님)
 - dp[] 채우면 될 듯
 - dp[n] = dp[n-1] + dp[n-2] + dp[n-3]
 - n은 0부터 시작
 - 출력 결과를 10억9로 나눈 나머지를 출력하라 했으므로, 모든 dp[i]값은 10억 9의 나머지 처리
 - 보다 좋은 성능을 위해 n의 최댓값인 100만까지 미리 계산하기 X -> 아직 계산 안된 곳이면 계산하기
 */