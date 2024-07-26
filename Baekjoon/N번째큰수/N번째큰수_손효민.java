import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args)  throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				
				if(pq.size()<N){
					pq.add(input);
				}else {
					if(pq.peek()<input) {
						pq.poll();
						pq.add(input);
					}
				}
			}
		}
		System.out.println(pq.peek());
	}
}
