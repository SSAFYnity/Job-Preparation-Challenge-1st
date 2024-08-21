import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Comparator;

public class 아기상어_강민정 {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int n;       // 2 <= 공간의 크기 <= 20
    static int[][] sea;     // 공간 배열
    static int sharkSize = 2;       // 아기 상어 크기
    static int eatCnt = 0;          // 아기 상어가 잡아먹은 물고기 수
    static int sharkX;              // 아기 상어의 행 위치
    static int sharkY;              // 아기 상어의 열 위치

    static class Fish {
        int x;      // 행
        int y;      // 열
        int distance;   // 거리

        Fish(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;     // 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간
        n = Integer.parseInt(br.readLine());        // 공간의 크기
        sea = new int[n][n];                        // n * n 크기의 공간 배열

        // n * n 크기의 공간 배열에 입력 값 채우기
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());       // 0: 빈 공간, 1 ~ 6: 칸에 있는 물고기의 크기, 9: 아기 상어의 위치
                if(sea[i][j] == 9) {        // 아기 상어가 있는 위치라면
                    sharkX = i;
                    sharkY = j;
                    sea[i][j] = 0;          // 아기 상어가 있는 위치는 편의상 0으로 할당
                }
            }
        }

        while(true) {       // 아기 상어가 물고기를 잡아먹을 수 있을 때까지 반복
            int result = bfs();         // 아기 상어가 잡아먹은 물고기와의 거리

            if(result == -1) {          // 아기 상어가 잡아먹은 물고기가 없음
                break;          // 엄마 상어에게 도움을 요청해야 해서 끝남
            }

            answer += result;       // 거리를 더하기
            eatCnt++;       // 잡아먹은 물고기 수 증가

            if(eatCnt == sharkSize) {       // 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때마다
                sharkSize++;    // 아기 상어의 크기가 1 증가
                eatCnt = 0;     // 초기화
            }
        }

        System.out.println(answer);     // 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력
    }

    public static int bfs() {
        PriorityQueue<Fish> pq = new PriorityQueue(new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                if(o1.distance == o2.distance && o1.x == o2.x) {        // 거리가 가까운 물고기가 많고, 가장 위에 있는 물고기도 여러마리라면
                    return o1.y - o2.y;         // 가장 왼쪽에 있는 물고기
                } else if(o1.distance == o2.distance) {       // 거리가 가까운 물고기가 많을 때
                    return o1.x - o2.x;         // 가장 위에 있는 물고기
                }
                return o1.distance - o2.distance;       // 거리가 가까운 물고기
            }
        });
        boolean[][] visited = new boolean[n][n];        // 방문배열

        pq.add(new Fish(sharkX, sharkY, 0));     // 아기상어 할당
        visited[sharkX][sharkY] = true;         // 아기상어가 있는 위치를 방문 처리

        while(!pq.isEmpty()) {      // 우선순위 큐에 원소가 있을 동안에 반복
            Fish fish = pq.poll();

            if(sea[fish.x][fish.y] > 0 && sea[fish.x][fish.y] < sharkSize) {       // 잡아먹을 수 있는 물고기를 발견
                sea[fish.x][fish.y] = 0;        // 잡아먹음
                sharkX = fish.x;
                sharkY = fish.y;
                return fish.distance;
            }

            for(int i=0; i<4; i++) {    // 4방 탐색
                int nx = fish.x + dx[i];
                int ny = fish.y + dy[i];

                if(!isValid(nx, ny)) {       // 좌표가 법위를 벗어났다면
                    continue;               // 건너띄기
                } else if(visited[nx][ny]) {       // 방문한 위치면
                    continue;       // 건너띄기
                } else if(sea[nx][ny] > sharkSize) {        // 아기 상어보다 더 크면
                    continue;       // 건너띄기
                }

                visited[nx][ny] = true;     // 방문 처리
                pq.add(new Fish(nx, ny, fish.distance + 1));
            }
        }

        return -1;
    }

    /*
        주어진 nx, ny가 0 ~ n 사이에 있는 유효한 값이면 true를 반환
     */
    public static boolean isValid(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n;
    }
}