package 암호생성기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 암호생성기_함승찬 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer token;

        for (int t = 0; t < 10; t++) {

            Deque<Integer> dq = new ArrayDeque<>();

            int testCase = Integer.parseInt(br.readLine());
            token = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 8; i++) {
                dq.add(Integer.parseInt(token.nextToken()));
            }

            int value = 1;
            while (true) {
                int tmp = dq.pollFirst();
                tmp -= value++;
                value = (value == 6 ? 1 : value);
                if (tmp <= 0) {
                    dq.addLast(0);
                    break;
                }
                dq.addLast(tmp);
            }

            sb.append("#").append(testCase).append(" ");
            while (!dq.isEmpty()) {
                sb.append(dq.pollFirst()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
