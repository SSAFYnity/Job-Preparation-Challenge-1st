package 성냥개비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 성냥개비_이승헌 {
    static int[][] minNum = {{6, 0}, {2, 1}, {5, 2}, {4, 4}, {6, 6}, {3, 7}, {7, 8}}; // 2, 3, 4, 5, 6, 7
    // 7 = 8 = 1 + 1 + 2 // 8
    // 6 = 9 = 1 + 1 + 1 // 0
    // 5 = 1 + 7 // 2
    // 4 = 1 + 1 // 4
    // 3 = 7 // 7
    // 2 = 1 // 1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int idx = 0; idx < N; idx++) {
            int num = Integer.parseInt(br.readLine()); // 성냥 갯수
            min(num, sb);
            sb.append(' ').append(max(num)).append('\n');
        }

        System.out.println(sb);
    }

    private static StringBuilder min(int num, StringBuilder sb) {
        int minDigit = calMinDigit(num); // 최대 자릿수
        int idx = 0; // 자릿수

        while (idx != minDigit) {
            if (num % 7 == 0) {
                for (; idx < minDigit; idx++) {
                    sb.append(8);
                }
                break;
            }

            for (int[] min : minNum) {
                if (idx == 0 && min[1] == 0) {
                    continue;
                }

                num -= min[0];
                if (calMinDigit(num) + idx + 1 != minDigit || num < 0) {
                    num += min[0];
                    continue;
                }
                sb.append(min[1]);
                idx++;
                break;
            }
        }
        return sb;
    }


    private static int calMinDigit(int num) { // 가장 작은 숫자가 몇 자릿수인지 구하는 함수
        return num % 7 > 0 ? num / 7 + 1 : num / 7;
    }

    private static StringBuilder max(int num) {
        StringBuilder sb = new StringBuilder();
        int numOf2 = num >> 1;

        for (int i = 0; i < numOf2; i++) {
            sb.append(1);
        }

        if (num % 2 != 0) {
            sb.replace(0, 1, "7");
        }
        return sb;
    }
}
