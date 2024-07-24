import java.util.*;
import java.util.stream.Collectors;

class Solution {

    static String[] exp;
    static Map<Integer, Integer> map;
    static List<String> list;
    static long[] arr;
    static int p;

    public void calculate(char giho) {

        while (true) {
            int idx = list.indexOf(giho + "");
            if (idx == -1) break;

            long a, b, result;
            a = Long.parseLong(list.get(idx-1));
            b = Long.parseLong(list.get(idx+1));

            if (giho == '*') result = a * b;
            else if (giho == '+') result = a + b;
            else result = a - b;

            list.remove(idx);
            list.remove(idx);
            list.set(idx-1, result+"");
        }


        return;
    }

    public long solution(String expression) {
        long answer = 0;

        exp = expression.split("[*+-]");

        char[] gihos = new char[] {'*', '+', '-'};

        for (char q : new char[] {'*', '+', '-'}) {

            for (char w : new char[] {'*', '+', '-'}) {
                if (q == w) continue;

                for (char e : new char[] {'*', '+', '-'}) {
                    if (q == e || w == e) continue;

                    list = new ArrayList<>();
                    int o = 0;
                    for (int i = 1; i < expression.length(); i++) {
                        char c = expression.charAt(i);
                        if (c == '*' || c == '+' || c == '-') {
                            list.add(expression.substring(o, i));
                            list.add(c+"");
                            o = i+1;
                        }
                    }
                    list.add(expression.substring(o));

                    // System.out.println(q + " " + w + " " + e + " " + list);
                    calculate(q);
                    // System.out.println(list);
                    calculate(w);
                    // System.out.println(list);
                    calculate(e);
                    // System.out.println(list);
                    answer = Math.max(answer, Math.abs(Long.parseLong(list.get(0))));
                }
            }
        }


        return answer;
    }


}