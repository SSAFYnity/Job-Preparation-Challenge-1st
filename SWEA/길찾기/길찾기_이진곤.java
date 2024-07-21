import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 길찾기_이진곤 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(in.readLine());
            int no = Integer.parseInt(st.nextToken());
            int wayCnt = Integer.parseInt(st.nextToken());
            int[][] way = new int[100][2];

            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < wayCnt; i++) {
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                if (way[start][0] == 0) {
                    way[start][0] = end;
                }
                else {
                    way[start][1] = end;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[100];
            queue.offer(0);
            boolean canReach = false;

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == 99) {
                    canReach = true;
                    break;
                }

                int first = way[cur][0];
                int second = way[cur][1];
                if (!visited[first] && first != 0) {
                    queue.offer(first);
                    visited[first] = true;
                }
                if (!visited[second] && second != 0) {
                    queue.offer(second);
                    visited[second] = true;
                }
            }

            sb.append("#").append(t).append(" ").append(canReach ? 1 : 0).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}