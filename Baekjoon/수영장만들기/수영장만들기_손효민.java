import java.io.*;
import java.util.*;

public class Main {

	static int N, M, max;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0}, dy= {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		
		for(int i=0;i<N;i++) {
			String[] str = br.readLine().split("");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(str[j]);
				max = Math.max(max, map[i][j]);
			}
		}
		
		int ans=0;
		for(int num=2;num<=max;num++) { // 2부터 가능한 높이 칸 수를 모두 합하기
			visit = new boolean[N][M];
			
			for(int i=1;i<N-1;i++) {
				for(int j=1;j<M-1;j++) {
					
					if(map[i][j]<num && !visit[i][j]) {
						int sum = bfs(i, j, num);
						
						if(sum != -1) {
							ans += sum;
						}
					}
				}
			}
		}
		
		System.out.println(ans);
	}

	private static int bfs(int r, int c, int num) {
		Queue<int[]> q = new LinkedList<>();
		
		int cnt=0;
		boolean flag = true;
		visit[r][c] = true;
		q.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.poll()[1];
			
			cnt++;
			if (x == N - 1 || y == M - 1 || x == 0 || y == 0) {
                if (map[x][y] < num) {
                    flag = false; // 가장 바깥 벽이 현재 높이보다 낮으면 -> 물이 흘러넘침.
                }
            }			
			
			for(int d=0;d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(nx<0||ny<0||nx>=N||ny>=M||visit[nx][ny]) continue;								
				if(map[nx][ny] >= num) continue; // 자기보다 높이가 같거나 높은 곳은 통과
				
				q.add(new int[] {nx, ny});
				visit[nx][ny] = true;
			}
		}
		
		if(!flag) cnt=-1;
		return cnt;
	}
}
