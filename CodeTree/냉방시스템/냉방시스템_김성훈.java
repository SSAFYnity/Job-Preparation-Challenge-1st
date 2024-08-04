import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m, k, time;

    static int[][] curCool, nextCool;
    static boolean[][][] wall;
    static ArrayList<int[]> offices, aircons;

    static ArrayList<int[]> nextPos;
    static Deque<int[]> que;


    static boolean[][] visit;
    // 왼쪽, 위, 오른쪽, 아래
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        input();

        while (time != 100) {
            makeCool();
            mixCool();
            makeHot();
            time++;
            if (isCool()) break;
        }

        if (time == 100) time = -1;
        System.out.println(time);
    }

    static void makeCool() {

        for (int[] aircon: aircons) {

            visit = new boolean[n][n];
            int airconY = aircon[0];
            int airconX = aircon[1];
            int airconDir = aircon[2];

            int nextX = airconX + dx[airconDir];
            int nextY = airconY + dy[airconDir];

            que.add(new int[] {nextY, nextX, 5});

            while (!que.isEmpty()) {

                int[] cur = que.poll();
                int curY = cur[0];
                int curX = cur[1];

                curCool[curY][curX] += cur[2];

                if (cur[2] == 1) continue;

                nextPos.clear();
                // 왼쪽, 위, 오른쪽, 아래
                int[] nextDir = {(airconDir + 1) % 4, (airconDir + 4 - 1) % 4};
                nextPos.add(new int[] {curY, curX, cur[2]});

                for (int dir : nextDir) {

                    if (wall[dir][curY][curX]) continue;
                    nextY = curY + dy[dir];
                    nextX = curX + dx[dir];
                    if (nextX == -1 || nextY == -1 || nextX == n || nextY == n) continue;

                    nextPos.add(new int[] {nextY, nextX, cur[2]});
                }

                for (int[] next : nextPos) {
                    nextY = next[0] + dy[airconDir];
                    nextX = next[1] + dx[airconDir];
                    if (nextX == -1 || nextY == -1 || nextX == n || nextY == n) continue;
                    if (wall[(airconDir + 2) % 4][nextY][nextX]) continue;
                    if (visit[nextY][nextX]) continue;
                    visit[nextY][nextX] = true;
                    que.add(new int[] {nextY, nextX, next[2] - 1});

                }
            }
        }
    }

    static void mixCool() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                for (int dir = 2; dir < 4; dir++) {

                    int ny = i + dy[dir];
                    int nx = j + dx[dir];
                    if (ny == -1 || ny == n) continue;
                    if (nx == -1 || nx == n) continue;

                    if (wall[dir][i][j]) continue;

                    int diff = (curCool[i][j] - curCool[ny][nx]) / 4;
                    if (diff == 0) continue;

                    nextCool[i][j] -= diff;
                    nextCool[ny][nx] += diff;
                }

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                curCool[i][j] += nextCool[i][j];
                nextCool[i][j] = 0;
            }
        }
    }

    static void makeHot() {
        for (int i = 0; i < n - 1; i++) {
            curCool[i][0]--;
            if (curCool[i][0] < 0) curCool[i][0] = 0;

            curCool[0][n - i - 1]--;
            if (curCool[0][n - i - 1] < 0) curCool[0][n - i - 1] = 0;

            curCool[n - 1][i]--;
            if (curCool[n - 1][i] < 0) curCool[n - 1][i] = 0;

            curCool[n - 1 - i][n - 1]--;
            if (curCool[n - 1 - i][n - 1] < 0) curCool[n - 1 - i][n - 1] = 0;
        }
    }
    static boolean isCool() {
        for (int[] office : offices) {
            if (curCool[office[0]][office[1]] < k) return false;
        }
        return true;
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        curCool = new int[n][n];
        nextCool = new int[n][n];
        wall = new boolean[4][n][n];
        offices = new ArrayList<>();
        aircons = new ArrayList<>();

        que = new ArrayDeque<>();
        nextPos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 0) continue;
                if (value == 1) {
                    offices.add(new int[] {i, j});
                } else {
                    aircons.add(new int[] {i, j, value - 2});
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            if (dir == 0) { // 위쪽에 벽
                wall[1][y][x] = true; // 위 방향 기준
                if (y - 1 != n) wall[3][y - 1][x] = true; // 아래 방향 기준
            } else { // 왼쪽에 벽
                wall[0][y][x] = true;
                if (x - 1 != n) wall[2][y][x - 1] = true; // 오른쪽 방향 기준
            }
        }
    }
}