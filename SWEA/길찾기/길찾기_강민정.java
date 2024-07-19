import java.io.*;

class Solution {

    static int[][] arr;     // 단방향 연결 정보를 담는 배열(갈림길이 1개면 0열에 0이 아닌 값, 갈림길이 2개라면 0열과 1열에 0이 아닌 값)
    static boolean[][] visited;     // 방문 배열
    static int answer;      // 목적지(99)에 도착할 수 있으면 1, 아니면 0

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = 10;         // 테스트 케이스 수

        for(int test_case = 1; test_case <= T; test_case++) {
            String[] firstInput = br.readLine().split(" ");
            int tc = Integer.parseInt(firstInput[0]);		// 테스트케이스 번호
            int pathCnt = Integer.parseInt(firstInput[1]);		// 길의 총 개수
            String[] secondInput = br.readLine().split(" ");
            answer = 0;
            arr = new int[100][2];
            visited = new boolean[100][2];
            for(int i=0; i<pathCnt*2; i+=2) {		// 순서쌍 입력받기
                int start = Integer.parseInt(secondInput[i]);
                int end = Integer.parseInt(secondInput[i + 1]);
                if(arr[start][0] == 0) {
                    arr[start][0] = end;
                } else {		// 갈림길이 두 개일때
                    arr[start][1] = end;
                }
            }

            dfs(0);
            bw.write("#" + tc + " " + answer + "\n");
        }
        bw.flush();
    }

    /*
        DFS 탐색
        cur: 현재 위치 인덱스
     */
    public static void dfs(int cur) {
        if(cur == 99) {     // 도착
            answer = 1;
            return;
        }

        for(int i=0; i<2; i++) {
            if (arr[cur][i] == 0 || visited[cur][i]) {       // 길이 없거나 방문한 적 있음
                return;
            }
            visited[cur][i] = true;
            dfs(arr[cur][i]);
            visited[cur][i] = false;
        }
    }
}