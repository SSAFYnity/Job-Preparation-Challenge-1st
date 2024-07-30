import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        long[] minDp = new long[101];
        Arrays.fill(minDp, Long.MAX_VALUE);

        int[] min = {1, 7, 4, 2, 0, 8};
        for (int i = 0, j = 2; i < 6; i++, j++) {
            minDp[j] = min[i];
        }

        minDp[6] = 6;
        minDp[8] = 10;

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j < 8; j++) {
                long compare = minDp[i - j] * 10 + min[j - 2];
                minDp[i] = Math.min(compare, minDp[i]);
            }
        }

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {

            int input = Integer.parseInt(br.readLine());
            StringBuilder maxNumBuilder = new StringBuilder();

            int count = input;
            if (input % 2 == 1) {
                maxNumBuilder.append(7);
                count -= 3;
            } else {
                maxNumBuilder.append(1);
                count -= 2;
            }

            for (int i = 0; i < count; i += 2) {
                maxNumBuilder.append(1);
            }
            sb.append(minDp[input]).append(' ').append(maxNumBuilder).append('\n');
        }

        System.out.println(sb);
    }
}