import java.util.*;

class Solution {
    
    String[] literal = { "+", "-", "*" };
    
    String[] priority = new String[3];
    boolean[] visited = new boolean[3];

    String[] strNumbers;
    String[] strOperations;
    ArrayDeque<Long> numbers;
    ArrayDeque<String> operators;
    
    long answer = 0;
    
    public long solution(String expression) {
        
        strNumbers = expression.split("\\D");
        strOperations = expression.replaceAll("\\d+", "").split("");
        
        perm(0);

        return answer;
    }
    
    private void perm(int cnt) {
        
        if (cnt == 3) {
            init();
            answer = Math.max(answer, getResult());
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            priority[cnt] = literal[i];
            perm(cnt + 1);
            visited[i] = false;
        }
    }

    private void init() {
      numbers = new ArrayDeque<>();
      for (int i = 0; i < strNumbers.length; i++) {
        numbers.offer(Long.parseLong(strNumbers[i]));
      }
      operators = new ArrayDeque<>();
      for (int i = 0; i < strOperations.length; i++) {
        operators.offer(strOperations[i]);
      }
    }
    
    private long getResult() {
        for (int i = 0; i < priority.length; i++) {
            ArrayDeque<Long> newNumbers = new ArrayDeque();
            ArrayDeque<String> newOperators = new ArrayDeque<>();
            newNumbers.offer(numbers.poll());
            while(!operators.isEmpty()) {
                String op = operators.poll();
                // 우선순위에 해당하는 연산자 -> 연산자 앞/뒤의 숫자 계산 후 deque에 추가
                if (op.equals(priority[i])) {
                    long arg1 = newNumbers.pollLast();
                    long arg2 = numbers.poll();
                    newNumbers.offer(calculate(arg1, arg2, op));
                    continue;
                }
                // 우선순위에 해당하는 연산자 -> 연산자 뒤의 숫자 deque에 추가
                newNumbers.offer(numbers.poll());
                newOperators.offer(op);
            }
            numbers = newNumbers;
            operators = newOperators;
        }
        return Math.abs(numbers.poll());
    }
    
    private long calculate(long a, long b, String op) {
        
        return switch(op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> throw new IllegalArgumentException();
        };
    }
}