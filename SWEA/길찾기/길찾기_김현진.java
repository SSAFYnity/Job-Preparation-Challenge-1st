import java.io.*;
import java.util.*;

public class 길찾기_김현진 {

	static int n;
	static int[][] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int test_case = 1; test_case <= 10; test_case++) {
			st = new StringTokenizer(sc.readLine());
			st.nextToken();
			n = Integer.parseInt(st.nextToken());

			graph = new int[2][100];
			visited = new boolean[100];

			st = new StringTokenizer(sc.readLine());
			for (int i = 0; i < n; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (graph[0][a] == 0)
					graph[0][a] = b;
				else
					graph[1][a] = b;
			}
			System.out.printf("%s%d %d\n", "#", test_case, BFS() ? 1 : 0);
		}
	}

	static boolean BFS() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.offer(0);
		visited[0] = true;

		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int i = 0; i < 2; i++) {
				int next = graph[i][now];

				if (next == 99)
					return true;
				if (!visited[next]) {
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
		return false;
	}
}
