import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String args[]) throws Exception	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int test_case = 1; test_case <= 10; test_case++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int n = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            sb.append("#");
            sb.append(test_case);
            sb.append(" ");
            int answer = 0;

            ArrayDeque<Integer> deque = new ArrayDeque<>();
            boolean[] visit = new boolean[100];

            TreeSet<Integer>[] map = new TreeSet[100];
            for (int i = 0; i < 100; i++) {
                map[i] = new TreeSet<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int dep = Integer.parseInt(st.nextToken());
                int arr = Integer.parseInt(st.nextToken());
                map[dep].add(arr);
            }

            deque.add(0);
            visit[0] = true;
            while (!deque.isEmpty()) {
                int now = deque.poll();
                if (now == 99) {
                    answer = 1;
                    break;
                }
                for (Iterator iter = map[now].iterator(); iter.hasNext(); ) {
                    int next = (int) iter.next();
                    if (visit[next]) continue;
                    deque.add(next);
                    visit[next] = true;
                }
            }

            sb.append(answer);

            System.out.println(sb.toString());
        }
    }
}
