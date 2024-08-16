import java.util.*;
class Solution {
    static List<Node> list[];
    
    public int solution(int n, int s, int a, int b, int[][] fares) {       
        list = new ArrayList[n+1];
        for(int i=0;i<n+1;i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0;i<fares.length;i++){ // 양방향
            list[ fares[i][0] ].add(new Node(fares[i][1], fares[i][2]));
            list[ fares[i][1] ].add(new Node(fares[i][0], fares[i][2]));
        }
        
        int[] distS = func(n, s); // s->i, S에서 각 지점까지의 최단거리
        int[] distA = func(n, a); // i->a = a->i, A에서 각 지점까지의 최단거리
        int[] distB = func(n, b); // i->b = b->i, B에서 각 지점까지의 최단거리
        
        int answer = Integer.MAX_VALUE;
        for(int i=0;i<n+1;i++){
            if(distS[i] != Integer.MAX_VALUE &&distA[i] != Integer.MAX_VALUE &&distB[i] != Integer.MAX_VALUE){
                answer = Math.min(answer, distS[i]+distA[i]+distB[i]);
            }
        }
        
        return answer;
    }
    
    public int[] func(int n, int start){   
        
        int[] dist = new int[n+1]; // 최소 비용을 저장할 배열
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        
        while(!q.isEmpty()){
            Node node = q.poll();            
            // 꺼낸 노드 = 현재 최소 비용을 갖는 노드
			// 즉, 해당 노드의 비용이 현재 dist배열에 기록된 내용보다 크다면 고려할 필요가 없으므로 스킵 -> 중복노드 방지
            if (node.cost > dist[node.x]) continue;
            
            for(Node next : list[node.x]){
                if(dist[next.x] > node.cost+next.cost){
                    dist[next.x] = node.cost+next.cost;
                    q.add(new Node(next.x, dist[next.x]));
                }
            }            
        }
        
        return dist;
    }
    
    public class Node implements Comparable<Node> {
        int x;
        int cost;
        
        public Node(int x, int cost){
            this.x = x;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}
