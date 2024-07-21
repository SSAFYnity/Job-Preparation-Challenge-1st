package 암호생성기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 암호생성기_이승헌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>(8);

        for (int tc = 1; tc <= 10; tc++) {
            int testCase = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int idx = 0; idx < 8; idx++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }

            int curNum;
            int i = 0;
            do {
                curNum = queue.poll();
//                poll -= i++;
//                i = i > 5 ? 1 : i;
                i %= 5; // 기존 조건식에서 모듈러 연산으로 변경
                curNum -= ++i;

                queue.offer(curNum);
            } while (curNum > 0);

            sb.append("#").append(testCase).append(" ");
            for (int idx = 0; idx < 7; idx++) {
                sb.append(queue.poll()).append(" ");
            }
            queue.poll(); // clear() 대신 poll로 비워주고, 0을 추가하여 마무리
            sb.append(0).append("\n");
//            queue.clear();
        }
        System.out.println(sb.toString());
    }
}
