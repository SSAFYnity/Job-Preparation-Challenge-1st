import java.io.*;
import java.util.*;
 
public class Solution {

	static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1;tc<=10;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int TC = Integer.parseInt(st.nextToken()); // 테스트케이스
			int M = Integer.parseInt(st.nextToken()); // 순서쌍의 개수
			arr = new int[2][100]; // 한 개의 정점에서 선택할 수 있는 길의 개수도 2개를 넘어가지 않는다.
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if(arr[0][x] == 0) {
					arr[0][x] = y;
				}else {
					arr[1][x] = y;
				}
			}
			
			int ans = bfs();
			System.out.println("#"+TC+" "+ans);
		}
	}
	
	public static int bfs() {
		boolean flag = false; // 0에서 99까지 갈 수 있는지 확인하는 변수
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		
		while(!q.isEmpty()) {
			int x = q.poll();
			if(x == 99) {
				flag = true;
				break;
			}			
			for(int i=0;i<2;i++) {
				if(arr[i][x] != 0) q.add(arr[i][x]);
			}
		}
		// 가능할 경우 1, 불가능할 경우 0
		if(flag) return 1;
		else return 0;
	}

}
