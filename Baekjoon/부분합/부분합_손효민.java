import java.util.*;
import java.io.*;

public class Main {

	static int N, start, end, sum;
	static long S, ans;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Long.parseLong(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = 100000000;
		start = 0;
		end = 0;
		sum = 0;
		
		while(true) {
			if(sum >= S) {
				sum -= arr[start++];
				
				int res = end - start +1;
				ans = Math.min(ans, res);
			}
			else if(end == N) break;
			else if(sum < S) {
				sum += arr[end++];
			}
		}
		if(ans == 100000000) System.out.println(0);
		else	System.out.println(ans);	
	}
}
