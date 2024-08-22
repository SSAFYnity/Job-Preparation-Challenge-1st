import java.util.*;
import java.io.*;
public class Main
{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] fishes;
    static int[][] map;
    static boolean[][] visit;

    static int curY, curX, curSize, curNeedFish;

    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    public static int[] searchBfs() {

        Queue<int[]> que = new LinkedList<>();

        que.add(new int[] {curY, curX, 0});
        visit = new boolean[n][n];
        visit[curY][curX] = true;

        List<int[]> sameDistShark = new ArrayList<>();

        while (!que.isEmpty()) {

            int[] cur = que.poll();
            if (sameDistShark.size() != 0 && map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] != curSize) {

                if (map[cur[0]][cur[1]] >= curSize) continue;
                if (cur[2] > sameDistShark.get(0)[2]) continue;
                if (cur[2] < sameDistShark.get(0)[2]) sameDistShark.clear();
                sameDistShark.add(cur);
                continue;
            }
//            System.out.println(Arrays.toString(cur) + " / " + curSize);
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                int nMove = cur[2] + 1;

                if (ny == n || nx == n || ny == -1 || nx == -1) continue;
                if (visit[ny][nx]) continue;
                if (map[ny][nx] > curSize) continue;

                visit[ny][nx] = true;
                if (map[ny][nx] == 0 || map[ny][nx] == curSize) {
                    que.add(new int[] {ny, nx, nMove});
                } else {
                    if (sameDistShark.size() == 0) sameDistShark.add(new int[] {ny, nx, nMove});
                    else if (sameDistShark.get(0)[2] == nMove) sameDistShark.add(new int[] {ny, nx, nMove});
                }
            }
        }

        if (sameDistShark.size() == 0) return null;

        int[] priorityShark = sameDistShark.get(0);
        for (int i = 1; i < sameDistShark.size(); i++) {
            int[] curShark = sameDistShark.get(i);

            if (curShark[0] < priorityShark[0]) { // 기존값이 y가 큰 경우
                priorityShark = curShark;
                continue;
            }

            if(curShark[0] == priorityShark[0]) { // y가 같은 경우
                priorityShark[1] = Math.min(priorityShark[1], curShark[1]);
            }
        }
        fishes[map[priorityShark[0]][priorityShark[1]]]--;
        map[priorityShark[0]][priorityShark[1]] = 0;
        curNeedFish--;
        return priorityShark;
    }

    public static void main(String args[]) throws IOException
    {
        n = Integer.parseInt(br.readLine());
        curSize = curNeedFish = 2;

        map = new int[n][n];
        fishes = new int[7];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                char c = st.nextToken().charAt(0);
                if (c == '9') {
                    curY = i;
                    curX = j;
                } else {
                    fishes[c - '0']++; // 0은 사용 x
                    map[i][j] = c - '0';
                }
            }
        }

        int move = 0;

        while (true) {

            boolean hasNextFish = false;
            int compare = Math.min(curSize, 7);

            for (int i = 1; i < compare; i++) {
                if (fishes[i] != 0) {
                    hasNextFish = true;
                    break;
                }
            }
            if (!hasNextFish) break;

            int[] nextFishInfo = searchBfs(); // y, x, move
            if (nextFishInfo == null) break;
            curY = nextFishInfo[0];
            curX = nextFishInfo[1];
            move += nextFishInfo[2];
//            System.out.println(Arrays.toString(nextFishInfo) + " / " + move);
            if (curNeedFish == 0) {
                curSize++;
                curNeedFish = curSize;
            }

        }
        System.out.println(move);

    }
}