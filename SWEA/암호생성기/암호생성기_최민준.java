import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception{
        /*
        1. 배열 0번째 원소를 뽑아 차례로 1...5 증가하며 감소
          - 1-5 까지 감소했으면 다시 처음부터 1부터 5까지 => 1사이클
        2. 감소했을때 0보다 작거나 같으면 프로그램 종료
          - 작지 않으면 맨 뒤로 보내기
         */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			String testCaseNum = br.readLine();
			Deque<Integer> deque = new LinkedList<>();

			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<8; j++) {
				deque.offer(Integer.parseInt(st.nextToken()));
			}

			solution(deque);
			sb.append('#').append(testCaseNum).append(' ');
			while (!deque.isEmpty()) {
				sb.append(deque.poll()).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void solution(Deque deque) {
		int diff = 1;
		while (true) {
			int calc = Math.max((int) deque.pollFirst() - diff++, 0);
			deque.offerLast(calc);
			if (calc == 0) break;
			if (diff == 6) diff = 1;
		}
	}
}