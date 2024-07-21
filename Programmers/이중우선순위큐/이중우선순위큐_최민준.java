import java.util.*;

class Solution {
	public int[] solution(String[] operations) {
		int[] answer = {0,0};
		PriorityQueue<Integer> maxQ = new PriorityQueue<>((n1, n2) -> n2 - n1);
		PriorityQueue<Integer> minQ = new PriorityQueue<>();

		for (int i = 0; i < operations.length; i++) {
			String[] str = operations[i].split(" ");
			int num = Integer.parseInt(str[1]);

			if ("I".equals(str[0])) {
				maxQ.offer(num);
				minQ.offer(num);
				continue;
			}

			// 빈큐인경우 연산 무시
			if (maxQ.isEmpty() && minQ.isEmpty())
				continue;

			// 최대값 삭제
			if (num == 1) {
				int n = maxQ.poll();
				minQ.remove(n);
			} else { // 최소값 삭제
				int n = minQ.poll();
				maxQ.remove(n);
			}
		}

		if (!maxQ.isEmpty() && !minQ.isEmpty()){
			answer[0] = maxQ.poll();
			answer[1] = minQ.poll();
		}

		return answer;
	}
}
