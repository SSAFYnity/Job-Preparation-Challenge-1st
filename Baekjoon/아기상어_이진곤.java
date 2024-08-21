import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어_이진곤 {
	
	static int N;
	static int shark = 2;
	static int duration = 0;
	static int[][] map;
	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		Place sharkPlace = new Place(-1, -1, -1);
		for (int y = 0; y < N; y++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 9)
					sharkPlace = new Place(y, x, 0);
			}
		}
		
		findFeed(sharkPlace);
		System.out.println(duration);
	}
	
	static void findFeed(Place start) {
		boolean[][] visited = new boolean[N][N];
		Queue<Place> queue = new LinkedList<>();
		queue.add(start);
		visited[start.y][start.x] = true;
		
		while(!queue.isEmpty()) {
			Place p = queue.poll();
			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx])
					continue;
				
				if (map[ny][nx] == 0 || map[ny][nx] == shark) {
					queue.offer(new Place(ny, nx, p.level + 1));
					visited[ny][nx] = true;
				}
				
				else if (map[ny][nx] < shark) {	// 먹이 발견
					map[ny][nx] = 9;
					duration += p.level;
					queue.clear();
					findFeed(new Place(p.y, p.x, 0));
					break;
				}
			}
		}
	}
	
	static class Place {
		int y;
		int x;
		int level;
		
		public Place(int y, int x, int level) {
			this.y = y;
			this.x = x;
			this.level = level;
		}
	}
}