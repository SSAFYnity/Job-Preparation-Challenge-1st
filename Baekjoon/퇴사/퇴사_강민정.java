import java.io.*;

public class 퇴사_강민정 {
    static class Counsel {
        int t;      // 상담을 완료하는데 걸리는 기간
        int p;      // 상담을 했을 때 받을 수 있는 금액

        Counsel(int t, int p) {
            this.t = t;
            this.p = p;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());         // 남은 일수
        Counsel[] counselArr = new Counsel[n];
        int[] dp = new int[n + 2];
        for(int i=0; i<n; i++) {
            String[] input = br.readLine().split(" ");
            int t = Integer.parseInt(input[0]);     // 1 <= 상담을 완료하는데 걸리는 기간 <= 5
            int p = Integer.parseInt(input[1]);     // 1 <= 상담을 했을 때 받을 수 있는 금액 <= 1,000
            counselArr[i] = new Counsel(t, p);      // n일까지 기간 및 금액 저장
        }

        for(int i=n-1; i>-1; i--) {
            if(counselArr[i].t + i > n) {       // 이 상담을 완료하면 n일을 넘어선다
                dp[i] = dp[i + 1];      // 이전 상담 시 받은 금액 할당
            } else {
                dp[i] = Math.max(dp[i + 1], counselArr[i].p + dp[i + counselArr[i].t]);    // 이전 상담 시 받은 금액과 이 상담을 완료한 후 금액 중 최댓값 할당
            }
        }

        System.out.println(dp[0]);       // 최대 이익
    }
}