import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		String result = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1; t<=10; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			int testNum = Integer.parseInt(stk.nextToken());
			int totalCnt = Integer.parseInt(stk.nextToken());
			
			ArrayList<Integer>[] graph = new ArrayList[99];
			for(int i=0; i<99; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			
			stk = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<totalCnt; i++) {
				int from = Integer.parseInt(stk.nextToken());
				int to = Integer.parseInt(stk.nextToken());
				graph[from].add(to);
			}
			result += String.format("#%d %d\n", testNum, bfs(graph));
		}
		System.out.print(result);
	}
	
	public static int bfs(ArrayList<Integer>[] graph) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[99];
		queue.offer(0);
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int next : graph[cur]) {
				if(next==99) {
					return 1;	
				}
				
				if(!visited[next]) {
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
		return 0;
	}
}
