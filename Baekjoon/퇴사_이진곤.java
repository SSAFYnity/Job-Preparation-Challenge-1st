import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 퇴사_이진곤 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[][] info = new int[N + 1][2];
		int[] dp = new int[N + 1];
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			
			if (i + info[i][0] - 1 > N)
				continue;
			
			dp[i + info[i][0] - 1] = Math.max(dp[i + info[i][0] - 1], dp[i - 1] + info[i][1]);
		}
		
		System.out.println(dp[N]);
	}
}