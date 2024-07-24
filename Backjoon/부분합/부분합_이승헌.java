package 부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합_이승헌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int length = Integer.MAX_VALUE;
        int start = -1;

        for (int end = 0; end < n; end++) {
            sum += arr[end];
            if (sum >= s) {
                while (sum >= s) {
                    length = Math.min(length, end - start);
                    start++;
                    sum -= arr[start];
                }
            }
        }

        if (length == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(length);
        }
    }

}
