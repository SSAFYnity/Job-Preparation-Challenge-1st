package 겹치는건싫어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 겹치는건싫어_이승헌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[100_001];
        int[] map = new int[N];
        int left = 0;
        int right = 0;

        st = new StringTokenizer(br.readLine());
        map[0] = Integer.parseInt(st.nextToken());
        dp[map[0]]++;
        int max = 1;
        int diff = 1;

        while (++right < N) {
            int cur = Integer.parseInt(st.nextToken());
            map[right] = cur;
            dp[cur]++;
            diff++;

            while (dp[cur] > K) {
                dp[map[left++]]--;
                diff--;
            }
            if (max < diff) {
                max = diff;
            }
        }
        System.out.println(max);
    }
}
