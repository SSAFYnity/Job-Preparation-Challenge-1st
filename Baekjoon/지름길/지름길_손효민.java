import java.io.*;
import java.util.*;

public class Main {
	
	static int N, D, min;
	static List<Road> roads[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		min = D;
		roads = new ArrayList[D+1];
		for(int i=0;i<=D;i++) roads[i] = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			if(e <= D) roads[s].add(new Road(e, len)); // 범위의 안에 있을 때만 추가
		}
		
		func(0);
	}
	
	private static void func(int start) {
		int[] dist = new int[D+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		
		PriorityQueue<Road> pq = new PriorityQueue<>();
		pq.add(new Road(0, 0));
		
		while(!pq.isEmpty()) {
			Road cur = pq.poll();
			
			if(cur.len > dist[cur.next]) continue;
			
			// 1. 한칸 뒤인 다음 지점으로 이동
			if(cur.next+1 <= D && cur.len+1 < dist[cur.next+1]) {
				// 한칸 뒤가 범위 안에 존재하고, 그 지점의 가중치가 dist배열의 값보다 작을 때==최소값일때
				dist[cur.next+1] = cur.len+1;
				pq.add(new Road(cur.next+1, dist[cur.next+1]));
			}
			
			// 2. 지름길로 이동
			for(Road r : roads[cur.next]) {
				int nv = r.next; // 지름길 도착 위치
				int nw = r.len+cur.len; //지름길까지 비용+지금까지 온 비용
				
				if(nw < dist[nv]) {
					dist[nv] = nw;
					pq.add(new Road(nv, dist[nv]));
				}
			}
		}
		
		System.out.println(dist[D]);		
	}

	private static class Road implements Comparable<Road> {
		int next, len;
		
		public Road(int next, int len) {
			this.next = next;
			this.len = len;
		}
		
		@Override
		public int compareTo(Road o) {
			return this.len-o.len;
		}
	}
}
