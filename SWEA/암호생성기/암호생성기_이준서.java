import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class s1225암호생성기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = 10;
        int n = 8;
        for (int t = 1; t <= T; t++) {
            String str = br.readLine();
            st = new StringTokenizer(br.readLine());
            Queue<Integer> queue = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }

            int sub = 1;

            while(!queue.isEmpty() && queue.peek() - sub > 0){
                queue.add(queue.poll() - sub++);
                if(sub == 6) sub = 1;
            }
            queue.poll();
            queue.add(0);

            sb.append("#").append(t);
            for (int i = 0; i < n; i++) {
                sb.append(" ").append(queue.poll());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
