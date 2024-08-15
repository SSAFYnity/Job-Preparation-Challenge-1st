class Solution {
    private final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
    private int n;

    public int solution(int[][] clockHands) {

        int min = Integer.MAX_VALUE;
        n = clockHands.length;

        // Greedy
        for(int i = 0; i < (1 << (2*n)); i++) { // 첫 행 모든 경우의 수
            int[][] copy = deepCopy(clockHands);
            int cnt = 0;
            int temp = i;

            // 첫 행 조작
            for(int col = 0; col < n; col++) { // 오른쪽 부터 0~3 으로 돌려보기
                int rotateCnt = temp % 4;
                temp /= 4;
                cnt += rotateCnt;
                rotate(copy, 0, col, rotateCnt);
            }

            // 나머지 행 조작
            for(int row = 1; row < n; row++) {
                for(int col = 0; col < n; col++) {
                    int rotateCnt = (4 - copy[row-1][col]) % 4; // 바로 윗행 값을 0으로 만드는 회전수
                    cnt += rotateCnt;
                    rotate(copy, row, col, rotateCnt);
                }
            }

            // 마지막 행이 모두 0인지 확인
            boolean flag = true;
            for(int col = 0; col < n; col++) {
                if(copy[n-1][col] != 0)  {
                    flag = false;
                    break;
                }
            }
            if(flag) min = Math.min(min, cnt);
        }

        return min;
    }

    private void rotate(int[][] arr, int row, int col, int rotateCnt) {
        arr[row][col] = (arr[row][col] + rotateCnt) % 4; // 현재 좌표 돌림
        for(int[] d : dir) {
            int r = row + d[0];
            int c = col + d[1];
            if( 0 <= r && r < n && 0 <= c && c < n) {
                arr[r][c] = (arr[r][c] + rotateCnt) % 4; // 4방 좌표 돌림
            }
        }
    }

    private int[][] deepCopy(int[][] arr) {
        int[][] copy = new int[n][n];
        for(int i = 0; i < n; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, n);
        }
        return copy;
    }
}

/*
< 문제 정리 >
  - N*N 행렬에 시계들이 채워있음 (N : clockHands의 길이)
  - 시계는 상하좌우 4방향 (12시부터 시계방향 0,1,2,3)
  - 최소 조작으로 모든 방향이 0이 되도록 하기

< 풀이 계획 >
1) 알고리즘
    - DFS
        - 행렬의 상태 저장이 필요하므로 DFS 어떤가
        - 그럼 깊이의 최대는? -> 가늠할 수 없는 것 같으니 포기
    - 그리디**
        - 돌렸을 때 12시가 되는 바늘이 많으면 +1, 주변에 0 개수는 -1
    - BFS
        - 방문체크를 2차원 배열로?
        - 최대 64칸을 4가지 경우의수 : 4^64 = 너무큼
    - 역방향 탐색
        - 모두 12시 상태에서 기존 상태 만들기
        - 잘 모르겠음
    - 그리디 ---- 정답 풀이 2개맞고 나머지 시간초과 나서 풀이 봄
        - >> 조작 순서가 중요하지 않으므로 규칙을 정해 한 방향으로 조작하면 됨 <<
        - 위에 첫 행을 임의조작하고, 다음 행에서 윗 줄이 0이 되도록 조작
        - 마지막 행까지 완료했을 때 모두 0이 됐는지 확인
        - 첫 행의 경우의 수만 따지면 되므로, 브루트포스 4^64에서 -> 그리디 4^8로 줄어듦

2) 메서드
    - 일단 돌리는건 1회당 시계방향 1칸
    - 인접한 인덱스도 회전 하는 기능 필요
*/

/*
오답 풀이

class Solution {
    private final int[] dx = {0, -1, 0, 1, 0}; // 중앙 상 우 하 좌 (시계방향)
    private final int[] dy = {0, 0, 1, 0, -1};

    public int solution(int[][] clockHands) {
        int total = 0;
        for(int[] a : clockHands) {
            for(int i : a) {
                total += i;
            }
        }

        int answer = 0;
        int n = clockHands.length;

        // Greedy
        while(total > 0) { // 모든 합이 0이 되면 전부 12시
            // 회전 점수가 높은 좌표 찾기
            //    - 3을 돌리면 10점
            //    - 1, 2를 돌리면 1점
            //    - 0을 돌리면 -10점
            int row = 0;
            int col = 0;
            int max = 0;

            // 회전 점수 최댓값 좌표 찾기
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    int sum = 0;
                    for(int k = 0; k < 5; k++) {
                        int r = i + dx[k];
                        int c = j + dy[k];
                        if(0 > r || r >= n || 0 > c || c >= n) {
                            continue;
                        }
                        switch(clockHands[r][c]) {
                            case 0:
                                sum -= 10;
                                break;
                            case 3:
                                sum += 10;
                                break;
                            case 1:
                            case 2:
                                sum += 1;
                        }
                    }
                    if(sum > max) {
                        max = sum;
                        row = i;
                        col = j;
                    }
                }
            }

            // 회전하기
            for(int k = 0; k < 5; k++) {
                int r = row + dx[k];
                int c = col + dy[k];
                if(0 > r || r >= n || 0 > c || c >= n) {
                    continue;
                }
                clockHands[r][c] = (clockHands[r][c] + 1) % 4;
                total += clockHands[r][c]==0 ? -3 : 1;
            }

            answer++;
        }

        return answer;
    }
}

*/