package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1219_길찾기 {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = 10;

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int route = Integer.parseInt(st.nextToken());

			boolean[][] adjustList = new boolean[100][100];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < route*2; i=i+2) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				adjustList[start][end] = true;
				
			}
			
			
			sb.append('#').append(t).append(' ').append(BFS(adjustList)).append('\n');
			
			

		}
		System.out.println(sb);

	}

	public static int BFS(boolean[][] matrix) {
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(0);
		
		boolean [] visited = new boolean[100];
		visited[0]=true;
		
		while(!q.isEmpty()) 
		{
			int cur = q.poll();
			
			if(cur==99)
				return 1;
			
			for(int i=1;i<100;i++) 
			{
				boolean next = matrix[cur][i];
				
				if(next) 
				{
					q.offer(i);
					visited[i]=true;
				}
			}
		}
		
		
		
		return 0;
	}

}
