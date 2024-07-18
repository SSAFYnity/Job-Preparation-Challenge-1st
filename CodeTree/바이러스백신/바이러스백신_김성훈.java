import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] map;
    static ArrayList<int[]> hospitals;
    static int virusCnt, hospitalCnt;
    static int n, m;
    static int minDay = Integer.MAX_VALUE;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] visit;
    static Deque<int[]> que;

    public static void main(String[] args) throws IOException{
        input();
        pro();
    }

    static void input() throws IOException{

        hospitals = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) virusCnt++;
                if (map[i][j] == 2) {
                    hospitals.add(new int[] {i, j, 0});
                    hospitalCnt++;
                    continue;
                }
            }
        }
    }

    static void pro() {

        if (virusCnt == 0) {
            System.out.println(0);
            return;
        }

        select(0, 0, new ArrayList<int[]>());
        if (minDay == Integer.MAX_VALUE) minDay = -1;
        System.out.println(minDay);
    }

    static void select(int depth, int startIdx, ArrayList<int[]> selectedHospitals) {
        if (depth == m) {
            minDay = Math.min(minDay, bfs(selectedHospitals));
            return;
        }

        for (int i = startIdx; i < hospitalCnt; i++) {
            selectedHospitals.add(hospitals.get(i));
            select(depth + 1, i + 1, selectedHospitals);
            selectedHospitals.remove(depth);
        }
    }

    static int bfs(ArrayList<int[]> selectedHospitals) {

        // selectedHospitals는 {r, c, curDay}로 구성된 int[3] 배열
        int day = -1;
        int killedVirus = 0;

        que = new ArrayDeque<>(selectedHospitals);
        visit = new boolean[n][n];
        for (int[] select : selectedHospitals) {
            visit[select[0]][select[1]] = true;
        }


        while(!que.isEmpty()) {
            int[] cur = que.pollFirst();
            int curDay = cur[2];
            day = curDay;
            if (killedVirus == virusCnt) continue;

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (ny == -1 || nx == -1 || ny == n || nx == n) continue;
                if (visit[ny][nx]) continue;
                if (map[ny][nx] == 1) continue;
                if (map[ny][nx] == 0) killedVirus++;
                visit[ny][nx] = true;
                int[] next = new int[] {ny, nx, curDay + 1};
                que.add(next);
            }
        }

        if (killedVirus != virusCnt) day = Integer.MAX_VALUE;
        return day;
    }
}