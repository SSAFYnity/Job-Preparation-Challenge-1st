import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());

        int[] a = new int[N];
        stk = new StringTokenizer(br.readLine());

        int[] cnt = new int[100_000 + 1];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(stk.nextToken());
        }

        int l = 0;
        int r = 0;
        int ret = 0;
        while (r < N) {
            if (cnt[a[r]] + 1 <= K) {
                cnt[a[r++]]++;
            } else {
                cnt[a[l++]]--;
            }
            ret = Math.max(ret, r - l);
        }

        System.out.println(ret);
    }
}
