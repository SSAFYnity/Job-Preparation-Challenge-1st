package 문자열교환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열교환_이승헌 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int len = str.length();
        boolean[] dp = new boolean[2 * len];
        int aCnt = 0;
        for (int idx = 0; idx < len; idx++) {
            if (str.charAt(idx) == 'a') {
                aCnt++;
                dp[idx] = true;
            }
        }
        System.arraycopy(dp, 0, dp, len, len);

        int min = 0;
        for (int i = 0; i < aCnt; i++) {
            if (str.charAt(i) == 'b') {
                min ++;
            }
        }

        int start = min;
        int maxLen = len + aCnt;
        for (int i = aCnt; i < maxLen; i++) {
            if(!dp[i]){
                start ++;
            }
            if(!dp[i - aCnt]){
                start --;
                min = Math.min(min, start);
            }
        }

        System.out.println(min);
    }
}