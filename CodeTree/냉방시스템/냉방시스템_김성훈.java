import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m, k, time;

    static int[][] curCool, nextCool;
    static boolean[][][] wall;
    static ArrayList<int[]> offices;
    static ArrayList<int[]> aircons;

    // 왼쪽, 위, 오른쪽, 아래
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    static int[][] ax = {
            {-1, 0, 10, 1}, // 왼쪽 위, 왼쪽, x, 왼쪽 아래
            {-1, -1, -1, 10}, // 위 왼쪽, 위, 위 오른쪽, x
            {10, -1, 0, 1}, // x, 오른쪽 위, 오른쪽, 오른쪽 아래
            {1, 10, 1, 1} // 아래 왼쪽, x, 아래, 아래 오른쪽
    };
    static int[][] ay = {
            {-1, -1, 10, -1}, // 왼쪽 위, 왼쪽, 왼쪽 아래
            {-1, 0, 1, 10}, // 위 왼쪽, 위, 위 오른쪽
            {10, 1, 1, 1}, // x, 오른쪽 위, 오른쪽, 오른쪽 아래
            {-1, 10, 0, 1} // 아래 왼쪽, x, 아래, 아래 오른쪽
    };

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

        Deque<int[]> que = new ArrayDeque<>();
        ArrayList<int[]> nextPos = new ArrayList<>();
        boolean[][] visit = new boolean[n][n];

        for (int[] aircon: aircons) {

            int airconX = aircon[0];
            int airconY = aircon[1];
            int airconDir = aircon[2];

            int nextX = airconX + dx[airconDir];
            int nextY = airconY + dy[airconDir];
//            curCool[nextX][nextY] += 5;

            que.add(new int[] {nextX, nextY, 5});

            while (!que.isEmpty()) {

                int[] cur = que.poll();
                int curX = cur[0];
                int curY = cur[1];

                curCool[curX][curY] += cur[2];

                if (cur[2] == 1) continue;

                nextPos.clear();

                int[] nextDir = {(airconDir + 1) % 4, (airconDir + 4 - 1) % 4};
                nextX = curX + dx[airconDir];
                nextY = curY + dy[airconDir];

                if (nextX != -1 && nextY != -1 && nextX != n && nextY != n) nextPos.add(new int[] {nextX, nextY, cur[2] - 1});

                for (int dir : nextDir) {

                    if (wall[curX][curY][dir]) continue;
                    nextX = curX + dx[dir];
                    nextY = curY + dy[dir];
                    if (nextX == -1 || nextY == -1 || nextX == n || nextY == n) continue;

                    nextPos.add(new int[] {nextX, nextY, cur[2] - 1});
                }

                for (int[] next : nextPos) {
                    nextX = next[0];
                    nextY = next[1];
                    if (wall[nextX][nextY][airconDir]) continue;
                    if (visit[nextX][nextY]) continue;
                    visit[nextX][nextY] = true;
                    que.add(new int[] {nextX, nextY, next[2]});
                }

            }


        }
    }
    static void mixCool() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                for (int dir = 0; dir < 4; dir++) {

                    if (i + dx[dir] == 0 || i + dx[dir] == n) continue;
                    if (i + dy[dir] == 0 || i + dy[dir] == n) continue;
                    if (wall[i][j][dir]) continue;

                    int nx = i + dx[dir];
                    int ny = i + dy[dir];

                    int diff = (curCool[i][j] - curCool[ny][nx]) / 4;
                    if (diff == 0) continue;

                    if (diff > 0) { // 현재 위치가 냉기를 뺏기는 경우
                        nextCool[i][j] -= diff;
                        nextCool[ny][nx] += diff;
                    } else { // 현재 위치가 냉기를 받는 경우
                        nextCool[i][j] += diff;
                        nextCool[ny][nx] -= diff;
                    }
                }

            }
        }
    }
    static void makeHot() {
        for (int i = 0; i < n - 1; i++) {
            curCool[i][0]--; // 0,0 1,0 2,0 // 0,0에서 밑으로
            if (curCool[i][0] < 0) curCool[i][0] = 0;

            curCool[0][n - i - 1]--; // 0, n-1, n, n-2 // 0, n-1에서 왼쪽으로
            if (curCool[0][n - i - 1] < 0) curCool[0][n - i - 1] = 0;

            curCool[n - 1][i]--; // n-1, 0, n-1, 1  // n-1,0에서 오른쪽으로
            if (curCool[n - 1][i] < 0) curCool[n - 1][i] = 0;

            curCool[n - 1 - i][n - 1]--; // n-1, n-1에서 위쪽으로
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
        wall = new boolean[n][n][4];
        offices = new ArrayList<>();
        aircons = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 0) continue;
                if (value == 1) {
                    offices.add(new int[] {i, j});
                } else {
                    aircons.add(new int[] {i, j, value - 1});
                }
            }
        }

        // 왼쪽, 위, 오른쪽, 아래
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if (dir == 0) { // 위쪽에 벽
                wall[x][y][1] = true; // 위 방향 기준
                if (x - 1 != 0) wall[x - 1][y][3] = true; // 아래 방향 기준
            } else { // 왼쪽에 벽
                wall[x][y][0] = true;
                if (y - 1 != 0) wall[x][y - 1][2] = true; // 오른쪽 방향 기준
            }
        }
    }

}