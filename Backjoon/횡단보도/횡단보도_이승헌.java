package 횡단보도;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 횡단보도_이승헌 {

    static List<List<Node>> map = new ArrayList<>();
    static int N;
    static long M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int idx = 0; idx <= N; idx++) {
            map.add(new ArrayList<>());
        }

        for (int idx = 1; idx <= M; idx++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            map.get(to).add(new Node(from, 0, idx));
            map.get(from).add(new Node(to, 0, idx));
        }

        System.out.println(solve());
    }

    private static long solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0, 0));

        long[] dp = new long[N + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.idx == N) {
                return cur.cycle * M + cur.time;
            }

            for (Node next : map.get(cur.idx)) {
                long nextTime = cur.cycle * M + next.time;

                if (cur.time < next.time && dp[next.idx] > nextTime) {
                    dp[next.idx] = nextTime;
                    pq.offer(new Node(next.idx, cur.cycle, next.time));
                } else if(dp[next.idx] >  nextTime + M) {
                    dp[next.idx] =  nextTime + M;
                    pq.offer(new Node(next.idx, cur.cycle + 1, next.time));
                }
            }
        }
        return 0;
    }

    private static class Node implements Comparable<Node> {
        int idx;
        int cycle;
        int time;

        public Node(int idx, int cycle, int time) {
            this.idx = idx;
            this.cycle = cycle;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            if (cycle == o.cycle) {
                return time - o.time;
            }
            return cycle - o.cycle;
        }
    }

}
