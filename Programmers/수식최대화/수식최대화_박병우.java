import java.util.LinkedList;
import java.util.List;

public class 수식최대화 {
	// Programmers 수식최대화
	
	static String[] operation = {"+" , "-" , "*"};
	static boolean[] check = new boolean [3];
	static long result = 0;
	
	public static void main(String[] args) {
		long result = solution("50*6-3*2");
		System.out.println(result);
	}
	
	public static long solution(String expression) {
        long answer = 0;
        
        comb(0, expression, new String[3]);
        answer = result;
        
        return answer;
    }

	private static void comb(int cnt, String expression, String[] arr) {
		if (cnt == 3) {
			calc(expression, arr, 0);
		}
		
		for (int i = 0; i < 3; i++) {
			if (!check[i]) {
				check[i] = true;
				arr[cnt] = operation[i];
				comb(cnt+1, expression, arr);
				check[i] = false;
			}
		}
	}

	private static void calc(String expression, String[] arr, int cnt) {
		List<String> operlist = new LinkedList<String>();
		List<Long> numlist = new LinkedList<Long>();
		
		String temp = "";
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
				operlist.add(String.valueOf(expression.charAt(i)));
				numlist.add(Long.parseLong(temp));
				temp = "";
			} else {
				temp += expression.charAt(i);
			}
		}
		numlist.add(Long.parseLong(temp));
		
		for (int i = 0; i < 3; i++) {
			while (true) {
				int index = operlist.indexOf(arr[i]);
				
				if (index != -1) {
					switch (arr[i]) {
					case "+":
						numlist.add(index, numlist.get(index) + numlist.get(index+1));
						break;
					case "-":
						numlist.add(index, numlist.get(index) - numlist.get(index+1));
						break;
					case "*":
						numlist.add(index, numlist.get(index) * numlist.get(index+1));
						break;
					}
					
					numlist.remove(index + 1);
					numlist.remove(index + 1);
					operlist.remove(index);
				} else {
					break;
				}
			}
		}
		
//		System.out.println(arr[0]);
//		for (int i = 0; i < operlist.size(); i++) {
//			System.out.print(operlist.get(i) + " ");
//		}
//		for (int i = 0; i < numlist.size(); i++) {
//			System.out.print(numlist.get(i) + " ");
//		}
		
		result = Math.abs(numlist.get(0)) > result ? Math.abs(numlist.get(0)) : result;
	}
	
}
