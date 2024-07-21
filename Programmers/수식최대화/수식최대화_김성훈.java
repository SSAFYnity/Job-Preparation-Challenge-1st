import java.util.*;
import java.io.*;

class Solution {

    HashMap<Character, Integer> priority;
    Deque<Long> numberStack;
    Deque<Character> operatorStack;

    public long solution(String expression) {
        long answer = Long.MIN_VALUE;

        priority = new HashMap<>();
        numberStack = new ArrayDeque();
        operatorStack = new ArrayDeque();

        for (int i = 0; i < 3; i++) { // +
            priority.put('+', i);
            for (int j = 0; j < 3; j++) { // -
                if (i == j) continue;
                priority.put('-', j);
                for (int k = 0; k < 3; k++) { // *
                    if (i == k) continue;
                    if (j == k) continue;
                    priority.put('*', k);
                    System.out.println(priority.toString());
                    answer = Math.max(answer, Math.abs(process(expression)));
                    priority.remove('*');
                }
                priority.remove('-');
            }
            priority.remove('+');
        }

        return answer;
    }

    public long process(String expression) {

        long answer = 0;
        long number = 0;

        char[] expressionToCharArr = expression.toCharArray();

        for (char element : expressionToCharArr) {
            if ('0' <= element) { // 숫자인 경우
                number *= 10;
                number += element - '0';
                continue;
            }
            numberStack.addLast(number);

            // 연산자인 경우
            number = 0;
            while (!operatorStack.isEmpty() && priority.get(operatorStack.peekLast()) >= priority.get(element)) {
                long num1 = numberStack.pollLast();
                long num2 = numberStack.pollLast();
                char operator = operatorStack.pollLast();
                long res = calculate(num1, num2, operator);
                numberStack.addLast(res);
            }
            operatorStack.addLast(element);
        }

        numberStack.addLast(number); // 마지막 숫자 하나가 남아있는 상태
        while (!operatorStack.isEmpty()) {
            long num1 = numberStack.pollLast();
            long num2 = numberStack.pollLast();
            char operator = operatorStack.pollLast();
            long res = calculate(num1, num2, operator);
            numberStack.addLast(res);
        }


        answer = numberStack.pollLast();
        return answer;
    }

    public long calculate(long a, long b, char operator) {
        // 스택에서 가져온 값이므로 b 연산자 a 순으로 계산해야 함
        switch(operator) {
            case '-':
                return b - a;
            case '+':
                return b + a;
            case '*':
                return b * a;
            default:
                System.out.println("error : " + a + operator + b);
                return -1;
        }
    }
}