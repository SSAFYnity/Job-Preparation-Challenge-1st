import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		int dp[][] = new int[n][3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			if (i == 0) {
				dp[0][0] = R;
				dp[0][1] = G;
				dp[0][2] = B;
			} else {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + R;
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + G;
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + B;
			}
		}

		int min = dp[n - 1][0];
		for (int i = 1; i < 3; i++) {
			if (min > dp[n - 1][i])
				min = dp[n - 1][i];
		}

		System.out.println(min);
	}
}