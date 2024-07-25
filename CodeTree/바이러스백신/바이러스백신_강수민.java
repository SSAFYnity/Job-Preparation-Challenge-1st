import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, answer;
    private static ArrayList<Point> hospitals;
    private static int[][] map;
    private static int[] pick;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        hospitals = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 2) {
                    hospitals.add(new Point(i, j));
                }
                map[i][j] = temp;
            }
        }

        pick = new int[M];
        answer = Integer.MAX_VALUE;

        combination(0, 0);

        bw.write(Integer.toString(answer == Integer.MAX_VALUE ? -1 : answer));
        bw.flush();
    }

    private static void combination(int start, int cnt) {
        if (cnt == M) {
            answer = Math.min(answer, bfs());
            return;
        }
        for (int i = start; i < hospitals.size(); i++) {
            pick[cnt] = i;
            combination(i + 1, cnt + 1);
        }
    }

    private static int bfs() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }

        if (finish(copy)) {
            return 0;
        }

        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            Point point = hospitals.get(pick[i]);
            visited[point.x][point.y] = true;
            q.add(point);
            copy[point.x][point.y] = 2;
        }

        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point point = q.poll();
                for (int j = 0; j < 4; j++) {
                    int x = point.x + dx[j];
                    int y = point.y + dy[j];
                    if (0 <= x && x < N && 0 <= y && y < N && !visited[x][y] && copy[x][y] != 1) {
                        q.add(new Point(x, y));
                        visited[x][y] = true;
                        copy[x][y] = 2;
                    }
                }
            }
            time++;
            if (finish(copy)) {
                return time;
            }
        }

        return Integer.MAX_VALUE;
    }

    private static boolean finish(int[][] copy) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copy[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
