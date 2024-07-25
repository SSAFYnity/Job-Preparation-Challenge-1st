import java.util.*;

class Solution {
    private static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private ArrayList<Node>[] graphs;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        graphs = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graphs[i] = new ArrayList<Node>();
        }

        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];

            graphs[start].add(new Node(end, cost));
            graphs[end].add(new Node(start, cost));
        }

        int[] distFromS = dijkstra(s, n);
        int[] distFromA = dijkstra(a, n);
        int[] distFromB = dijkstra(b, n);

        int minFare = Integer.MAX_VALUE;

        for (int k = 1; k <= n; k++) {
            if (distFromS[k] == Integer.MAX_VALUE || distFromA[k] == Integer.MAX_VALUE || distFromB[k] == Integer.MAX_VALUE) {
                continue;
            }
            int fare = distFromS[k] + distFromA[k] + distFromB[k];
            if (fare < minFare) {
                minFare = fare;
            }
        }

        return minFare;
    }

    private int[] dijkstra(int start, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curVertex = cur.vertex;
            int curCost = cur.cost;

            if (curCost > dist[curVertex]) {
                continue;
            }

            for (Node next : graphs[curVertex]) {
                if (dist[next.vertex] > curCost + next.cost) {
                    dist[next.vertex] = curCost + next.cost;
                    pq.offer(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }

        return dist;
    }
}
