import java.io.*;

class 1,2,3더하기3_강민정 {
    private static final int SIZE = 1000001;
    private static final int DENOMINATOR = 1000000009;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());        // 테스트 케이스 수
        long[] dp = new long[SIZE];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<SIZE; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % DENOMINATOR;
        }

        for(int tc=0; tc<t; tc++) {
            bw.write(String.valueOf(dp[Integer.parseInt(br.readLine())]));
            bw.write("\n");
        }

        bw.flush();
    }
}