import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;

class 강민정_합승택시요금 {
    static int[][] mapArr;      // 인접 행렬
    
    class Node {
        int payment;    // 비용
        int num;        // 지점 번호
        
        public Node(int payment, int num) {
            this.payment = payment;
            this.num = num;
        }
    }
    
    /*
        n: 3 <= 지점의 개수 <= 200
        s: 1 <= 출발지점 <= n
        a: 1<= A의 도착지점 <= n
        b: 1 <= B의 도착지점 <= n
        fares: 지점 사이의 예상 택시요금 [c, d, c지점과 d지점 사이의 예상 택시 요금]
    */
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;     // 예상 최저 택시요금
        mapArr = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(mapArr[i], Integer.MAX_VALUE);  // 연결되지 않은 부분을 무한대로 초기화
            mapArr[i][i] = 0;  // 자기 자신으로의 비용은 0으로 초기화
        }
        for(int[] fare : fares) {
            int c = fare[0];            // 지점1
            int d = fare[1];            // 지점2
            int payment = fare[2];      // 비용
            mapArr[c][d] = payment;     // c -> d로 가는데 payment 비용만큼 소비
            mapArr[d][c] = payment;     // d -> c로 가는데 payment 비용만큼 소비
        }
        
        int[] sDijkstraResult = dijkstra(s);        // 출발점 s에서 다른 정점으로의 최소 비용 배열
        for(int i = 1; i <= n; i++) {
            int[] iDijkstraResult = dijkstra(i);
            // (출발s -> i지점) + (i지점 -> a도착지점) + (i지점 -> b도착지점)
            answer = Math.min(answer, sDijkstraResult[i] + iDijkstraResult[a] + iDijkstraResult[b]);   
        }

        return answer;      // 예상 최저 택시요금
    }
    
    public int[] dijkstra(int s) {
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(a -> a.payment));     // 비용이 낮은 순으로 우선순위 큐 정렬
        Set<Integer> visited = new HashSet();       // 방문 여부 집합
        int[] payments = new int[mapArr.length];    // 출발 s에서 시작하는 비용 배열
        Arrays.fill(payments, Integer.MAX_VALUE);       // 비용 배열의 초기값 설정
        payments[s] = 0;        // 출발 지점의 비용은 0으로 할당
        que.add(new Node(0, s));    // 우선순위 큐에 출발 지점의 객체(객체, 지점번호) 추가
        
        while(!que.isEmpty()) {     
            Node curNode = que.remove();    // 비용이 가장 낮은 객체 꺼내기
            int cur = curNode.num;          // 현재 지점 번호
            if(visited.contains(cur)) {     // 방문한 지점 건너띄기
                continue;
            }
            visited.add(cur);               // 현재 정점 방문 처리      
            for(int j = 1; j < mapArr.length; j++) {        // 현재 정점과 인접한 정점 i를 탐색
                if(mapArr[cur][j] == Integer.MAX_VALUE) {     // 연결되지 않은 정점은 건너띄기
                    continue;
                }
                if(payments[j] > payments[cur] + mapArr[cur][j]) {
                    payments[j] = payments[cur] + mapArr[cur][j];       // 최소 비용 갱신
                    que.add(new Node(payments[j], j));      // 우선순위 큐에 추가
                }
            }
        }
        
        return payments;
    }
}
