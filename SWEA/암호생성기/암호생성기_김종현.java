import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		for (int TC = 1; TC <= 10; TC++) {
			br.readLine();

			// 입력
			Queue<Integer> q = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < 8; i++)
				q.offer(Integer.parseInt(st.nextToken()));

			// 사이클 반복
			outer: while (true) {
				for (int i = 1; i <= 5; i++) {
					int n = q.poll() - i;
					if (n <= 0) {
						n = 0;
						q.offer(n);
						break outer;
					}
					q.offer(n);
				}
			}

			sb.append("#"+TC);
			while (q.size() > 0) {
				sb.append(" " + q.poll());
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
