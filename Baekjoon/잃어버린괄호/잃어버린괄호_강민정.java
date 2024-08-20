import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

/*
    1. -를 기준으로 문자열을 나눈다.
    2. 나눈 문자열에서 다시 +를 기준으로 문자열을 나눈다.
    3. +로 나눈 문자열들의 합을 구한다.
    4. 합계에서 구한 문자열들의 합을 뺀다.
*/
public class 잃어버린괄호_강민정 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();       // 0 ~ 9, +, -로만 이루어진 50보다 작거나 같은 길이의 식
        StringTokenizer st = new StringTokenizer(s, "-");       // -를 기준으로 문자열을 나누기
        int answer = Integer.MAX_VALUE;     // 합계

        while(st.hasMoreTokens()) {     // 뺄 값이 있을 동안에
            StringTokenizer str1 = new StringTokenizer(st.nextToken(), "+");        // +를 기준으로 문자열을 나누기
            int tempSum = 0;        // 더한 값을 담는 변수

            while(str1.hasMoreTokens()) {       // 더할 값이 있을 동안에
                tempSum += Integer.parseInt(str1.nextToken());
            }

            if(answer == Integer.MAX_VALUE) {         // 첫 번째 피연산자 값은 합계에 그대로 대입
                answer = tempSum;
            } else {
                answer -= tempSum;
            }
        }

        System.out.println(answer);     // 괄호를 적절히 쳐서 값을 최소로 만든 값
    }
}