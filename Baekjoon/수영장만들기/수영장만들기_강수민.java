import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    private static int N, M;
    private static int[][] pool;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pool = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                pool[i][j] = Character.getNumericValue(chars[j]);
            }
        }

        // BFS 1) 물을 부으면 안되는 곳은 true
        for (int i = 0; i < N; i++) {
            if (!visited[i][0]) {
                noWater_Upper(new Point(i, 0));
            }
            if (!visited[i][M - 1]) {
                noWater_Upper(new Point(i, M - 1));
            }
        }
        for (int i = 0; i < M; i++) {
            if (!visited[0][i]) {
                noWater_Upper(new Point(0, i));
            }
            if (!visited[N - 1][i]) {
                noWater_Upper(new Point(N - 1, i));
            }
        }

        // BFS 2) 채우는 물 양 카운트
        int cnt = 0;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (!visited[i][j]) {
                    cnt += cntPoolWater(new Point(i, j));
                }
            }
        }

        // Output
        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static void noWater_Upper(Point start) { // 상승 방향으로 물을 못 담는 위치 표시
        Queue<Point> que = new ArrayDeque<>();
        que.add(start);
        visited[start.row][start.col] = true;
        while (!que.isEmpty()) {
            Point p = que.poll();
            for (int[] dir : dir) {
                int row = p.row + dir[0];
                int col = p.col + dir[1];
                if (0 <= row && row < N && 0 <= col && col < M
                        && !visited[row][col] && pool[row][col] >= pool[p.row][p.col]) {
                    visited[row][col] = true;
                    que.add(new Point(row, col));
                }
            }
        }
    }

    private static int cntPoolWater(Point start) { // 물이 고이는 최대 높이 반환
        int maxHeight = Integer.MAX_VALUE;

        Queue<Point> que = new ArrayDeque<>();
        Queue<Point> water = new ArrayDeque<>();
        boolean[][] flag = new boolean[N][M];

        que.add(start);
        water.add(start);
        flag[start.row][start.col] = true;

        while (!que.isEmpty()) { // 물 높이의 최대값 찾기
            Point p = que.poll();
            for (int[] dir : dir) {
                int row = p.row + dir[0];
                int col = p.col + dir[1];
                if (visited[row][col]) { // 물을 채우면 안되는 곳
                    if (pool[row][col] <= pool[p.row][p.col]) { // 채우면 안되는 곳인데 흘러감
                        noWater_Upper(new Point(row, col));
                        return 0;
                    }
                    maxHeight = Math.min(maxHeight, pool[row][col]);
                } else if (!flag[row][col] && pool[row][col] <= pool[p.row][p.col]) { // 시작 위치에 물 부우면 같이 참
                    flag[row][col] = true;
                    que.add(new Point(row, col));
                    water.add(new Point(row, col));
                } else if (pool[row][col] > pool[p.row][p.col])
                    maxHeight = Math.min(maxHeight, pool[row][col]);
            }
        }

        int cnt = 0;
        for (Point p : water) {
            if (maxHeight > pool[p.row][p.col]) {
                cnt += maxHeight - pool[p.row][p.col];
                pool[p.row][p.col] = maxHeight;
            }
        }

        return cnt;
    }

    private static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}