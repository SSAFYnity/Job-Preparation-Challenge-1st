import java.util.*;

class Solution {
    
    class Taxi implements Comparable<Taxi> {
        int node, fare;
        public Taxi(int node, int fare) {
            this.node = node;
            this.fare = fare;
        }
        public int compareTo(Taxi o) {
            if (this.fare == o.fare) return this.node - o.node;
            return this.fare - o.fare;
        }
    }
    
    static PriorityQueue<Taxi> que = new PriorityQueue<>();
    static boolean[] visit;
    static int[][] memo;
    static List<Taxi>[] tree;
    
    public void dijkstra(int n, int start) {
        que.clear();
        visit = new boolean[n+1];
        for (int i = 0; i < n+1; i++) {
            memo[start][i] = Integer.MAX_VALUE;
        }
        memo[start][start] = 0;
        que.add(new Taxi(start, 0));
        
        while (!que.isEmpty()) {
            Taxi now = que.poll();
            if (visit[now.node]) continue;
            visit[now.node] = true;
            
            for (Taxi next : tree[now.node]) {
                int fareSum = next.fare + now.fare;
                if (fareSum >= memo[start][next.node]) continue;
                memo[start][next.node] = fareSum;
                que.add(new Taxi(next.node, fareSum));
            }
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        memo = new int[n+1][n+1];
        tree = new ArrayList[n+1];
        for (int i = 1; i < n+1; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] f : fares) {
            tree[f[0]].add(new Taxi(f[1], f[2]));
            tree[f[1]].add(new Taxi(f[0], f[2]));
        }

        dijkstra(n, s);
        dijkstra(n, a);
        dijkstra(n, b);
        
        for (int i = 1; i < n+1; i++) {
            int temp = memo[s][i] + memo[a][i] + memo[b][i];
            answer = Math.min(temp, answer);
        }
        
        answer = Math.min(memo[s][a]+memo[s][b], answer);
        
        return answer;
    }
}