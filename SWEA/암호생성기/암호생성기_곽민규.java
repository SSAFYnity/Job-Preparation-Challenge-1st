import java.io.*;
import java.util.*;

class Solution{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static Deque<Integer> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception{
        for(int test = 1;test<=10;test++) {
            br.readLine();
            sb.append("#").append(test).append(" ");
            st = new StringTokenizer(br.readLine());
            int min = Integer.MAX_VALUE;

            // 입력 받으면서 제일 작은 값 확인
            for(int i=0;i<8;i++) {
                q.offer(Integer.parseInt(st.nextToken()));
                min = Math.min(min, q.peekLast());
            }

            // 모든 수가 15보다 큰 경우에는 하나씩 계산 할 필요가 없음
            min -= (min - 1) % 15 + 1;
            for(int i = 0;i < 8;i++) {
                q.offer(q.poll() - min);
            }

            // 문제에서 시키는대로 계산
            for(int i = 0;q.peekLast() > 0;i = (i + 1) % 5) {
                q.offer(q.poll() - i - 1);
            }
            q.pollLast();
            q.offerLast(0);

            // 출력
            while(!q.isEmpty()) sb.append(q.poll()).append(" ");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}