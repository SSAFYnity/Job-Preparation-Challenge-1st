package swea.d3;

import java.io.*;
import java.util.*;

public class 암호생성기_김현진 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {
            st = new StringTokenizer(sc.readLine());
            st.nextToken(); // Skip
            st = new StringTokenizer(sc.readLine());
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            for(int i = 0; i < 8; i++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }

            int idx = 1;
            while(true) {
                int current = queue.poll();
                current -= idx;

                if(current <= 0) {
                    queue.offer(0);
                    break;
                } else queue.offer(current);
                idx = (idx >= 5) ? 1 : idx + 1;
            }

            sb.append("#").append(test_case).append(" ");
            for(int num : queue) {
                sb.append(num).append(" ");
            } sb.append("\n");
        } System.out.print(sb);
    }
}