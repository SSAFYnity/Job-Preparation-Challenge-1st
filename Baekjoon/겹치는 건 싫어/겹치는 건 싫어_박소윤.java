import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] sequence = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        int[] count = new int[200000 + 1];

        int start = 0, end = 0;
        int max = 0;
        while (end < N) {
            if (count[sequence[end]] < K) {
                count[sequence[end]]++;
                end++;
            } else {
                count[sequence[start]]--;
                start++;
            }
            max = Math.max(max, end - start);
        }
        System.out.println(max);
    }
}
