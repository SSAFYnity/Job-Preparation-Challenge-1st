import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 바이러스백신_이진곤 {
    final static int[] dy = new int[] { -1, 0, 1, 0 };
    final static int[] dx = new int[] { 0, 1, 0, -1 };

    static ArrayList<int[]> hospitalPos;
    static int[][] map;
    static int[] hospital;
    static int N, M;
    static int answer = Integer.MAX_VALUE;
    static int target;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        hospital = new int[M];
        hospitalPos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    hospitalPos.add(new int[] { i, j });
                }
                else if (map[i][j] == 0) {
                    target++;
                }
            }
        }

        combination(0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void process() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < hospital.length; i++) {
            int[] pos = hospitalPos.get(hospital[i]);
            queue.offer(pos);
            visited[pos[0]][pos[1]] = true;
        }

        int time = -1;
        int erased = 0;
        loop:
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (map[cur[0]][cur[1]] == 0) {
                    erased++;
                }
                if (erased >= target) {
                    break loop;
                }

                for (int d = 0; d < 4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];

                    if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] == 1 || visited[ny][nx]) {
                        continue;
                    }

                    queue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;
                }
            }
        }

        if (erased == target) {
            answer = Math.min(answer, time);
        }
    }

    static void combination(int idx, int num) {
        if (idx >= M) {
            process();
            return;
        }

        for (int i = num; i < hospitalPos.size(); i++) {
            hospital[idx] = i;
            combination(idx + 1, i + 1);
        }
    }
}