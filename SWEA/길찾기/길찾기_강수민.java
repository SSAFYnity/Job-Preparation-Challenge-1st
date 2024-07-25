import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            int[][] adjMatrix = new int[100][2];
            for (int j = 0; j < 100; j++) {
                Arrays.fill(adjMatrix[j], -1);
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                if (adjMatrix[start][0] == -1) {
                    adjMatrix[start][0] = end;
                } else {
                    adjMatrix[start][1] = end;
                }
            }
            sb.append('#').append(i).append(' ').append(bfs(adjMatrix)).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static int bfs(int[][] adjMatrix) {
        boolean[] visited = new boolean[100];

        Queue<Integer> que = new ArrayDeque<>();
        que.add(0);
        visited[0] = true;

        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int i : adjMatrix[cur]) {
                if (i == -1) {
                    continue;
                }
                if (i == 99) {
                    return 1;
                }
                if (!visited[i]) {
                    que.add(i);
                    visited[i] = true;
                }
            }
        }
        return 0;
    }
}