import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // Intput
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] cnt = new int[100_001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // Two-Pointer
        int start = 0;
        int end = 0;
        int answer = 0;

        while (end < N) {
            int temp = arr[end];
            cnt[temp]++;

            // 수열에 포함 불가능 -> 수열 초기화
            if (cnt[temp] > K) {
                answer = Math.max(answer, end - start);
                // 현재 수가 빠질 때 까지 수열에서 빼기
                for (int i = start; i < end; i++) {
                    cnt[arr[i]]--;
                    if (cnt[temp] == K) {
                        start = i + 1;
                        break;
                    }
                }
            }
            end++;
        }

        // end == N 일 때 남은 수열 길이도 비교
        answer = Math.max(answer, end - start);

        // Output
        bw.write(Integer.toString(answer));
        bw.flush();
    }
}