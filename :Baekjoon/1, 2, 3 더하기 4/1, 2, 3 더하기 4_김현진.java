import java.io.*;

public class 1, 2, 3 더하기 4_김현진 {
    
    public static void main (String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        int[][] dp = new int[4][10001];
            
        dp[0][0] = 1;

        for (int j=1; j<4; j++) {
            for (int k=0; k<=10000; k++) {
                if (j > k) dp[j][k] = dp[j-1][k];
                else dp[j][k] = dp[j][k-j] + dp[j-1][k];
            }
        }
        
        for (int i=0; i<T; i++) {
            
            int n = Integer.parseInt(br.readLine());
            
            sb.append(dp[3][n] + "\n");
        }
        
        System.out.println(sb);
        
        br.close();
    }
}