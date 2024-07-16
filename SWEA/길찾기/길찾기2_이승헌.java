package 길찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class 길찾기2_이승헌 {

    static int[][] map = new int[2][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
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

            sb.append("#").append(tc).append(" ").append(solve() ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean solve() {

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);

        while (!queue.isEmpty()) {

            int cur = queue.poll();

            if (cur == 99) {
                return true;
            }

            if (map[0][cur] != -1) {
                queue.offer(map[0][cur]);
            }
            if (map[1][cur] != -1) {
                queue.offer(map[1][cur]);
            }
        }
        return false;
    }
}
