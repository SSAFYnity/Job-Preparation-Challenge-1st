import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // 상 좌 우 하
    private static Shark shark;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int fish = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 9) {
                    map[i][j] = 0;
                    shark = new Shark(i, j, 2, 0);
                } else if (temp > 0) {
                    map[i][j] = temp;
                    fish++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < fish; i++) {
            int result = bfs();
            if (result == 0) {
                break;
            } else {
                ans += result;
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static int bfs() {
        Queue<Shark> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[map.length][map.length];

        que.add(shark);

        int time = 0;
        Shark temp = new Shark(map.length, map.length, shark.size, shark.eat);

        while (!que.isEmpty()) {
            int size = que.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                Shark now = que.poll();
                if (0 < map[now.row][now.col] && map[now.row][now.col] < now.size) {
                    if ((temp.row > now.row) || (temp.row == now.row && temp.col > now.col)) {
                        temp.copy(now);
                    }
                    flag = true;
                }
                for (int j = 0; j < 4; j++) {
                    int row = now.row + dir[j][0];
                    int col = now.col + dir[j][1];
                    if (0 <= row && row < map.length && 0 <= col && col < map.length && !visited[row][col]
                            && map[row][col] <= now.size) {
                        visited[row][col] = true;
                        que.add(new Shark(row, col, now.size, now.eat));
                    }
                }
            }
            if (flag) {
                map[temp.row][temp.col] = 0;
                temp.eat();
                shark = temp;
                return time;
            }
            time++;
        }
        return 0;
    }

    private static class Shark {
        int row;
        int col;
        int size;
        int eat;

        Shark(int row, int col, int size, int eat) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.eat = eat;
        }

        public void eat() {
            if (++this.eat == size) {
                this.eat = 0;
                size++;
            }
        }

        public void copy(Shark shark) {
            this.row = shark.row;
            this.col = shark.col;
            this.size = shark.size;
            this.eat = shark.eat;
        }
    }
}