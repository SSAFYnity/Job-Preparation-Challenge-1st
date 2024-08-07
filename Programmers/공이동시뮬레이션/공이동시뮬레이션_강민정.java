class Solution {
    /*
        1 <= 격자의 행의 개수 n <= 10^9
        1 <= 격자의 열의 개수 m <= 10^9
        0 <= 도착 행 x < n
        0 <= 도착 열 y < m
        1 <= queries의 행의 개수 <= 200,000
    */
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long top = x;       // 맨 위 행
        long bottom = x;    // 맨 아래 행
        long left = y;      // 맨 왼쪽 열
        long right = y;     // 맨 오른쪽 열
        
        for(int i = queries.length - 1; i >= 0; i--) {        // 쿼리를 거꾸로 실행
            int cmd = queries[i][0];        // 방향
            int distance = queries[i][1];   // 이동하는 칸 수
            
            if(cmd == 0) {      // 열 번호가 감소하는 방향으로 distance만큼 이동
                if(left != 0) {     // 0이면 감소시키면 격자 바깥으로 나가니까
                    left += distance;
                }
                right = Math.min(m-1, right + distance);
            } else if(cmd == 1) {   // 열 번호가 증가하는 방향으로 distance만큼 이동
                if(right != m-1) {      // 맨 오른쪽에 있는 열이면 증가시켰을 때 격자 바깥으로 나가니까
                    right -= distance;
                }
                left = Math.max(0, left - distance);
            } else if(cmd == 2) {   // 행 번호가 감소하는 방향으로 distance만큼 이동
                if(top != 0) {      // 맨 위에 있는 행이면 감소시켰을 때 격자 바깥으로 나가니까
                    top += distance;
                }
                bottom = Math.min(n-1, bottom + distance);
            } else {                // 행 번호가 증가하는 방향으로 distance만큼 이동
                if(bottom != n-1) {     // 맨 아래에 있는 행이면 증가시켰을 때 격자 바깥으로 나가니까
                    bottom -= distance;
                }
                top = Math.max(0, top - distance);   
            }

            // 범위가 격자를 벗어나면 더 이상 유효한 시작점이 없음
            if (left >= m || right < 0 || top >= n || bottom < 0) {
                return 0;
            }
        }
        
        // 최종적으로 가능한 시작점의 범위가 격자 내에 있다면 개수를 반환
        return (right - left + 1) * (bottom - top + 1);
    }
} 
