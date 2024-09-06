import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int sum = 0;
        String[] arr = s.split("[+-]");
        int[] num = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            num[i] = Integer.parseInt(arr[i]);
        }
        ArrayList<Character> oper = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                oper.add(s.charAt(i));
            }
        }
        boolean flag = true;
        int i = 0, j = 0;
        if (num.length != oper.size()) {
            sum += num[0];
            i = 1;
        }
        for (; i < num.length; i++, j++) {
            if (oper.get(j) == '-') {
                flag = false;
            }
            if (flag) {
                sum += num[i];
            } else {
                sum -= num[i];
            }
        }

        System.out.println(sum);
    }
}