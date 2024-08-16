package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int[] cnts = {6,2,5,5,4,5,6,3,7,6};
        Map<Integer, Integer> map = new HashMap<>();

        map.put(2, 1);
        map.put(3, 7);
        map.put(4, 4);
        map.put(5, 2);
        map.put(6, 0);
        map.put(7, 8);

        long[] arr = new long[101];
        for (int i = 2; i <= 7; i++) {
            if (i == 6) {
                arr[i] = 6;
                continue;
            }
            arr[i] = map.get(i);
        }
        arr[8] = 10;
        for (int i = 9; i <= 100; i++) {
            arr[i] = Long.MAX_VALUE;
            for (int j = 2; j <= 7; j++) {
                long temp = arr[i-j] * 10 + map.get(j);
                arr[i] = Math.min(temp, arr[i]);
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine());

            sb.append(arr[n]);

            sb.append(' ');
            if (n % 2 == 0) {
                for (int i = 0; i < n/2; i++) {
                    sb.append('1');
                }
            } else {
                sb.append('7');
                for (int i = 0; i < (n-3)/2; i++) {
                    sb.append('1');
                }
            }
            
            System.out.println(sb.toString());
        }
    }
}
