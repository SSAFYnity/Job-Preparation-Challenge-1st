import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class 도넛과막대그래프_강민정 {
    private static final int VERTEX_CNT = 1000000;      // 정점의 갯수

    static List<List<Integer>> graph;       // 어떤 정점에서 나가는 정점을 기록
    static List<List<Integer>> reverseGraph;        // 어떤 정점을 들어오는 정점을 기록
    static boolean[] visited;       // 방문 배열
    static int barGraph = 0;         // 막대 그래프 갯수
    static int eightGraph = 0;        // 8자 그래프 갯수

    public int[] solution(int[][] edges) {
        graph = new ArrayList();
        reverseGraph = new ArrayList();
        List<Integer> vertex = new ArrayList();     // 생성된 정점과 연결된 정점들의 번호
        int graphLen = 0;       // 총 모양 그래프 개수
        visited = new boolean[VERTEX_CNT];
        int generatedVertex = -1;       // 생성된 정점 번호

        // 인접 리스트 초기화
        for(int i=0; i<VERTEX_CNT; i++) {
            graph.add(new ArrayList());
            reverseGraph.add(new ArrayList());
        }

        // 간선 연결
        for(int[] edge : edges) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            graph.get(a).add(b);        // a -> b
            reverseGraph.get(b).add(a);     // b -> a
        }

        // 생성된 정점 찾기
        for(int i=0; i<=edges.length; i++) {
            if(graph.get(i).size() >= 2 && reverseGraph.get(i).isEmpty()) { // 나가는 간선이 2개 이상 존재하면서 들어오는 간선이 존재하지 않다면
                generatedVertex = i;
                break;
            }
        }

        // 생성된 정점과 연결된 정점 기억하기
        graphLen = graph.get(generatedVertex).size();       // 생성된 정점과 연결된 정점의 개수 = 모양 그래프 갯수
        vertex.addAll(graph.get(generatedVertex));          // 생성된 정점과 연결된 정점들의 번호 추가

        // 생성된 정점과 연결된 간선을 모두 삭제
        for(int v : graph.get(generatedVertex)) {
            int idx = reverseGraph.get(v).indexOf(generatedVertex);     // 생성된 정점의 인덱스
            reverseGraph.get(v).remove(idx);    // 생성된 정점의 번호 삭제
        }
        graph.get(generatedVertex).clear();     // 생성된 정점에서 나가는 정점들 모두 제거

        for(int node : vertex) {       // 생성된 정점과 연결된 정점
            if(!isBarGraphVertex(node)) {       // 막대 그래프가 아니면
                bfs(node);      // 생성된 정점과 연결된 정점에서 시작해서 모두 탐색
            } else {
                barGraph++;     // 정점이 막대 그래프의 정점 중 하나라면 BFS 탐색을 못 한다
            }
        }

        return new int[]{generatedVertex + 1, graphLen - (barGraph + eightGraph), barGraph, eightGraph};  // {생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수}
    }

    /*
        시작점 node에서 출발해서 연결된 정점들을 모두 탐색
        막대 그래프, 8자 그래프의 갯수를 세기
    */
    private void bfs(int node) {
        Queue<Integer> que = new LinkedList();
        que.add(node);
        visited[node] = true;       // 시작 정점 방문 처리

        while(!que.isEmpty()) {     // 큐가 비어있지 않을 동안에 반복
            int vertex = que.poll();        // 가장 앞에 있는 정점을 꺼낸다

            if(isBarGraphVertex(vertex)) {     // 막대 그래프라면
                barGraph++;
            } else if(isEightGraphVertex(vertex)) {       // 8자 그래프라면
                eightGraph++;
            }

            for(int connectedVertex : graph.get(vertex)) {      // 현재 정점과 연결된 정점
                if(visited[connectedVertex]) {      // 방문한 정점이라면
                    continue;       // 건너띄기
                }
                visited[connectedVertex] = true;        // 방문 처리
                que.add(connectedVertex);       // 큐에 추가
            }
        }
    }

    private boolean isBarGraphVertex(int node) {
        return graph.get(node).isEmpty();   // 나가는 간선이 없으면 막대 그래프
    }

    private boolean isEightGraphVertex(int node) {
        return graph.get(node).size() == 2 && reverseGraph.get(node).size() == 2;     // 나가는 간선 2개와 들어오는 간선이 2개인 정점
    }
}