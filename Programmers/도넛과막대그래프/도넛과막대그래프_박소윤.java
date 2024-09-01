import java.util.*;

class Solution {
    
    Map<Integer, List<Integer>> outputGraph = new HashMap<>();
    Map<Integer, List<Integer>> inputGraph = new HashMap<>();
    boolean[] visited;
    int[] answer = new int[4];
    
    public int[] solution(int[][] edges) {
        initGraph(edges);
        visited = new boolean[outputGraph.size() + 1];
        
        int generatedVertex = findGeneratedVertex();
        answer[0] = generatedVertex;
        
        for (int start : outputGraph.get(generatedVertex)) {
            answer[getGraphType(start)]++;
        }
        return answer;
    }
    
    private void initGraph(int[][] edges) {
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            outputGraph.putIfAbsent(from, new ArrayList<>());
            outputGraph.putIfAbsent(to, new ArrayList<>());
            outputGraph.get(from).add(to);
            inputGraph.putIfAbsent(from, new ArrayList<>());
            inputGraph.putIfAbsent(to, new ArrayList<>());
            inputGraph.get(to).add(from);
        }
    }
    
    private int findGeneratedVertex() {
        for (Map.Entry<Integer, List<Integer>> entry : inputGraph.entrySet()) {

            if (entry.getValue().size() == 0) { // input 간선 존재 X
                if (outputGraph.get(entry.getKey()).size() > 1) {   // 외부로 향하는 간선이 2개 이상
                    return entry.getKey();  // 생성된 정점이구나!
                }
            }
        }
        return -1;
    }
    
    private int getGraphType(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        while(!q.isEmpty()) {
            int cur = q.poll();
            visited[cur] = true;
            if (outputGraph.get(cur).size() == 2) { // output이 2인 노드 존재 -> 8자 그래프
                return 3;
            }
            for (int next : outputGraph.get(cur)) {
                if (visited[next]) {
                    if (next == start) {    // 돌고 돌아 처음으로 -> 도넛 그래프
                        return 1;
                    }
                    continue;
                }
                q.offer(next);
            }
        }
        // 비순환 그래프 -> 막대 그래프
        return 2;
    }
}