import java.io.*;
import java.util.*;

public class Main {
    // 구간 합 + 투 포인터
    // 전 구역의 누적합을 구한다.
    // sum[right] - sum[left] 와 target 값을 비교해서, right나 left를 이동 시킨다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long [] sum = new long [N+1];
        // 1. 누적합 만들기
        st = new StringTokenizer(br.readLine());
        sum[0] = 0;
        sum[1] = Integer.parseInt(st.nextToken());
        for (int i = 2; i <= N; i++) {
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
        }
        // 2. 투포인터로 값 구하기
        int left = 0, right = 0;
        long cur_sum = 0;
        long cur_len = Integer.MAX_VALUE;
        while (right <= N){
            cur_sum = sum[right] - sum[left];
            if(cur_sum == M){
                cur_len = Math.min(cur_len, right -left);
                right++;
            } else if (cur_sum < M) {
                right++;
            } else {
                cur_len = Math.min(cur_len, right -left);
                left++;
            }
        }
        System.out.println(cur_len == Integer.MAX_VALUE? 0 : cur_len);
    }
}