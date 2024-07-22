import java.util.*;

class Solution {
    private List<Long> nums;
	private List<String> operations;
	private Set<String> operationSet;
	private List<String> operationKinds;
	private int[] selectedOperation;
	private boolean[] isSelected;
	private long result;

	private void makePermutation(int depth) {
		if (depth == operationSet.size()) {
			calcExpression();
			return;
		}

		for (int i = 0; i < operationSet.size(); i++) {
			if (isSelected[i]) {
				continue;
			}
			isSelected[i] = true;
			selectedOperation[depth] = i;
			makePermutation(depth + 1);
			isSelected[i] = false;
		}
	}

	private void calcExpression() {
		List<Long> copyNums = new ArrayList<>(nums);
		List<String> copyOperations = new ArrayList<>(operations);
		for (int i = 0; i < operationKinds.size(); i++) {
			String operation = operationKinds.get(selectedOperation[i]);
			for (int j = 0; j < copyOperations.size(); j++) {
				if (operation.equals(copyOperations.get(j))) {
					copyNums.set(j, (calcOperation(copyNums.get(j), copyNums.get(j + 1), operation)));
					copyNums.remove(j + 1);
					copyOperations.remove(j);
					j--;
				}
			}
		}
		result = Math.max(result, Math.abs(copyNums.get(0)));
	}

	private long calcOperation(long n1, long n2, String operation) {
		switch (operation) {
			case "*":
				return n1 * n2;
			case "+":
				return n1 + n2;
			case "-":
				return n1 - n2;
			default:
				return Integer.MAX_VALUE;
		}
	}

	private void init() {
		nums = new ArrayList<>();
		operations = new ArrayList<>();
		selectedOperation = new int[3];
		isSelected = new boolean[3];
		result = 0;
	}

    public long solution(String expression) {
        StringTokenizer st;
		init();

		// expression -> 숫자, 연산자 구분
		operationSet = new HashSet<>();
		st = new StringTokenizer(expression, "*+-", true);
		while (st.hasMoreTokens()) {
			String cur = st.nextToken();
			if (cur.equals("*") || cur.equals("+") || cur.equals("-")) {
				operationSet.add(cur);
				operations.add(cur);
			} else {
				nums.add(Long.parseLong(cur));
			}
		}

		operationKinds = new ArrayList<>(operationSet);
		makePermutation(0);

		return result;
    }
}