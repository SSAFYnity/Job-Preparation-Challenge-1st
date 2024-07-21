import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int answer = Integer.MAX_VALUE;
    static int inf = Integer.MAX_VALUE;
    static int[][] region;
    static int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};
    static List<int[]> hospitals = new ArrayList<>();
    static boolean[][] selection;
    static boolean[][] hRegion;
    static StringBuilder sb = new StringBuilder();

    public static class Spot implements Comparable<Spot> {
        int y, x, time;
        public Spot(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
        public int compareTo(Spot o) {
            return this.time - o.time;
        }
    }

    public static boolean isOut(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    public static void calculate() {
        int[][] vaccine = new int[n][n];
        PriorityQueue<Spot> que = new PriorityQueue<>();
        boolean[][] visit = new boolean[n][n];

        int virusCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = region[i][j];
                if (k == 0) { // 바이러스
                    vaccine[i][j] = inf;
                    virusCnt++;
                } else if (selection[i][j]) {
                    vaccine[i][j] = 0; // 선택된 병원
                    que.add(new Spot(i, j, 0));
                    visit[i][j] = true;
                } else vaccine[i][j] = -1; // 벽
            }
        }

        int t = 0;
        while(!que.isEmpty() && t < answer && virusCnt > 0) {
            Spot now = que.poll();
            for (int d = 0; d < 4; d++) {
                int y = now.y + dr[0][d];
                int x = now.x + dr[1][d];
                if (isOut(y,x) || visit[y][x]) continue;
                if (hRegion[y][x] && !selection[y][x]) {
                    visit[y][x] = true;
                    vaccine[y][x] = now.time+1;
                    que.add(new Spot(y,x,now.time+1));
                    continue;
                }
                if (vaccine[y][x] != inf) continue;
                t = now.time+1;
                visit[y][x] = true;
                vaccine[y][x] = now.time+1;
                que.add(new Spot(y,x,now.time+1));
                virusCnt--;
            }

        }

        if (virusCnt == 0) answer = Math.min(answer,t);
    }

    public static void back(int p, int cnt) {
        if (cnt == m) {
            calculate();
            return;
        }
        if (p >= hospitals.size()) return;

        back(p+1, cnt);
        int[] pick = hospitals.get(p);
        selection[pick[0]][pick[1]] = true;
        back(p+1, cnt+1);
        selection[pick[0]][pick[1]] = false;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        region = new int[n][n];
        selection = new boolean[n][n];
        hRegion = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                region[i][j] = Integer.parseInt(st.nextToken());
                if (region[i][j] == 2) {
                    hospitals.add(new int[] {i, j});
                    hRegion[i][j] = true;
                }
            }
        }

        back(0, 0);

        System.out.println(answer == inf ? -1 : answer);
    }
}