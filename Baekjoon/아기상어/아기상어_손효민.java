import java.io.*;
import java.util.*;

public class Main {

	static int N, ans, sharkSize;
	static int[][] map;
	static Queue<Shark> queue;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		ans = 0; //정답
		sharkSize = 2; //2로 초기화
		map = new int[N][N];
		queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					queue.offer(new Shark(i, j, 0));
					map[i][j] = 0;
				}
			}
		}
		go();
	}
	
	private static void go() {
		int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};
		int eat = 0; //물고기를 먹은 횟수
		
		while(true) {
			ArrayList<Shark> Fish = new ArrayList<>(); // 먹을 수 있는 물고기들을 담는 배열
			int[][] len = new int[N][N]; //
			
			// 상어가 먹을 수 있는 물고기들을 Fish 리스트에 담음
			while(!queue.isEmpty()) {
				Shark s = queue.poll();
				int x = s.x;
				int y = s.y;
				int length = s.len;
				
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(nx<0 || nx>=N || ny<0 || ny>=N)
						continue;
					if(len[nx][ny] == 0 && map[nx][ny] <= sharkSize) {
						// 한번도 방문하지 않았고, 다음 물고기의 크기가 상어 사이즈보다 같거나작다면 지나갈 수 있음
						
						len[nx][ny] = len[x][y] + 1; // 아기 상어 위치에서 1 증가
						queue.add(new Shark(nx, ny, len[nx][ny])); //새로 상어의 위치와 크기를 넣어줌
						
						 if (1 <= map[nx][ny] && map[nx][ny] <= 6 && map[nx][ny] < sharkSize) {
                             Fish.add(new Shark(nx, ny, len[nx][ny]));
                             // 물고기 크기 1에서 6사이이고 크기가 상어사이즈보다 작다면 먹을 수 있는 것!
                             // 물고기 리스트에 넣어줌
                         }
					}
				}
			}
			
			// 만약, 먹을 수 있는 물고기가 없다면 이동한 거리, 시간을 출력함
			if (Fish.size() == 0) {
                System.out.println(ans);
                return;
            }
			
			// 먹을 수 있는 물고기가 있다면
			// 제일 거리 가깝고 -> 가장 위 -> 가장 왼쪽 순으로 구함
			Shark fish = Fish.get(0); //비교 할 가장 처음의 물고기 꺼냄
			for (int i = 1; i < Fish.size(); i++) { // 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다
				if(fish.len > Fish.get(i).len) {
					//거리가 더 가깝다면 갱신
					fish = Fish.get(i);
				}else if(fish.len == Fish.get(i).len) {
					//거리 가까우면 => 가장 위
					if(fish.x > Fish.get(i).x) {
						fish = Fish.get(i);
					}else if (fish.x == Fish.get(i).x) {
						//거리 가깝고 가장 위 => 가장 왼쪽
						if(fish.y > Fish.get(i).y)
							fish = Fish.get(i);
					}
				}
			}
			
			map[fish.x][fish.y] = 0; //물고기를 먹었으니까 전체 배열 초기화
			ans += fish.len; 
			
			eat++; // 물고기 먹은 횟수 증가
			if(sharkSize == eat) { // 상어 사이즈만큼 먹었으면
				sharkSize++; //상어사이즈 증가
				eat = 0; //먹은횟수 초기화
			}
			queue.add(fish); //다시 상어 위치 옮기기
		}
	}
    
	static class Shark{
		int x;
		int y;
		int len;
		public Shark(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
}
