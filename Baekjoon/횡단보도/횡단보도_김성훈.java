import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Edge {
        int to, greenStartTime;

        Edge(int _to, int _greenStartTime) {
            to = _to; greenStartTime = _greenStartTime;
        }
    }

    static class Info {
        int cur;
        long time;

        Info(int _cur, long _time) {
            cur = _cur; time = _time;
        }
    }

    public static void main(String[] args) throws IOException {

        int n, m;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e, i));
            graph[e].add(new Edge(s, i));
        }

        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> Long.compare(o1.time, o2.time));

        long[] minTime = new long[n + 1];
        Arrays.fill(minTime, Long.MAX_VALUE);

        que.add(new Info(1, 0));
        minTime[1] = 0;

        while (!que.isEmpty()) {

            Info curInfo = que.poll();
            long curTime = curInfo.time;
            int curLoc = curInfo.cur;

            if (minTime[curLoc] != curTime) continue;

            for (Edge next : graph[curLoc]) {

                long order = curTime % m;
                order = next.greenStartTime - order;
                if (order < 0) order += m; // 만약 순서가 지나갔다면 다음 순서에 건너기

                long nextTime = curTime + order;

                if (minTime[next.to] > nextTime) {
                    minTime[next.to] = nextTime;
                    que.add(new Info(next.to, nextTime));
                }

            }
        }

        System.out.println(minTime[n]);
    }
}