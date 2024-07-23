import java.util.*;

class Solution {

    class Info {
        int cur, costSum;
        Info(int _cur, int _costSum) {
            cur = _cur; costSum = _costSum;
        }
    }

    class Edge {
        int to, cost;
        Edge(int _to, int _cost) {
            to = _to; cost = _cost;
        }
        int to() {
            return to;
        }
    }

    ArrayList<Edge>[] graph;
    int nodeCnt, start, endA, endB;
    int[][] minCosts;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        nodeCnt = n;
        start = s;
        endA = a;
        endB = b;

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];

            graph[start].add(new Edge(end, cost));
            graph[end].add(new Edge(start, cost));
        }

        minCosts = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dijkstra(i, minCosts[i]);
        }

        int answer = Integer.MAX_VALUE;

        for (int stop = 1; stop <= n; stop++) { // 어피치와 무지가 헤어질 장소
            int stopCost = minCosts[start][stop]; // 헤어진 장소까지의 비용
            if (minCosts[stop][endA] == Integer.MAX_VALUE || minCosts[stop][endB] == Integer.MAX_VALUE) continue;
            stopCost += minCosts[stop][endA]; // 각자 장소까지의 비용
            stopCost += minCosts[stop][endB];
            answer = Math.min(answer, stopCost);
        }

        if (answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }

    public void dijkstra(int start, int[] minCost) {

        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[start] = 0;

        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> (o1.costSum - o2.costSum));
        pq.add(new Info(start, 0));

        while (!pq.isEmpty()) {

            Info curInfo = pq.poll();
            if (minCost[curInfo.cur] != curInfo.costSum) continue;
            for (Edge next : graph[curInfo.cur]) {
                int nextCostSum = curInfo.costSum + next.cost;
                if (minCost[next.to] <= nextCostSum) continue;
                minCost[next.to] = nextCostSum;
                pq.add(new Info(next.to, nextCostSum));
            }
        }

    }
}