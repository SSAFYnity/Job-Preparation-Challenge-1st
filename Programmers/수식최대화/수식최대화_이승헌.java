package 수식최대화;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class 수식최대화_이승헌 {
    static final String[] OPERATIONS = new String[]{"+", "-", "*"};
    static long[] numbers;
    static String[] operation;
    static long result = -1;

    public static void main(String[] args) {
        result = -1;
        solution("100-200*300-500+20"); // 60420
        System.out.println(result);
        result = -1;
        solution("50*6-3*2"); // 300
        System.out.println(result);

    }

    public static long solution(String expression) {
        long answer = 0;

        numbers = Arrays.stream(expression.split("[\\+\\-\\*]"))
                .mapToLong(Long::parseLong)
                .toArray();
        operation = expression.split("[0-9]+");

        Queue<String> queOper = new ArrayDeque<>();
        Arrays.stream(operation).forEach(queOper::offer);
        queOper.poll();
        Queue<Long> queNum = new ArrayDeque<>();
        Arrays.stream(numbers).forEach(queNum::offer);

        solve(queOper, queNum, 0);
        return result;
    }

    private static void solve(Queue<String> queOper, Queue<Long> queNum, int bit) {

        if (bit == 7) {
            result = Math.max(result, Math.abs(queNum.peek()));
            return;
        }

        int size = queOper.size();

        for (int idx = 0; idx < 3; idx++) {
            Queue<String> copyOper = new ArrayDeque<>(queOper);
            Queue<Long> copyNum = new ArrayDeque<>(queNum);

            if ((bit & (1 << idx)) == 0) { // plus
                bit |= (1 << idx);
                getBit(copyOper, copyNum, size, idx);
                solve(new ArrayDeque<>(copyOper), new ArrayDeque<>(copyNum), bit);
                bit ^= (1 << idx);
            }
        }
    }

    private static void getBit(Queue<String> copyOper, Queue<Long> copyNum, int size, int idx) {
        long curNum;
        Queue<String> tempOper = new ArrayDeque<>();
        Queue<Long> tempNum = new ArrayDeque<>();

        curNum = copyNum.poll();
        for (int i = 0; i < size; i++) {
            String oper = copyOper.poll();

            if (!oper.equals(OPERATIONS[idx])) {
                tempOper.add(oper);
                tempNum.add(curNum);
                curNum = copyNum.poll();
                continue;
            }

            switch (idx) {
                case 0 :
                    curNum += copyNum.poll();
                    continue;
                case 1 :
                    curNum -= copyNum.poll();
                    continue;
                case 2 :
                    curNum *= copyNum.poll();
                    continue;
            }

        }
        tempNum.add(curNum);

        copyOper.addAll(tempOper);
        copyNum.addAll(tempNum);
    }
}
