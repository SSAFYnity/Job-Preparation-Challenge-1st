import java.util.*;

class Solution {
	static long answer;
	static ArrayList<Long> numList = new ArrayList<>();
	static ArrayList<String> opList = new ArrayList<>();
	static String[] opArr = {"+", "-", "*"};
	static String[] selectedOp = new String[3];
	static boolean[] visited = new boolean[3];

	public long solution(String expression) {
		StringBuilder num = new StringBuilder();
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c == '*' || c == '-' || c == '+') {
				opList.add(c + "");
				numList.add(Long.parseLong(num.toString()));
				num = new StringBuilder();
			} else {
				num.append(c);
			}
		}

		// 마지막 숫자
		numList.add(Long.parseLong(num.toString()));

		permutation(0, opArr.length);
		return answer;
	}

	/**
	 * 연산자 수 만큼 순열만들기
	 */
	static void permutation(int depth, int r) {
		if (depth == r) {
			// 연산
			solve();
			return;
		}

		for (int i = 0; i < opArr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selectedOp[depth] = opArr[i];
				permutation(depth + 1, r);
				visited[i] = false;
			}
		}
	}

	static void solve() {
		ArrayList<String> oper = new ArrayList<>(opList);
		ArrayList<Long> nums = new ArrayList<>(numList);

		for (int i = 0; i < selectedOp.length; i++) {
			String curOp = selectedOp[i];

			for (int j = 0; j < oper.size(); j++) {
				if (oper.get(j).equals(curOp)) {
					long n1 = nums.get(j);
					long n2 = nums.get(j + 1);
					long res = cal(n1, n2, curOp);

					nums.remove(j + 1);
					nums.remove(j);
					oper.remove(j);

					nums.add(j, res);
					j--;
				}
			}
		}

		answer = Math.max(answer, Math.abs(nums.get(0)));
	}

	static long cal(long n1, long n2, String o) {
		switch (o) {
			case "+":
				return n1 + n2;
			case "-":
				return n1 - n2;
			case "*":
				return n1 * n2;
			default:
				return 0;
		}
	}
}