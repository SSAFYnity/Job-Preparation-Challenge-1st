import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= 10; testCase++) {
            Queue<Integer> q = new ArrayDeque<>();
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                q.offer(Integer.parseInt(st.nextToken()));
            }
            int x = 1;
            while (true) {
                int temp = q.poll() - x;
                x = x % 5 + 1;
                if (temp <= 0) {
                    q.offer(0);
                    break;
                }
                q.offer(temp);
            }
            sb.append("#").append(testCase).append(" ");
            for (Integer num : q) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
