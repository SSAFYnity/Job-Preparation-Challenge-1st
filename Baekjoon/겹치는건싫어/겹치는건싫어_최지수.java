import java.util.*;
import java.io.*;

public class Main {

    static int n, inf = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[100001];
        count[nums[0]]++;
        int answer = 1;
        int start = 0, end = 0;
        while (start <= end && end < n) {
            if (count[nums[end]] > k) {
                count[nums[start]]--;
                start++;
            } else {
                answer = Math.max(end-start+1, answer);
                end++;
                if (end < n) {
                    count[nums[end]]++;
                }
            }
        }

        System.out.println(answer);
    }
}
