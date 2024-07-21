import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tcNum = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			boolean[] visited = new boolean[100];
			List[] list = new List[100];
			for (int j = 0; j < 100; j++) {
				list[j] = new ArrayList<Integer>();
			}

			st = new StringTokenizer(br.readLine());
			// 순서 쌍 개수만큼 반복
			for (int j = 0; j < cnt; j++) {
				int idx = Integer.parseInt(st.nextToken());
				int targetIdx = Integer.parseInt(st.nextToken());
				list[idx].add(targetIdx);
			}
			sb.append('#').append(tcNum).append(' ');

			// solution
			int answer = solution(list, visited);
			sb.append(answer).append('\n');
		}

		System.out.println(sb);
	}

	static int solution(List[] list, boolean[] visited) {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i=0; i<list[0].size(); i++) {
			queue.offer((Integer) list[0].get(i));
		}
		visited[0] = true;

		while (!queue.isEmpty()) {
			int curIdx = queue.poll();
			visited[curIdx] = true;

			ArrayList<Integer> getList = (ArrayList<Integer>) list[curIdx];
			for (int i = 0; i < getList.size(); i++) {
				int getTarget = getList.get(i);

				if (getTarget == 99) return 1;
				if (visited[getTarget]) continue;
				queue.offer(getTarget);
			}
		}

		return 0;
	}
}