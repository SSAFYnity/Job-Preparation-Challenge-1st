package 길찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 길찾기_이승헌 {

    static int[][] map = new int[2][100];
    static boolean result = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            result = false;
            Arrays.fill(map[0], -1);
            Arrays.fill(map[1], -1);

            br.readLine();
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());

                if (map[0][from] == -1) {
                    map[0][from] = Integer.parseInt(st.nextToken());
                } else {
                    map[1][from] = Integer.parseInt(st.nextToken());
                }
            }
            solve(0);
            sb.append("#").append(tc).append(" ").append(result ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(int idx) {
        if (result || idx == 99) {
            result = true;
            return;
        }

        if (map[0][idx] != -1) {
            solve(map[0][idx]);
        }
        if (map[1][idx] != -1) {
            solve(map[1][idx]);
        }
    }
}
