import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    private static long ans;
    private static ArrayList<Long> nums;
    private static ArrayList<Character> ops;
    private static char[][] precedences = {
            {'+', '-', '*'},
            {'+', '*', '-'},
            {'-', '+', '*'},
            {'-', '*', '+'},
            {'*', '+', '-'},
            {'*', '-', '+'}
    };

    public long solution(String expression) {
        nums = new ArrayList<>();
        ops = new ArrayList<>();

        // 정규 표현식 패턴: 숫자와 연산자를 매칭
        Pattern pattern = Pattern.compile("\\d+|[-+*/]");
        Matcher matcher = pattern.matcher(expression);

        // 매칭되는 모든 요소를 리스트에 추가
        while (matcher.find()) {
            String token = matcher.group();
            if (Character.isDigit(token.charAt(0))) {
                nums.add(Long.parseLong(token));
            } else {
                ops.add(token.charAt(0));
            }
        }

        long maxAbsResult = 0;
        for (char[] precedence : precedences) {
            long result = calculateExpression(new ArrayList<>(nums), new ArrayList<>(ops), precedence);
            maxAbsResult = Math.max(maxAbsResult, Math.abs(result));
        }

        return maxAbsResult;
    }

    private long calculateExpression(List<Long> nums, List<Character> ops, char[] precedence) {
        for (char op : precedence) {
            for (int i = 0; i < ops.size(); ) {
                if (ops.get(i) == op) {
                    long result = applyOperation(nums.remove(i), nums.remove(i), op);
                    nums.add(i, result);
                    ops.remove(i);
                } else {
                    i++;
                }
            }
        }
        return nums.get(0);
    }

    private long applyOperation(long a, long b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return -1;
    }
}