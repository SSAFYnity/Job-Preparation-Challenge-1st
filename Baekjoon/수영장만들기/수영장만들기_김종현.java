import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, max, a[][];
    private static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    private static boolean[][] visited;
    private static Queue<int[]> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        a = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                a[i][j] = str.charAt(j) - '0';
                max = Math.max(max, a[i][j]);
            }
        }

        q = new LinkedList<>();
        visited = new boolean[N][M];
        int ans = 0;

        for (int i = 1; i <= max; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(visited[j], false);
            }

            for (int j = 1; j < N-1; j++) {
                for (int k = 1; k < M-1; k++) {
                    if (a[j][k] < i && !visited[j][k]) {
                        int ret = bfs(i, j, k);
                        ans += ret;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static int bfs(int height, int j, int k) {
        q.add(new int[]{j, k});
        visited[j][k] = true;
        int cnt = 1;
        boolean check = false;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    check = true;
                    continue;
                }

                if (visited[nr][nc] || a[nr][nc] >= height) {
                    continue;
                }

                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
                cnt++;
            }
        }

        if (check) cnt = 0;
        return cnt;
    }
}
