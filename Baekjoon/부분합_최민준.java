import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 1;
		int answer = Integer.MAX_VALUE;

		int sum = arr[start];
		/*
		1. start(0), end (1) 인덱스 시작
		2. start ~ end 각 요소들의 합을 더함 -> 더해서 S 이상일때
		  - true: end - start + 1 (길이)
		    2-1. 최소값 갱신
		  - false: end++
		 */
		while (start <= N && end <= N) {
			if (sum >= S && answer > end - start)
				answer = end - start;
			if (sum < S) {
				sum += arr[end++];
			} else {
				sum -= arr[start++];
			}
		}

		System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
	}

}
