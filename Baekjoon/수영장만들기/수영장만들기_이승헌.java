package 수영장만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 수영장만들기_이승헌 {

    static int N, M;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int row = 0; row < N; row++) {
            map[row] = br.readLine().toCharArray();
        }
        int result = 0;
        for (int idx = 1; idx < 9; idx++) {
            visited = new boolean[N][M];
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (map[row][col] != idx + '0' || visited[row][col]) {
                        continue;
                    }
                    result += solve(row, col, map[row][col]);
                }
            }
        }
        System.out.println(result);
    }

    private static int solve(int row, int col, char c) {
        int max = ':';

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(row, col));
        visited[row][col] = true;
        Queue<Node> visitedNode = new ArrayDeque<>();
        visitedNode.offer(new Node(row, col));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int idx = 0; idx < 4; idx++) {
                int nextRow = cur.row + dy[idx];
                int nextCol = cur.col + dx[idx];

                if (nextRow >= N || nextCol >= M || nextRow < 0 || nextCol < 0 || map[nextRow][nextCol] == '0') {
                    while (!visitedNode.isEmpty()) {
                        Node poll = visitedNode.poll();
                        map[poll.row][poll.col] = '0';
                    }
                    return 0; // 넘침
                }else if (visited[nextRow][nextCol]) {
                    continue;
                }else if (map[nextRow][nextCol] != c) {
                    max = Math.min(max, map[nextRow][nextCol]);
                    continue;
                }
                visited[nextRow][nextCol] = true;
                visitedNode.offer(new Node(nextRow, nextCol));
                queue.offer(new Node(nextRow, nextCol));
            }
        }
        if(max == ':') return 0;
        int result = (max - c) * visitedNode.size();
        while (!visitedNode.isEmpty()) {
            Node poll = visitedNode.poll();
            map[poll.row][poll.col] = (char) max;
        }
        return result;
    }

    private static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}