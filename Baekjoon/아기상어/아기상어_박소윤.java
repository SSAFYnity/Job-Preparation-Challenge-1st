import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Block implements Comparable<Block> {

        int x;
        int y;
        int distance;

        public Block(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Block other) {
            if (this.distance == other.distance) {
                if (this.x == other.x) {
                    return Integer.compare(this.y, other.y);    // 3. 좌측에 위치한 순
                }
                return Integer.compare(this.x, other.x);    // 2. 상단에 위치한 순
            }
            return Integer.compare(this.distance, other.distance);  // 1. 거리가 가까운 순
        }
    }

    static int N;
    static int[][] sea;
    static int[] startPoint = new int[2];
    static int size = 2;
    static int eaten = 0;
    static int time = 0;
    static int[] dx = {-1, 0, 1, 0};   // 상 좌 하 우
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sea = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] == 9) {
                    startPoint[0] = i;
                    startPoint[1] = j;
                    sea[i][j] = 0;
                }
            }
        }

        while (hunt()) {
        }
        System.out.println(time);
    }

    static boolean hunt() {
        PriorityQueue<Block> q = new PriorityQueue<>();
        q.offer(new Block(startPoint[0], startPoint[1], 0));
        boolean[][] visited = new boolean[N][N];
        visited[startPoint[0]][startPoint[1]] = true;

        while (!q.isEmpty()) {
            Block cur = q.poll();
            if (sea[cur.x][cur.y] > 0 && sea[cur.x][cur.y] < size) {   // 물고기 크기가 더 작음 -> 먹자!
                sea[cur.x][cur.y] = 0;

                startPoint[0] = cur.x;
                startPoint[1] = cur.y;
                time += cur.distance;
                if (eaten + 1 == size) {
                    size++;
                    eaten = 0;
                } else {
                    eaten++;
                }
                return true;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (isOutOfBounds(nx, ny) || // sea 배열 범위 초과
                        visited[nx][ny] ||  // 이미 방문한 곳
                        sea[nx][ny] > size) {     // 물고기 크기가 더 큼 -> 이동 불가한 경우들
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new Block(nx, ny, cur.distance + 1));
            }
        }
        return false;
    }

    static boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}