import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 암호생성기_강수민 {

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Queue<Integer> que = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            int testNum = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 8; j++) {
                que.add(Integer.parseInt(st.nextToken()));
            }

            // Progress
            boolean flag = true;
            while (flag) {
                for (int j = 1; j <= 5; j++) {
                    int cur = Math.max(que.poll() - j, 0);
                    que.add(cur);
                    if (cur == 0) {
                        flag = false;
                        break;
                    }
                }
            }

            // Output
            sb.append('#').append(testNum);
            for (int j = 0; j < 8; j++) {
                sb.append(' ').append(que.poll());
            }
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }
}