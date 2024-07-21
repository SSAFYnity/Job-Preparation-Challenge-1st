import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = 10;
        int maxVertex = 100;
        for (int t = 1; t <= T ; t++) {
            st  = new StringTokenizer(br.readLine());
            int numTestCase = Integer.parseInt(st.nextToken());
            int numEdge = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int[] edge1 = new int[100];
            int[] edge2 = new int[100];

            for (int i = 0; i < numEdge; i++) {
                int idx = Integer.parseInt(st.nextToken());
                if (edge1[idx] == 0) {
                    edge1[idx] = Integer.parseInt(st.nextToken());
                } else {
                    edge2[idx] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<Integer> queue = new ArrayDeque<>();
            if(edge1[0] != 0) queue.add(edge1[0]);
            if(edge2[0] != 0) queue.add(edge2[0]);

            int ans = 0;
            while(!queue.isEmpty()) {
                int idx = queue.poll();
                if(edge1[idx] != 0) queue.add(edge1[idx]);
                if(edge2[idx] != 0) queue.add(edge2[idx]);
                if(idx == 99) ans = 1;
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
