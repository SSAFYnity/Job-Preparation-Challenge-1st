package 아기상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 아기상어_이승헌 {

    static int[] dx = {0, -1, 1, 0}; // 상 좌 우 하
    static int[] dy = {-1, 0, 0, 1};
    static boolean[][] visited;
    static int[] target;
    static int eatAble;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Shark[][] map = new Shark[N][N];
        visited = new boolean[N][N];
        BabyShark babyShark = null;
        target = new int[8];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < N; column++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 0) {
                    continue;
                } else if (input == 9) {
                    babyShark = new BabyShark(row, column, 2, 0, 0);
                    continue;
                }
                target[input]++;
                map[row][column] = new Shark(row, column, input);
            }
        }

        System.out.println(solve(map, babyShark));
    }

    private static int solve(Shark[][] map, BabyShark shark) {
        PriorityQueue<BabyShark> pqq = new PriorityQueue<>();
        pqq.offer(shark);
        visited[shark.row][shark.col] = true;
        int result = 0;
        eatAble = target[1];

        while (!pqq.isEmpty() && eatAble != 0) {
            BabyShark curBaby = pqq.poll();

            if(map[curBaby.row][curBaby.col] != null &&  map[curBaby.row][curBaby.col].size < curBaby.size) {
                pqq.clear();
                map[curBaby.row][curBaby.col] = null;
                curBaby.eat();
                eatAble += target[curBaby.size];
                clearVisited(curBaby);
                result = curBaby.result;
            }

            for (int idx = 0; idx < 4; idx++) {
                int nextRow = curBaby.row + dy[idx];
                int nextCol = curBaby.col + dx[idx];

                if(nextRow < 0 || nextCol < 0 || nextRow >= map.length || nextCol >= map[0].length || visited[nextRow][nextCol]
                || (map[nextRow][nextCol] != null && map[nextRow][nextCol].size > curBaby.size)) {
                    continue;
                }
                visited[nextRow][nextCol] = true;
                pqq.offer(new BabyShark(nextRow, nextCol, curBaby.size, curBaby.eat, curBaby.result + 1));
            }
        }
        return result;
    }

    private static void clearVisited(BabyShark babyShark){
        for (int row = 0; row < visited.length; row++) {
            Arrays.fill(visited[row], false);
        }
        visited[babyShark.row][babyShark.col] = true;
    }

    private static class BabyShark extends Shark implements Comparable<BabyShark>{
        int eat;
        int result;

        public BabyShark(int row, int col, int size, int eat, int result) {
            super(row, col, size);
            this.eat = eat;
            this.result = result;
        }

        @Override
        public int compareTo(BabyShark o) {
            if(result == o.result){
                if(this.row == o.row){
                    return this.col - o.col;
                }
                return this.row - o.row;
            }
            return this.result - o.result;
        }

        public void eat() {
            if (this.size > 6) {
                return;
            }
            this.eat++;
            if(this.eat == this.size){
                this.eat = 0;
                this.size++;

            }
        }
    }

    private static class Shark {
        int row;
        int col;
        int size;

        public Shark(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
        }
    }
}
