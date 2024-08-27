import java.util.*;
import java.io.*;

public class Main {

    static int[][] arr;
    static int n;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());  // 상담 기간
            arr[i][1] = Integer.parseInt(st.nextToken());  // 상담 금액
        }
        backtracking(0, 0);
        System.out.println(result);
    }

    public static void backtracking(int day, int sum) {
        // 기저 조건: 모든 날을 다 돌았을 경우
        if (day == n) {
            result = Math.max(result, sum); // 최종 결과 갱신
            return;
        }

        // 오늘 일을 선택할 수 있는 경우
        if (day + arr[day][0] <= n) {
            backtracking(day + arr[day][0], sum + arr[day][1]);
        }

        // 오늘 일을 선택하지 않는 경우
        backtracking(day + 1, sum);
    }
}