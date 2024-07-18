import java.io.*;
import java.util.*;

public class Main {
    static int n;       // 도시 크기
    static int m;       // 선택해야 하는 병원 갯수
    static int answer = 50 * 50;      // 바이러스를 다 죽이는 최소시간
    static int hospitalsCnt;        // 총 병원의 수
    static int virusCnt = 0;            // 바이러스의 수
    static int[][] city;                   // n * n 크기의 도시
    static boolean[] selectedHospital;   // 선택한 병원
    static List<Hospital> hospitals; // 병원의 좌표 리스트
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstInput = br.readLine().split(" ");
        n = Integer.parseInt(firstInput[0]);        // 3 <= 도시 크기 <= 50
        m = Integer.parseInt(firstInput[1]);        // 1 <= 선택할 병원 갯수 <= 10
        city = new int[n][n];
        hospitals = new ArrayList<>();

        for(int i=0; i<n; i++) {        // 행
            String[] row = br.readLine().split(" ");
            for(int j=0; j<n; j++) {    // 열
                city[i][j] = Integer.parseInt(row[j]);
                if(city[i][j] == 2) {       // 병원
                    hospitals.add(new Hospital(i, j));      // 병원 좌표 추가
                } else if(city[i][j] == 0)  {               // 바이러스
                    virusCnt++;                 // 바이러스 수 카운팅
                }
            }
        }
        
        if(virusCnt == 0) {     // 바이러스가 없는 입력을 받았으면
            System.out.println(0);
            return;
        }
        
        hospitalsCnt = hospitals.size();
        selectedHospital = new boolean[hospitalsCnt];

        combination(0, 0);              // 조합 생성

        if(answer == 50 * 50) {         // 바이러스를 전부 없애는데 걸린 최소 시간이 초기값 그대로면
            answer = -1;                // 바이러스를 전부 없애지 못한다
        }

        System.out.println(answer);     // 바이러스를 전부 없애는데 걸린 최소 시간 출력
    }

    /*
        선택한 병원의 조합 생성
        start: 시작 인덱스
        cnt: 선택한 병원 갯수
    */
    public static void combination(int start, int cnt) {
        if(cnt == m) {      // 병원을 m개 다 골랐다면
            bfs();          // 만든 조합으로 BFS 탐색
            return;
        } else {
            for(int i=start; i<hospitalsCnt; i++) {
                selectedHospital[i] = true;      // i번째 병원 선택
                combination(i+1, cnt + 1);
                selectedHospital[i] = false;     // i번째 병원을 선택하지 않음
            }
        }
    }

    /*
        선택한 병원 좌표들을 큐에 넣기
    */
    public static void initQue(Queue<Node> que, boolean[][] visited) {
        for(int i=0; i<hospitalsCnt; i++) {
            if(selectedHospital[i]) {    // 선택한 병원이라면
                que.add(new Node(hospitals.get(i).x, hospitals.get(i).y, 0));
                visited[hospitals.get(i).x][hospitals.get(i).y] = true;         // 방문 처리
            }
        }
    }

    public static void bfs() {
        Queue<Node> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        initQue(que, visited);       // 선택한 병원들을 큐에 넣기
        int cnt = 0;        // 죽인 바이러스의 수

        while(!que.isEmpty()) {
            Node node = que.poll();     
            if(city[node.x][node.y] == 0) {     // 바이러스
                cnt++;
                if(cnt == virusCnt) {
                    answer = Math.min(answer, node.time);
                    break;
                }
            }
            
            for(int i=0; i<4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(0 > nx || nx >= n || 0 > ny || ny >= n) { // 좌표를 벗어나면
                    continue;
                }
                if(visited[nx][ny] || city[nx][ny] == 1) {      // 방문한 기록이 있거나 벽이라면
                    continue;
                }  
                que.add(new Node(nx, ny, node.time + 1));       // 바이러스를 찾았을 때 시간이 증가
                visited[nx][ny] = true;         // 방문 처리
            }
        }
    }

    static class Node {
        int x;
        int y;
        int time;       // (x, y) 위치까지 오는데 걸린 시간

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static class Hospital {
        int x;
        int y;

        public Hospital(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
