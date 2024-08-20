import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1113_MakeSwimmingPool {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int[][] map = new int[N][M];
        int[][][] visited = new int[N][M][8]; // -1 : 방문했지만 유효하지 않은 곳, 1 : 방문하여 마친 곳
        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        int answer = 0;
        for (int k = 1; k < 9; k++) {
            for (int i = 1; i < N - 1; i++) {
                loop: for (int j = 1; j < M - 1; j++) {
                    if (map[i][j] > k || visited[i][j][k - 1] != 0) {
                        continue;
                    }

                    int size = 1;
                    Queue<int[]> queue = new LinkedList<>();
                    ArrayList<int[]> list = new ArrayList<>();
                    queue.offer(new int[] { i, j });
                    visited[i][j][k - 1] = 1;
                    list.add(new int[] { i, j, k - 1 });

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        if (cur[0] == 0 || cur[0] == N - 1 || cur[1] == 0 || cur[1] == M - 1) {
                            for (int[] pos : list) {
                                visited[pos[0]][pos[1]][pos[2]] = -1;
                            }
                            continue loop;
                        }

                        for (int d = 0; d < 4; d++) {
                            int ny = cur[0] + dy[d];
                            int nx = cur[1] + dx[d];

                            if (visited[ny][nx][k - 1] < 0) {
                                for (int[] pos : list) {
                                    visited[pos[0]][pos[1]][pos[2]] = -1;
                                }
                                continue loop;
                            }
                            else if (visited[ny][nx][k - 1] > 0) {
                                continue;
                            }

                            if (map[ny][nx] <= k) {
                                queue.offer(new int[] { ny, nx });
                                if (ny != 0 && ny != N - 1 && nx != 0 && nx != M - 1) {
                                    visited[ny][nx][k - 1] = 1;
                                    list.add(new int[] { ny, nx, k - 1 });
                                }
                                size++;
                            }
                        }
                    }
                    answer += size;
                }
            }
        }
        System.out.println(answer);
    }
}