package 잃어버린괄호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 잃어버린괄호_이승헌 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        StringTokenizer sub = new StringTokenizer(br.readLine(), "-");
        sum += sumParts(sub.nextToken());

        while (sub.hasMoreTokens()) {
            sum -= sumParts(sub.nextToken());
        }
        System.out.println(sum);
    }

    private static int sumParts(String part) {
        int sum = 0;
        StringTokenizer st = new StringTokenizer(part, "+");;
        while (st.hasMoreTokens()) {
            sum += Integer.parseInt(st.nextToken());
        }
        return sum;
    }
}