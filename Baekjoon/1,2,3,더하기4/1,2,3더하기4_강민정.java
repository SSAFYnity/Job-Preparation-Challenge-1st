import java.util.Scanner;

public class 1,2,3더하기4_강민정 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // 테스트 케이스의 수
        int[] testCases = new int[t];

        // 테스트 케이스 입력
        for (int i = 0; i < t; i++) {
            testCases[i] = sc.nextInt();
        }

        // 최대 n은 10000이므로 이를 기준으로 DP 배열을 미리 계산
        int maxN = 10000;
        int[] dp = new int[maxN + 1];

        // DP 기본 케이스
        dp[0] = 1; // 0을 만들 수 있는 방법은 1가지 (아무것도 선택하지 않는 경우)

        // 1, 2, 3을 사용하여 dp 배열 채우기 (중복되지 않게)
        for (int i = 1; i <= 3; i++) {
            for (int j = i; j <= maxN; j++) {
                dp[j] += dp[j - i];
            }
        }

        // 각 테스트 케이스에 대해 결과 출력
        for (int i = 0; i < t; i++) {
            System.out.println(dp[testCases[i]]);
        }

        sc.close();
    }
}
