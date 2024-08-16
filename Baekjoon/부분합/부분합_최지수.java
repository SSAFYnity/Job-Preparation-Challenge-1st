import java.util.*;
import java.io.*;

public class Main {

    static int n, s, answer, inf = Integer.MAX_VALUE;
    static int[] sequence, partialSum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        sequence = new int[n];
        partialSum = new int[n];
        st = new StringTokenizer(br.readLine());
        answer = inf;
        sequence[0] = Integer.parseInt(st.nextToken());
        if (sequence[0] >= s) answer = 1;
        partialSum[0] = sequence[0];

        for (int i = 1; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
            if (sequence[i] >= s) {
                answer = 1;
                break;
            }
            partialSum[i] = partialSum[i-1] + sequence[i];
        }

        for (int i = -1; i < n-2; i++) {
            for (int j = i+2; j < n; j++) {
                if (j-i >= answer) break;
                int sum = partialSum[j];
                if (i != -1) sum -= partialSum[i];
                if (sum >= s) {
                    answer = Math.min(j-i, answer);
                    break;
                }
            }
        }

        System.out.println(answer == inf ? 0 : answer);
    }
}
