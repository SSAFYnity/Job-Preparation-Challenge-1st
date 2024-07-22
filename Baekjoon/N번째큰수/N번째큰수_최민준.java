import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((n1,n2) -> n2-n1);

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 1; i < N; i++) {
			pq.poll();
		}
		System.out.println(pq.poll());
	}

}