import java.util.*;

class Solution {
    public long solution(String expression) {
        long answer = 0;
        ArrayList<Long> numbers = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        // 수식 토큰화
        StringBuilder num = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '*') {
                numbers.add(Long.parseLong(num.toString()));
                operators.add(c);
                num = new StringBuilder();
            } else {
                num.append(c);
            }
        }
        numbers.add(Long.parseLong(num.toString()));

        // 가능한 모든 연산자 우선순위 조합
        char[][] priorities = {{'+', '-', '*'}, {'+', '*', '-'},
                {'-', '+', '*'}, {'-', '*', '+'},
                {'*', '+', '-'}, {'*', '-', '+'}};

        // 각 우선순위 조합에 대해 계산
        for (char[] priority : priorities) {
            ArrayList<Long> tempNumbers = new ArrayList<>(numbers);
            ArrayList<Character> tempOperators = new ArrayList<>(operators);

            for (char op : priority) {
                for (int i = 0; i < tempOperators.size(); i++) {
                    if (tempOperators.get(i) == op) {
                        long result = calculate(tempNumbers.remove(i), tempNumbers.remove(i), op);
                        tempNumbers.add(i, result);
                        tempOperators.remove(i);
                        i--;
                    }
                }
            }

            answer = Math.max(answer, Math.abs(tempNumbers.get(0)));
        }

        return answer;
    }

    private long calculate(long a, long b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default: return 0;
        }
    }
}