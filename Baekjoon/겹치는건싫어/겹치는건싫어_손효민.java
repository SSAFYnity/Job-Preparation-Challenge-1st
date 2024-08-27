import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		int ans = 0; // 같은 원소가 K개 이하로 들어 있는 최장 연속 부분 수열의 길이
		int[] count = new int[100001];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left=0, right=0;
		while(right<N) {
			count[arr[right]]++; // 오른쪽 포인터가 가리키는 수 빈도 증가
			
			while(count[arr[right]]>K) {
				//만약 현재 수 빈도수가 K초과 왼쪽 포인터가 가리키는 수 빈도 감소
				count[arr[left]]--;
				left++;
			}
			
			ans = Math.max(ans, right-left+1);
			right++;			
		}
		System.out.println(ans);
	}
}
