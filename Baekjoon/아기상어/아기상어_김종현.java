import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int map[][];
    static final int dr[] = {-1, 0, 1, 0};
    static final int dc[] = {0, -1, 0, 1};
    static int shark_size = 2;
    static int shark_curr_ate = 2;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        Queue<int[]> q = new LinkedList<>();
        boolean visited[][] = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
										// [0] = r;
										// [1] = c;
										// [2] = dist;
                    q.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[2] == o2[2]) {
                        if (o1[0] == o2[0]) return o1[1] - o2[1];
                        else return o1[0] - o2[0];
                    } else return o1[2] - o2[2];
                }
            });

            while (!q.isEmpty()) {
                int[] curr = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dr[d];
                    int nc = curr[1] + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                    if (map[nr][nc] > shark_size) continue;
                    if (visited[nr][nc]) continue;

                    if (map[nr][nc] == shark_size || map[nr][nc] == 0) {
                        q.add(new int[]{nr, nc, curr[2] + 1});
                        visited[nr][nc] = true;
                    } else if (map[nr][nc] < shark_size) {
                        pq.add(new int[]{nr, nc, curr[2] + 1});
                        q.add(new int[]{nr, nc, curr[2] + 1});
                        visited[nr][nc] = true;
                    }
                }
            }

            if (pq.isEmpty()) {
                break;
            }

            int[] curr = pq.poll();
            if (--shark_curr_ate <= 0) shark_curr_ate = ++shark_size;
            ans += curr[2];
            map[curr[0]][curr[1]] = 0;
            q.add(new int[]{curr[0], curr[1], 0});
            visited = new boolean[N][N];
            visited[curr[0]][curr[1]] = true;
        }
        
        System.out.println(ans);
    }
}